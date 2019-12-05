package gryszq.dev.angularshop.repository;


import gryszq.dev.angularshop.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductListRepository extends JpaRepository<ProductList,Long> {
}
