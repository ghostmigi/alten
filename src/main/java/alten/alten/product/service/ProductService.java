package alten.alten.product.service;

import alten.alten.product.dto.ProductRequest;
import alten.alten.product.model.Product;
import alten.alten.product.repository.ProductRepository;
import alten.alten.user.model.User;
import alten.alten.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository ;

    @Autowired
    private UserRepository userRepository ;

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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        Product product = mapToProductEntity(productRequest);
        product.setUser(user);

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

    // Patch request
    public ProductRequest updateProductPartial(Long productId, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        if (productRequest.getCode() != null) existingProduct.setCode(productRequest.getCode());
        if (productRequest.getName() != null) existingProduct.setName(productRequest.getName());
        if (productRequest.getDescription() != null) existingProduct.setDescription(productRequest.getDescription());
        if (productRequest.getImage() != null) existingProduct.setImage(productRequest.getImage());
        if (productRequest.getCategory() != null) existingProduct.setCategory(productRequest.getCategory());
        if (productRequest.getPrice() != null) existingProduct.setPrice(productRequest.getPrice());
        if (productRequest.getQuantity() != null) existingProduct.setQuantity(productRequest.getQuantity());
        if (productRequest.getInternalReference() != null) existingProduct.setInternalReference(productRequest.getInternalReference());
        if (productRequest.getShellId() != null) existingProduct.setShellId(productRequest.getShellId());
        if (productRequest.getInventoryStatus() != null) existingProduct.setInventoryStatus(productRequest.getInventoryStatus());
        if (productRequest.getRating() != null) existingProduct.setRating(productRequest.getRating());
        if (productRequest.getCreatedAt() != null) existingProduct.setCreatedAt(productRequest.getCreatedAt());

        Product updatedProduct = productRepository.save(existingProduct);

        return mapToProductRequest(updatedProduct);
    }
}
