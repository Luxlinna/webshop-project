package com.example.webshop.controller;

import com.example.webshop.model.Product;
import com.example.webshop.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Value("${upload.dir}")
    private String uploadDir;

    @PostConstruct
    public void initUploadDir() throws IOException {
        Files.createDirectories(Paths.get(uploadDir));
    }

    // Upload utility
    private String saveImage(MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir, filename);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/" + filename;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Integer quantity,
            @RequestParam String description,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        // Validation
        if (name == null || name.trim().isEmpty()) return ResponseEntity.badRequest().body("❌ Name is required");
        if (description == null || description.trim().isEmpty()) return ResponseEntity.badRequest().body("❌ Description is required");
        if (price == null || price <= 0) return ResponseEntity.badRequest().body("❌ Price must be greater than 0");
        if (quantity == null || quantity < 0) return ResponseEntity.badRequest().body("❌ Stock cannot be negative");

        try {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setQuantity(quantity);

            // Handle image upload
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = saveImage(imageFile);
                product.setImageUrl(imageUrl);
            }
            
            return ResponseEntity.ok(productRepo.save(product));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("❌ Failed to save image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Failed to create product: " + e.getMessage());
        }
    }

    // READ all
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // READ by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
        @PathVariable Long id,
        @RequestParam("name") String name,
        @RequestParam("price") Double price,
        @RequestParam("description") String description,
        @RequestParam("quantity") Integer quantity,
        @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        if (name == null || name.isEmpty())
            return ResponseEntity.badRequest().body("❌ Name is required");
        if (description == null || description.isEmpty())
            return ResponseEntity.badRequest().body("❌ Description is required");
        if (price == null || price <= 0)
            return ResponseEntity.badRequest().body("❌ Price must be greater than 0");
        if (quantity == null || quantity < 0)
            return ResponseEntity.badRequest().body("❌ Quantity must be >= 0");

        return productRepo.findById(id).map(product -> {
            try {
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setQuantity(quantity);

                if (image != null && !image.isEmpty()) {
                    String imageUrl = saveImage(image);
                    product.setImageUrl(imageUrl);
                }

                return ResponseEntity.ok(productRepo.save(product));
            } catch (IOException e) {
                return ResponseEntity.status(500).body("❌ Failed to save image: " + e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(500).body("❌ Failed to update product: " + e.getMessage());
            }
        }).orElse(ResponseEntity.notFound().build());
    }


    // PARTIAL UPDATE (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProduct(@PathVariable Long id, @RequestBody Product updated) {
        return productRepo.findById(id).map(product -> {
            
            if (updated.getName() != null) product.setName(updated.getName());
            if (updated.getDescription() != null) product.setDescription(updated.getDescription());
            if (updated.getPrice() != null) product.setPrice(updated.getPrice());
            if (updated.getImageUrl() != null) product.setImageUrl(updated.getImageUrl());
            if (updated.getQuantity() != null) product.setQuantity(updated.getQuantity());
            
            try {
                return ResponseEntity.ok(productRepo.save(product));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("❌ Failed to patch product: " + e.getMessage());
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE IMAGE (Multipart upload) 
    @PostMapping("/{id}/image")
    public ResponseEntity<?> updateProductImage(@PathVariable Long id, @RequestParam("image") MultipartFile imageFile) {
        if (imageFile == null || imageFile.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Image file is required");
        }

        return productRepo.findById(id).map(product -> {
            try {
                product.setImageUrl(saveImage(imageFile));
                return ResponseEntity.ok(productRepo.save(product));
            } catch (IOException e) {
                return ResponseEntity.status(500).body("❌ Failed to save image: " + e.getMessage());
            }
        }).orElse(ResponseEntity.notFound().build());
    }
}
