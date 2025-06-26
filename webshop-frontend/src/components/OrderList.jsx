// src/components/OrderList.jsx
import React from "react";

const OrderList = ({ orders, onSelect }) => {
  return (
    <div>
      <h2 className="text-xl font-bold">Orders</h2>
      <table className="min-w-full mt-4 border rounded">
        <thead>
          <tr className="bg-gray-100">
            <th className="p-3 text-left">ID</th>
            <th className="p-3 text-left">Customer</th>
            <th className="p-3 text-left">Total Amount</th>
            <th className="p-3 text-left">Date</th>
            <th className="p-3 text-left">Status</th>
          </tr>
        </thead>
        <tbody>
            {orders.map((order, index) => (
                <tr key={order.id ?? index}>
                    <td>{order.id ?? "N/A"}</td>
                    <td>{order.customer?.name ?? "No Name"}</td>
                    <td>${order.totalAmount?.toFixed(2) ?? "0.00"}</td>
                    <td>{order.status ?? "Unknown"}</td>
                    <td>
                    <button onClick={() => onSelect(order)}>View Details</button>
                    </td>
                </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
};
export default OrderList;
