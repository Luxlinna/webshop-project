// src/components/Cart.jsx
import React from "react";

export default function Cart({ cart }) {
  if (cart.length === 0) {
    return <p className="mt-4 text-gray-500">🛒 Your cart is empty.</p>;
  }

  const total = cart.reduce((sum, item) => sum + Number(item.price), 0);

  return (
    <div className="mt-8">
      <h2 className="text-xl font-semibold mb-2">Cart</h2>
      <ul className="mb-2">
        {cart.map((item, index) => (
          <li key={index} className="mb-1">
            {item.name} - ${Number(item.price).toFixed(2)}
          </li>
        ))}
      </ul>
      <p className="font-bold">Total: ${total.toFixed(2)}</p>
    </div>
  );
}


