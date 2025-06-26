package com.example.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}











// package com.example.webshop.exception;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestControllerAdvice

// public class GlobalExceptionHandler {

//     @ExceptionHandler(ProductNotFoundException.class)
//     public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
//         return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
//     }

//     @ExceptionHandler(IllegalArgumentException.class)
//     public ResponseEntity<?> handleBadRequest(IllegalArgumentException ex) {
//         return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
//     }
// }
