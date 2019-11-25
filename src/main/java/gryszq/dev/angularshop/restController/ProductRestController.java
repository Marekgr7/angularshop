package gryszq.dev.angularshop.restController;

import gryszq.dev.angularshop.model.Product;
import gryszq.dev.angularshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping
    public Optional<Product> findById(@RequestParam Long id){
        return productService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public void saveProduct(@RequestBody Product product){
            System.out.println(product);
            productService.save(product);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@RequestBody Product product){
        productService.save(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam Long id){
        productService.deleteById(id);
    }


}
