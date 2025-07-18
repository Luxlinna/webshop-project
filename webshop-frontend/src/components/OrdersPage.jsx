// src/pages/OrdersPage.jsx
import React, { useState, useEffect } from "react";
import OrderDetail from "../components/OrderDetail";
import OrderList from "../components/OrderList";


export const OrdersPage = () => {
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);

  useEffect(() => {
    async function fetchOrders() {
      try {
        const res = await fetch("http://localhost:8080/orders");
        const data = await res.json();
        setOrders(data);
      } catch (error) {
        console.error("Failed to fetch orders:", error);
      }
    }

    fetchOrders(); // ✅ Fixed: no `.then(setOrders)`
  }, []);

  return (
    <div className="p-4">
      {selectedOrder ? (
        <OrderDetail
          order={selectedOrder}
          onBack={() => setSelectedOrder(null)}
        />
      ) : (
        
        <OrderList
          orders={orders}
          onSelect={(order) => setSelectedOrder(order)}
        />
      )}
    </div>
  );
};









// // src/pages/OrdersPage.jsx
// import React, { useState, useEffect } from "react";
// import OrderList from "../components/OrderList";
// import OrderDetail from "../components/OrderDetail";

// export const OrdersPage = ({ order }) => {
//   // if (!order) return <p>Loading ...</p>;

//   const [orders, setOrders] = useState([]);
//   const [selectedOrder, setSelectedOrder] = useState(null);

//   useEffect(() => {
//     async function fetchOrders() {
//       const res = await fetch("http://localhost:8080/api/orders"); // Adjust endpoint
//       const data = await res.json();
//       setOrders(data);
//     }
//     fetchOrders();
//   }, []);
//   console.log("Fetched orders:", orders);

//   return (
//     <div className="p-4">
//       {selectedOrder ? (
//         <OrderDetail
//           order={selectedOrder}
//           onBack={() => setSelectedOrder(null)}
//         />
//       ) : (
//         <OrderList
//           orders={orders}
//           onSelect={(order) => setSelectedOrder(order)}
//         />
//       )}
//     </div>
//   );
// };
