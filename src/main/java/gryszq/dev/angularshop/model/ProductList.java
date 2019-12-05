package gryszq.dev.angularshop.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "product_list")
@IdClass(ProductListPK.class)
public class ProductList implements Serializable {

    @Id
    @ManyToOne
    private Order orderId;

    @Id
    @ManyToOne
    private Product productId;

    private Long qty;

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}

