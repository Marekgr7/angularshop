package gryszq.dev.angularshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double weight;

    private String description;

    private double price;

    public Products(){};

    public Products(String name, double weight, String description, double price) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.price = price;
    }
}
