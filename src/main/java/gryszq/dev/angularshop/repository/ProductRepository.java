package gryszq.dev.angularshop.repository;

import gryszq.dev.angularshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
