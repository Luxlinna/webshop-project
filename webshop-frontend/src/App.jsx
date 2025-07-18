// src/App.jsx
import { OrdersPage } from './components/OrdersPage';
import ProductList from './components/ProductList';
import Home from './pages/Home';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function App() {
  return (
    <div className="mx-auto p-4 max-w-4xl bg-white shadow-lg rounded-lg m-lg-4">
      <h1 className="text-3xl font-bold text-green-500 mb-5">🛍️ Webshop</h1>
      <Home />
      <ProductList />
      <OrdersPage />
      <footer className="mt-8 text-center text-gray-600">
        <p>&copy; 2025 Webshop. All rights reserved.</p>
      </footer>
      <p className="text-sm text-gray-500 mt-2">
        Built by Linna Yinloch, Företagsuniversitetet
      </p>
      <p className="text-sm text-gray-500 mt-2">
        <a href="https://github.com/Luxlinna?tab=repositories
        " className="text-blue-500 hover:text-blue-700">
          Visit our GitHub for more projects
        </a>
      </p>
      <ToastContainer position="top-right" autoClose={5000} hideProgressBar={false} closeOnClick pauseOnHover draggable />
    </div>
  );
}
