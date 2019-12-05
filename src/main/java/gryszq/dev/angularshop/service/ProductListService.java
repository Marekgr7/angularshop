package gryszq.dev.angularshop.service;

import gryszq.dev.angularshop.model.ProductList;
import gryszq.dev.angularshop.repository.ProductListRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter @Setter
@Service
public class ProductListService {

    private ProductListRepository productListRepository;

    @Autowired
    public ProductListService(ProductListRepository productListRepository) {
        this.productListRepository = productListRepository;
    }

    public void save(ProductList productList){
        productListRepository.save(productList);
    }

}
