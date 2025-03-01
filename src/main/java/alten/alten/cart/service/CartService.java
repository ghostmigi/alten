package alten.alten.cart.service;

import alten.alten.cart.model.Cart;
import alten.alten.cart.repository.CartRepository;
import alten.alten.product.model.Product;
import alten.alten.product.repository.ProductRepository;
import alten.alten.user.model.User;
import alten.alten.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get cart by user ID
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No cart found for user with ID: " + userId));
    }

    // Create a new cart
    public Cart createCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        if (cartRepository.existsByUserId(userId)) {
            throw new RuntimeException("Cart already exists for user with ID: " + userId);
        }

        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    // Add product to cart
    public Cart addProductToCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    // Remove product from cart
    public Cart removeProductFromCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        cart.getProducts().remove(product);
        return cartRepository.save(cart);
    }

    // Clear cart
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.getProducts().clear();
        cartRepository.save(cart);
    }
}
