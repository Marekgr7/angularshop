package gryszq.dev.angularshop.restController;

import gryszq.dev.angularshop.model.ProductList;
import gryszq.dev.angularshop.service.ProductListService;
import gryszq.dev.angularshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productlist")
public class ProductListRestController {

    private ProductListService productListService;

    @Autowired
    public ProductListRestController(ProductListService productListService) {
        this.productListService = productListService;
    }

    @PostMapping
    public void saveList(@RequestBody ProductList productList){
        productListService.save(productList);
    }
}
