package gryszq.dev.angularshop.model;

public class UserCart {

    private Long id;

    private String name;

    private Double weight;

    private Long qty;

    private double price;

    public UserCart(Long id, String name, Double weight, Long qty, Double price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.qty = qty;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
