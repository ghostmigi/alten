package alten.alten.product.service;

import alten.alten.product.dto.ProductRequest;
import alten.alten.product.model.Product;
import alten.alten.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository ;

    // Get all products
    public List<ProductRequest> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductRequest)
                .collect(Collectors.toList());
    }

    // Get product by ID
    public ProductRequest getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return mapToProductRequest(product);
    }

    private ProductRequest mapToProductRequest(Product product) {
        ProductRequest request = new ProductRequest();
        request.setId(product.getId());
        request.setCode(product.getCode());
        request.setName(product.getName());
        request.setDescription(product.getDescription());
        request.setImage(product.getImage());
        request.setCategory(product.getCategory());
        request.setPrice(product.getPrice());
        request.setQuantity(product.getQuantity());
        request.setInternalReference(product.getInternalReference());
        request.setShellId(product.getShellId());
        request.setInventoryStatus(product.getInventoryStatus());
        request.setRating(product.getRating());
        request.setCreatedAt(product.getCreatedAt());
        return request;
    }

    // Create a new product
    public ProductRequest createProduct(ProductRequest productRequest) {
        Product product = mapToProductEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        return mapToProductRequest(savedProduct);
    }

    private Product mapToProductEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setCode(productRequest.getCode());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setImage(productRequest.getImage());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setInternalReference(productRequest.getInternalReference());
        product.setShellId(productRequest.getShellId());
        product.setInventoryStatus(productRequest.getInventoryStatus());
        product.setRating(productRequest.getRating());
        product.setCreatedAt(productRequest.getCreatedAt());
        return product;
    }

    // Update product
    public ProductRequest updateProduct(Long productId, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        existingProduct.setCode(productRequest.getCode());
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setImage(productRequest.getImage());
        existingProduct.setCategory(productRequest.getCategory());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setQuantity(productRequest.getQuantity());
        existingProduct.setInternalReference(productRequest.getInternalReference());
        existingProduct.setShellId(productRequest.getShellId());
        existingProduct.setInventoryStatus(productRequest.getInventoryStatus());
        existingProduct.setRating(productRequest.getRating());
        existingProduct.setCreatedAt(productRequest.getCreatedAt());

        Product updatedProduct = productRepository.save(existingProduct);

        return mapToProductRequest(updatedProduct);
    }

    // Delete product
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        productRepository.delete(product);
    }
}
