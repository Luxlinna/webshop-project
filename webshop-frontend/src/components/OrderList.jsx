import React from "react";

const OrderList = ({ orders, onSelect }) => {
  const calculateTotal = (items = []) => {
    return items.reduce((sum, item) => {
      const price = item?.priceAtPurchase ?? 0;
      const quantity = item?.quantity ?? 0;
      return sum + price * quantity;
    }, 0);
  };

  return (
    <div>
      <h2 className="text-xl font-bold mb-4">Orders</h2>
      <table className="min-w-full border rounded">
        <thead>
          <tr className="bg-gray-100">
            <th className="p-3 text-left">ID</th>
            <th className="p-3 text-left">Customer</th>
            <th className="p-3 text-left">Total Amount</th>
            <th className="p-3 text-left">Status</th>
            <th className="p-3 text-left">Actions</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <li
              key={order.id}
              className="border p-4 rounded shadow cursor-pointer hover:bg-gray-100"
              onClick={() => onSelect(order)}
            >
              <p><strong>Name:</strong> {order.customer.name}</p>
              <p><strong>Email:</strong> {order.customer.email}</p>
              <p><strong>Items:</strong> {order.items.length}</p>
            </li>
          ))}
          {/* {orders.map((order, index) => (
            <tr key={index} className="border-t hover:bg-gray-50 transition">
              <td className="p-3">{order.id ?? index + 1}</td>
              <td className="p-3">{order.customer?.name ?? "No Name"}</td>
              <td className="p-3">${calculateTotal(order.items).toFixed(2)}</td>
              <td className="p-3">{order.status ?? "Processing"}</td>
              <td className="p-3">
                <button
                  className="text-blue-600 hover:underline"
                  onClick={() => onSelect(order)}
                >
                  View Details
                </button>
              </td>
            </tr>
          ))} */}
        </tbody>
      </table>
    </div>
  );
};

export default OrderList;








// import React from "react";

// const OrderList = ({ orders, onSelect }) => {
//   const calculateTotal = (items = []) => {
//     if (!Array.isArray(items)) return 0;

//     return items.reduce((sum, item) => {
//       const price = item?.priceAtPurchase ?? 0;
//       const quantity = item?.quantity ?? 0;
//       return sum + price * quantity;
//     }, 0);
//   };
//   // const calculateTotal = (items) =>
//   //   items?.reduce((sum, item) => sum + (item.priceAtPurchase || 0) * item.quantity, 0);

//   return (
//     <div>
//       <h2 className="text-xl font-bold mb-4">Orders</h2>
//       <table className="min-w-full border rounded">
//         <thead>
//           <tr className="bg-gray-100">
//             <th className="p-3 text-left">ID</th>
//             <th className="p-3 text-left">Customer</th>
//             <th className="p-3 text-left">Total Amount</th>
//             <th className="p-3 text-left">Status</th>
//             <th className="p-3 text-left">Actions</th>
//           </tr>
//         </thead>
//         <tbody>
//           {orders.map((order, index) => (
//             <tr
//               key={order.id ?? index}
//               className="border-t hover:bg-gray-50 transition"
//             >
//               <td className="p-3">{order.id ?? "N/A"}</td>
//               <td className="p-3">{order.customer?.name ?? "No Name"}</td>
//               <td className="p-3">
//                 ${calculateTotal(order.items).toFixed(2)}
//               </td>
//               <td className="p-3">{order.status ?? "Unknown"}</td>
//               <td className="p-3">
//                 <button
//                   className="text-blue-600 hover:underline"
//                   onClick={() => onSelect(order)}
//                 >
//                   View Details
//                 </button>
//               </td>
//             </tr>
//           ))}
//         </tbody>
//       </table>
//     </div>
//   );
// };

// export default OrderList;








// // src/components/OrderList.jsx
// import React from "react";

// const OrderList = ({ orders, onSelect }) => {
//   return (
//     <div>
//       <h2 className="text-xl font-bold">Orders</h2>
//       <table className="min-w-full mt-4 border rounded">
//         <thead>
//           <tr className="bg-gray-100">
//             <th className="p-3 text-left">ID</th>
//             <th className="p-3 text-left">Customer</th>
//             <th className="p-3 text-left">Total Amount</th>
//             <th className="p-3 text-left">Date</th>
//             <th className="p-3 text-left">Status</th>
//           </tr>
//         </thead>
//         <tbody>
//             {orders.map((order, index) => (
//                 <tr key={order.id ?? index}>
//                     <td>{order.id ?? "N/A"}</td>
//                     <td>{order.customer?.name ?? "No Name"}</td>
//                     <td>${order.totalAmount?.toFixed(2) ?? "0.00"}</td>
//                     <td>{order.status ?? "Unknown"}</td>
//                     <td>
//                     <button onClick={() => onSelect(order)}>View Details</button>
//                     </td>
//                 </tr>
//             ))}
//         </tbody>
//       </table>
//     </div>
//   );
// };
// export default OrderList;
