package dk.vv.mtogo.order.msvc.pojos;

import io.cucumber.java.an.E;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "unit_gross_price")
    private BigDecimal unitGrossPrice;

    @Column(name = "unit_net_price")
    private BigDecimal unitNetPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "sub_total")
    private BigDecimal subTotal;


    // ===== Constructors =====

    // ===== Methods =====

    public void createLineSubTotal() throws Exception{

        if(this.unitGrossPrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Unit gross price must be zero or greater");
        }else if (this.quantity < 0){
            throw new IllegalArgumentException("Quantity must be zero or greater");
        }

        this.subTotal = this.unitGrossPrice.multiply(BigDecimal.valueOf(this.quantity)).setScale(2,RoundingMode.HALF_UP);

    }

    public void createLineTotal() throws Exception{

        if(this.unitNetPrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Unit net price must be zero or greater");
        }else if (this.quantity < 0){
            throw new IllegalArgumentException("Quantity must be zero or greater");
        }
        this.total = this.unitNetPrice.multiply(BigDecimal.valueOf(this.quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    // ===== Geters and Setter =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getUnitGrossPrice() {
        return unitGrossPrice;
    }

    public void setUnitGrossPrice(BigDecimal unitGrossPrice) {
        this.unitGrossPrice = unitGrossPrice;
    }

    public BigDecimal getUnitNetPrice() {
        return unitNetPrice;
    }

    public void setUnitNetPrice(BigDecimal unitNetPrice) {
        this.unitNetPrice = unitNetPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
