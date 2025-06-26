import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AddProduct from '../components/AddProduct';
import ProductList from '../components/ProductList';
import Cart from '../components/Cart';
import OrderForm from '../components/OrderForm';

export default function Home() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [cart, setCart] = useState([]);

  // 🔄 Fetch products from backend
  const refreshProducts = () => {
    axios.get('http://localhost:8080/products')
      .then(res => setProducts(res.data))
      .catch(err => console.error('Failed to fetch products:', err));
  };

  useEffect(() => {
    refreshProducts();
  }, []);

  // ➕ Add to cart
  const addToCart = (product) => {
    setCart(prev => {
      const existing = prev.find(p => p.id === product.id);
      if (existing) {
        return prev.map(p =>
          p.id === product.id ? { ...p, quantity: p.quantity + 1 } : p
        );
      }
      return [...prev, { ...product, quantity: 1 }];
    });
  };

  const clearCart = () => setCart([]);

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Welcome to Our Webshop</h1>
      <p className="mb-4">Browse our products, manage inventory, and shop!</p>

      {/* Add/Edit Product */}
      <AddProduct
        productToEdit={selectedProduct}
        onProductSaved={() => {
          refreshProducts();
          setSelectedProduct(null);
        }}
      />

      {/* Product List */}
      {/* <h2 className="text-xl font-semibold mt-8 mb-4">Product List</h2>
      <ul>
        {products.map(product => (
          <li key={product.id} className="mb-2">
            <strong>{product.name}</strong> - ${product.price}
            <div className="space-x-2 mt-1">
              <button
                className="bg-blue-500 text-white px-2 py-1 rounded"
                onClick={() => addToCart(product)}
              >
                Add to Cart
              </button>
              <button
                className="bg-yellow-500 text-white px-2 py-1 rounded"
                onClick={() => setSelectedProduct(product)}
              >
                Edit
              </button>
            </div>
          </li>
        ))}
      </ul> */}

      {/* Cart and Order */}
      {/* <Cart cart={cart} />
      <OrderForm cart={cart} clearCart={clearCart} /> */}
    </div>
  );
}
