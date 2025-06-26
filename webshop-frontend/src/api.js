// src/api.js
import axios from 'axios';

const apiUrl = import.meta.env.VITE_API_URL;
const api = axios.create({
  baseURL: apiUrl,
  // baseURL: 'http://localhost:8080', // or use env variable if you want
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add or update product with FormData
export const saveProduct = async (product, imageFile, isEditMode) => {
  const formData = new FormData();
  formData.append('name', product.name);
  formData.append('price', product.price);
  formData.append('quantity', product.quantity);
  formData.append('description', product.description);
  if (imageFile) {
    formData.append('image', imageFile);
  }

  if (isEditMode) {
    return api.put(`/products/${product.id}`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });
  } else {
    return api.post('/products', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });
  }
};

export default api;









// // src/api.js
// import axios from 'axios';

// const api = axios.create({
//   baseURL: 'http://localhost:8080', // adjust if your backend is different
//   headers: {
//     'Content-Type': 'application/json',
//   },
// });

// export default api;





