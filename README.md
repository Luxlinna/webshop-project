# 🛒 Webshop Full Stack Application
==========================
### Overview
- Code build in VSCode

This is a full-stack webshop application built with:
- **Spring Boot (Java 17)** as the backend
- **React Vite (JavaScript)** as the frontend
- **Maven** for build management
- **Docker** for containerization

---

## 📁 Project Structure


webshop/
├── webshop/ → Spring Boot backend
├── webshop-frontend/ → React frontend
├── docker-compose.yml → To run both apps together
└── README.md


## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Luxlinna/webshop.git
cd webshop



========================

# RUN BACKEND:
cd webshop
mvn spring-boot:run

App will run at: http://localhost:8080


API End points:
API Endpoints
Method	Endpoint	Description
GET	/products	Get all products
POST	/products	Create a product
GET	/orders	Get all orders
POST	/orders	Create an order





# RUN FRONTEND
cd webshop-frontend
- npm install
- npm install react-toastify // In case you don't have it installed
- npm run dev

App will run at: http://localhost:3000


# Axios is pre-configured to connect to backend
src/services/api.js

baseURL: 'http://localhost:8080'


# Docker Support
docker-compose up --build
Run full app with Docker Compose

React: http://localhost:5173

Spring Boot: http://localhost:8080




