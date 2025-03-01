package alten.alten.product.controller;

import alten.alten.product.dto.ProductRequest;
import alten.alten.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductRequest>> getAllProducts() {
        List<ProductRequest> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductRequest> getProductById(@PathVariable Long id) {
        ProductRequest product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Add product
    @PostMapping
    public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest productRequest) {
        ProductRequest createdProduct = productService.createProduct(productRequest);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<ProductRequest> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody ProductRequest productRequest) {
        ProductRequest updatedProduct = productService.updateProduct(id, productRequest);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.NO_CONTENT);
    }

    // Patch product
    @PatchMapping("/{id}")
    public ResponseEntity<ProductRequest> updateProductPartial(
            @PathVariable("id") Long id,
            @RequestBody ProductRequest productRequest) {
        ProductRequest updatedProduct = productService.updateProductPartial(id, productRequest);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
