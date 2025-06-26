// src/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', // adjust if your backend is different
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;







// import axios from "axios";

// const api = axios.create({
//   baseURL: "http://localhost:8080"
// });

// export default api;

