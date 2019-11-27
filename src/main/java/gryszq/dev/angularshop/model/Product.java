package gryszq.dev.angularshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private double weight;

    private String description;

    private double price;

    public Product(){};

    public Product(String name, double weight, String description, double price) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.price = price;
    }
}
