package gryszq.dev.angularshop.model;

import lombok.Getter;
import lombok.Setter;


public class Cart {

    private String name;

    private Long id;

    private Long qty;

    private Long price;

    public Cart() {
    }

    public Cart(String name, Long id, Long qty, Long price) {
        this.name = name;
        this.id = id;
        this.qty = qty;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", qty=" + qty +
                '}';
    }
}
