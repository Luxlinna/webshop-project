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


webshop-project/
├── webshop/ → Spring Boot backend
├── webshop-frontend/ → React frontend
├── docker-compose.yml → To run both apps together
└── README.md


## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Luxlinna/webshop-project.git
cd webshop-project



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


Project clone : https://github.com/Luxlinna/webshop-project.git

---

## ✅ 1. Final `README.md` (Ready to Commit)

Save this as `README.md` in the root of `webshop-project`:

```markdown
# Webshop Project

A full-stack webshop application with:

- **Backend:** Java + Spring Boot (deployed on [Render](https://render.com))
- **Frontend:** Vite.js (deployed on [Vercel](https://vercel.com))
- **IDE:** Visual Studio Code

---

## 📁 Project Structure

```

webshop-project/
├── webshop/             # Java Spring Boot backend
├── webshop-frontend/    # Vite.js frontend
└── README.md            # You're reading it!

````

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- Node.js + npm
- Git
- VSCode (recommended)

---

## 🧠 Environment Setup

### Backend (Spring Boot)

1. Navigate to backend:

    ```bash
    cd webshop
    ```

2. Build the project:

    ```bash
    mvn clean package
    ```

3. Run the app:

    ```bash
    java -jar target/webshop.jar
    ```

4. The backend runs at: `http://localhost:8080`

To deploy, use [Render](https://render.com).

---

### Frontend (Vite)

1. Navigate to frontend:

    ```bash
    cd webshop-frontend
    ```

2. Create `.env` file:

    ```bash
    echo "VITE_API_URL=https://your-backend-url.onrender.com" > .env
    ```

3. Install and run:

    ```bash
    npm install
    npm run dev
    ```

4. The frontend runs at: `http://localhost:3000`

To deploy, use [Vercel](https://vercel.com).

---

## 🌐 Deployment URLs

| Service   | Platform | URL Example |
|-----------|----------|-------------|
| Backend   | Render   | `https://webshop-backend.onrender.com` |
| Frontend  | Vercel   | `https://webshop-frontend.vercel.app` |

---

## 🔐 CORS Setup (Spring Boot)

```java
@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return registry -> registry.addMapping("/**")
            .allowedOrigins("https://your-frontend-url.vercel.app")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
````

---

## ⚙️ VSCode Tips

* Open the root `webshop-project` folder in VSCode
* Use the built-in terminal for separate frontend/backend
* Install extensions:

  * Java Extension Pack
  * ESLint
  * Prettier

---

## 📝 License

MIT or your preferred license.

---

## 🙋 Contact

Created by \[Your Name] – \[[your.email@example.com](mailto:your.email@example.com)]

````

---

## ✅ 2. Commit `README.md`

```bash
git add README.md
git commit -m "Add project README"
git push
````

---

## ✅ 3. Folder Structure Tips

Make sure it looks like this:

```
webshop-project/
├── .git/
├── .gitignore
├── README.md
├── webshop/
│   ├── src/
│   ├── target/ (ignored)
│   ├── pom.xml
│   └── ...
├── webshop-frontend/
│   ├── src/
│   ├── dist/ (ignored)
│   ├── vite.config.js
│   ├── package.json
│   └── ...
```

---

You're now fully set up with:

* Clean Git repo ✅
* Frontend + backend split ✅
* Deploy to Vercel & Render ✅
* Final README ✅


