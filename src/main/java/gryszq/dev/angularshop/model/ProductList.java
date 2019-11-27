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

    private int qty;
}
