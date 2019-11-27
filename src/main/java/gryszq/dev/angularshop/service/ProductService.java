package gryszq.dev.angularshop.service;

import gryszq.dev.angularshop.model.Product;
import gryszq.dev.angularshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void save(Product product){
      productRepository.save(product);
    }

    public void deleteById(Long id){

        System.out.println(id);

        productRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        productRepository.save(new Product("jablo",10,"prosto z polski",15));
        productRepository.save(new Product("pomarancza",15,"swieże z hiszpanii",4));
    }
}
