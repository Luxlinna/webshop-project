import { useState } from 'react';
import { toast } from 'react-toastify';


export default function OrderForm({ cart, clearCart }) {
  const [form, setForm] = useState({ name: '', email: '', address: '' });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const apiUrl = import.meta.env.VITE_API_URL;

  const handleChange = (e) => {
    setError('');
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    if (!form.name || !form.email || !form.address) {
      setError('Please fill in all fields.');
      return false;
    }
    if (!form.email.includes('@')) {
      setError('Please enter a valid email address.');
      return false;
    }
    if (cart.length === 0) {
      setError('Your cart is empty.');
      return false;
    }
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccessMessage('');

    if (!validateForm()) {
      toast.error(error || 'Please fill in all required fields.');
      return;
    }

    setLoading(true);

    const orderData = {
      customer: {
        name: form.name,
        email: form.email,
        address: form.address,
      },
      items: cart.map((item) => ({
        productId: item.id,
        quantity: item.quantity,
      })),
    };

    try {
      const response = await fetch(`${apiUrl}/api/orders`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderData),
      });

      if (!response.ok) {
        let errorMessage = 'Order submission failed';
        const contentType = response.headers.get('Content-Type');

        if (contentType && contentType.includes('application/json')) {
          const errorData = await response.json();
          errorMessage = errorData.message || errorMessage;
        } else {
          errorMessage = await response.text();
        }

        throw new Error(errorMessage);
      }

      await response.json();
      setSuccessMessage('Your order has been placed successfully!');
      toast.success('✅ Order placed successfully!');

      // Reset cart and form
      clearCart();
      setForm({ name: '', email: '', address: '' });
      
      setTimeout(() => {
        window.location.reload();    // ✅ Refresh page after successful order
      }, 2500); // Wait 2.5 seconds
      
    } catch (error) {
      setError(error.message || 'Something went wrong.');
      toast.error(error.message || 'Something went wrong.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="max-w-md mx-auto p-6 bg-white rounded shadow-md space-y-4"
    >
      <h2 className="text-2xl font-semibold mb-4">Place Order</h2>

      {error && <div className="text-red-600 font-medium">{error}</div>}
      {successMessage && (
        <div className="text-green-600 font-medium">{successMessage}</div>
      )}

      <input
        type="text"
        name="name"
        placeholder="Name"
        value={form.name}
        onChange={handleChange}
        required
        className="w-full border border-gray-300 rounded px-3 py-2"
      />
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={form.email}
        onChange={handleChange}
        required
        className="w-full border border-gray-300 rounded px-3 py-2"
      />
      <input
        type="text"
        name="address"
        placeholder="Address"
        value={form.address}
        onChange={handleChange}
        required
        className="w-full border border-gray-300 rounded px-3 py-2"
      />
      <button
        type="submit"
        disabled={loading || cart.length === 0}
        className={`w-full py-2 rounded text-white font-semibold transition-colors ${
          loading || cart.length === 0
            ? 'bg-gray-400 cursor-not-allowed'
            : 'bg-blue-600 hover:bg-blue-700'
        }`}
      >
        {loading ? 'Placing Order...' : 'Send Order'}
      </button>
    </form>
  );
}

