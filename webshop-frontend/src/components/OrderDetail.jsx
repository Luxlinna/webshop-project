// src/components/OrderDetail.jsx
import React from "react";

const OrderDetail = ({ order, onBack }) => {
  if (!order) {
    return <div className="p-4">Select an order to view details</div>;
  }

  return (
    <div className="p-4">
      <button
        className="mb-4 rounded bg-gray-300 px-3 py-1"
        onClick={onBack}
      >
        ← Back
      </button>
      <h2 className="text-2xl font-bold">Order #{order.id}</h2>
      <div className="mt-2">
        <h3 className="font-semibold">Customer Info</h3>
        <p>Name: {order.customer?.name}</p>
        <p>Email: {order.customer?.email}</p>
        <p>Address: {order.customer?.address}</p>
      </div>
      <div className="mt-4">
        <h3 className="font-semibold">Items</h3>
        <ul className="list-disc pl-5">
          {order.items?.map((item, index) => (
            <li key={index}>
              Product ID: {item.productId}, Quantity: {item.quantity}
            </li>
          ))}
        </ul>
      </div>
      <div className="mt-4">
        <p>Total Amount: ${order.totalAmount?.toFixed(2)}</p>
        <p>Status: {order.status}</p>
        <p>Date: {new Date(order.orderDate).toLocaleString()}</p>
      </div>
    </div>
  );
};

export default OrderDetail;
