// webshop-frontend/src/components/ProductList.jsx
import React, { useEffect, useState } from "react";
import axios from "axios";
import Cart from "./Cart";
import OrderForm from "./OrderForm";

export default function ProductList() {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState(null);
  const [editingId, setEditingId] = useState(null);
  const [formData, setFormData] = useState({
    name: "",
    price: "",
    description: "",
    quantity: "",
    imageUrl: "",
  });
  const [imageFile, setImageFile] = useState(null);
  const [cart, setCart] = useState([]);

  const fetchProducts = async () => {
    try {
      const res = await axios.get("http://localhost:8080/products");
      setProducts(res.data);
    } catch {
      setError("Failed to fetch products.");
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  
  const startEdit = (product) => {
    setEditingId(product.id);
    setFormData({
      name: product.name || "",
      price: product.price?.toString() || "",
      description: product.description || "",
      quantity: product.quantity?.toString() || "1",
      imageUrl: product.imageUrl || "",
    });
    setImageFile(null);
  };

  const cancelEdit = () => {
    setEditingId(null);
    setFormData({ name: "", price: "", description: "", imageUrl: "" });
    setImageFile(null);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleImageChange = (e) => {
    if (e.target.files.length > 0) {
      setImageFile(e.target.files[0]);
    }
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    if (!editingId) return;

    const data = new FormData();
    data.append("name", formData.name);
    data.append("price", formData.price);
    data.append("description", formData.description);
    data.append("quantity", formData.quantity);
    if (imageFile) data.append("image", imageFile);

    try {
      await axios.put(`http://localhost:8080/products/${editingId}`, data, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      await fetchProducts();
      cancelEdit();
      alert("✅ Product updated successfully!");
    } catch (err) {
      alert("❌ Failed to update product.");
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this product?")) return;

    try {
      await axios.delete(`http://localhost:8080/products/${id}`);
      setProducts((prev) => prev.filter((p) => p.id !== id));
    } catch {
      alert("Failed to delete product.");
    }
  };

  const addToCart = (product) => {
    setCart((prev) => {
      const existing = prev.find((item) => item.id === product.id);
      if (existing) {
        return prev.map((item) =>
          item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
        );
      } else {
        return [...prev, { ...product, quantity: 1 }];
      }
    });
  };


  const clearCart = () => {
    setCart([]);
  };

  if (error) return <div className="text-red-600">{error}</div>;

  return (
    <div className="p-4 pb-8">
      <h2 className="text-xl font-semibold mb-4">Products List</h2>

      <ul className="grid grid-cols-1 md:grid-cols-3 gap-4">
        {products.map((product) => (
          
          <li key={product.id} className="border rounded p-4 flex flex-col">
            {editingId === product.id ? (
              <form
                onSubmit={handleUpdate}
                encType="multipart/form-data"
                className="flex flex-col gap-2 bg-gray-200 p-4 rounded"
              >
                <input
                  type="text"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                  placeholder="Name"
                  className="border p-2 rounded"
                  required
                />
                <input
                  type="number"
                  name="price"
                  value={formData.price}
                  onChange={handleChange}
                  placeholder="Price"
                  className="border p-2 rounded"
                  required
                  min="0"
                  step="0.01"
                />
                <input
                  type="number"
                  name="quantity"
                  value={formData.quantity}
                  onChange={handleChange}
                  placeholder="Quantity"
                  className="border p-2 rounded"
                  required
                  min="0"
                />
                <textarea
                  name="description"
                  value={formData.description}
                  onChange={handleChange}
                  placeholder="Description"
                  className="border p-2 rounded"
                  required
                />
                <input type="file" accept="image/*" onChange={handleImageChange} />
                <div className="flex gap-2 mt-2">
                  <button type="submit" className="bg-green-600 text-white py-2 rounded flex-grow">
                    Save
                  </button>
                  <button type="button" onClick={cancelEdit} className="bg-gray-400 text-white py-2 rounded flex-grow">
                    Cancel
                  </button>
                </div>
              </form>
            ) : (
              <>               
                <img
                  src={`http://localhost:8080${
                    product.imageUrl?.startsWith("/") 
                      ? product.imageUrl 
                      : `/uploads/${product.imageUrl}`
                  }`}
                  alt={product.name}
                  className="w-full h-48 object-cover mb-2"
                  onError={(e) => (e.target.src = "/placeholder.png")}
                />

                <h3 className="font-semibold text-lg">{product.name}</h3>
                <p className="text-gray-600 mb-2">{product.description}</p>
                <p className="font-bold mb-2">${Number(product.price).toFixed(2)}</p>
                <div className="flex gap-2 mb-2">
                  <button
                    onClick={() => startEdit(product)}
                    className="bg-yellow-500 hover:bg-yellow-600 text-white py-2 rounded flex-grow"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => handleDelete(product.id)}
                    className="bg-red-600 hover:bg-red-700 text-white py-2 rounded flex-grow"
                  >
                    Delete
                  </button>
                </div>
                <button
                  onClick={() => addToCart(product)}
                  className="bg-blue-500 text-white px-2 py-1 rounded"
                >
                  Add to Cart
                </button>
              </>
            )}
          </li>
        ))}
      </ul>

      {/* Cart and order form */}
      <Cart cart={cart} />
      <OrderForm cart={cart} clearCart={clearCart} />
    </div>
  );
}

