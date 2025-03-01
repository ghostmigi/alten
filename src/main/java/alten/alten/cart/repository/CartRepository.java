package alten.alten.cart.repository;

import alten.alten.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByUserId(Long userId);
    Optional<Cart> findByUserId(Long userId);
}
