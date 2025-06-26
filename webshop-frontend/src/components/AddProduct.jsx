import React, { useState, useRef, useEffect } from 'react';

function AddProduct({ onProductSaved, productToEdit }) {
  const isEditMode = Boolean(productToEdit);

  const [product, setProduct] = useState({
    id: null,
    name: '',
    price: '',
    quantity: '',
    description: '',
    imageUrl: '',
  });

  const [imageFile, setImageFile] = useState(null);
  const [message, setMessage] = useState('');
  const fileInputRef = useRef(null);

  useEffect(() => {
    if (isEditMode && productToEdit) {
      setProduct({ ...productToEdit });
      setImageFile(null);
      if (fileInputRef.current) {
        fileInputRef.current.value = '';
      }
    }
  }, [isEditMode, productToEdit]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleImageChange = (e) => {
    if (e.target.files && e.target.files[0]) {
      setImageFile(e.target.files[0]);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('Submitting...');

    try {
      const formData = new FormData();
      formData.append('name', product.name);
      formData.append('price', product.price);      // Append as string
      formData.append('quantity', product.quantity); // Append as string
      formData.append('description', product.description);
      if (imageFile) {
        formData.append('image', imageFile);
      }

      let response;

      if (isEditMode) {
        response = await fetch(`http://localhost:8080/products/${product.id}`, {
          method: 'PUT',
          body: formData,
        });
      } else {
        response = await fetch('http://localhost:8080/products', {
          method: 'POST',
          body: formData,
        });
      }

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || 'Failed to save product');
      }

      const savedProduct = await response.json();
      setMessage(isEditMode ? '✅ Product updated!' : '✅ Product added!');

      if (onProductSaved) onProductSaved(savedProduct);

      // ✅ This line to force page refresh:
      window.location.reload();

      // Reset form after success
      setProduct({ id: null, name: '', price: '', quantity: '', description: '', imageUrl: '' });
      setImageFile(null);
      if (fileInputRef.current) {
        fileInputRef.current.value = '';
      }
    } catch (error) {
      setMessage('❌ Error: ' + error.message);
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>{isEditMode ? 'Edit Product' : 'Add New Product'}</h2>
      {message && (
        <p style={message.startsWith('✅') ? styles.success : styles.error}>
          {message}
        </p>
      )}
      <form onSubmit={handleSubmit} style={styles.form}>
        <div style={styles.formGroup}>
          <label style={styles.label}>Name:</label>
          <input
            style={styles.input}
            type="text"
            name="name"
            value={product.name}
            onChange={handleChange}
            placeholder="Enter product name"
            required
          />
        </div>
        <div style={styles.formGroup}>
          <label style={styles.label}>Price:</label>
          <input
            style={styles.input}
            type="number"
            name="price"
            value={product.price}
            onChange={handleChange}
            placeholder="Enter product price"
            min="0"
            step="0.01"
            required
          />
        </div>
        <div style={styles.formGroup}>
          <label style={styles.label}>Quantity:</label>
          <input
            style={styles.input}
            type="number"
            name="quantity"
            value={product.quantity}
            onChange={handleChange}
            placeholder="Enter quantity"
            min="0"
            required
          />
        </div>
        <div style={styles.formGroup}>
          <label style={styles.label}>Description:</label>
          <textarea
            style={{ ...styles.input, height: '100px', resize: 'vertical' }}
            name="description"
            value={product.description}
            onChange={handleChange}
            placeholder="Enter product description"
            required
          />
        </div>
        <div style={styles.formGroup}>
          <label style={styles.label}>Image:</label>
          <input
            type="file"
            accept="image/*"
            onChange={handleImageChange}
            ref={fileInputRef}
            required={!isEditMode}
          />
        </div>
        <button type="submit" style={styles.button}>
          {isEditMode ? 'Update Product' : 'Add Product'}
        </button>
      </form>
    </div>
  );
}

const styles = {
  container: {
    maxWidth: '400px',
    margin: '40px auto',
    padding: '20px',
    border: '1px solid #ddd',
    borderRadius: '8px',
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    backgroundColor: '#f9f9f9',
  },
  title: {
    textAlign: 'center',
    color: '#333',
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '15px',
  },
  formGroup: {
    display: 'flex',
    flexDirection: 'column',
  },
  label: {
    marginBottom: '6px',
    fontWeight: '600',
    color: '#555',
  },
  input: {
    padding: '10px',
    fontSize: '1rem',
    borderRadius: '4px',
    border: '1px solid #ccc',
    outline: 'none',
    transition: 'border-color 0.3s ease',
  },
  button: {
    padding: '12px',
    backgroundColor: '#007bff',
    border: 'none',
    borderRadius: '4px',
    color: 'white',
    fontWeight: '600',
    fontSize: '1rem',
    cursor: 'pointer',
    transition: 'background-color 0.3s ease',
  },
  success: {
    color: 'green',
    fontWeight: '600',
    textAlign: 'center',
  },
  error: {
    color: 'red',
    fontWeight: '600',
    textAlign: 'center',
  },
};

export default AddProduct;
