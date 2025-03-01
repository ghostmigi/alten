package alten.alten.cart.controller;

import alten.alten.cart.model.Cart;
import alten.alten.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get cart by userId
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    // Create cart for a user
    @PostMapping("/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId) {
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.ok(cart);
    }

    // Add product to cart
    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long userId, @PathVariable Long productId) {
        Cart cart = cartService.addProductToCart(userId, productId);
        return ResponseEntity.ok(cart);
    }

    // Remove product from cart
    @PostMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        Cart cart = cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(cart);
    }

    // Clear the cart for a user
    @PostMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
