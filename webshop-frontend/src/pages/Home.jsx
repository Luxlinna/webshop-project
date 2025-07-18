import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AddProduct from '../components/AddProduct';

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
    </div>
  );
}
