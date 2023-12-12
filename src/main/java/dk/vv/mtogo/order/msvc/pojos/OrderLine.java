package dk.vv.mtogo.order.msvc.pojos;

import dk.vv.common.data.transfer.objects.order.OrderLineDTO;
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

    @Column(name = "unit_net_price")
    private BigDecimal unitNetPrice;

    @Column(name = "unit_gross_price")
    private BigDecimal unitGrossPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "sub_total")
    private BigDecimal subTotal;


    // ===== Constructors =====

    public OrderLine(OrderLineDTO orderLineDTO) throws Exception {
        this.productId = orderLineDTO.getProductId();
        this.quantity = orderLineDTO.getQuantity();
    }

    public OrderLine() {
    }

    // ===== Methods =====

    public OrderLineDTO toDTO() {
        OrderLineDTO orderLineDTO = new OrderLineDTO();
        orderLineDTO.setId(this.getId());
        orderLineDTO.setProductId(this.getProductId());
        orderLineDTO.setUnitNetPrice(this.getUnitNetPrice());
        orderLineDTO.setUnitGrossPrice(this.getUnitGrossPrice());
        orderLineDTO.setQuantity(this.getQuantity());
        orderLineDTO.setTotal(this.getTotal());
        orderLineDTO.setSubTotal(this.getSubTotal());
        return orderLineDTO;
    }

    public void createLineSubTotal() throws Exception{

        if(this.unitNetPrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Unit net price must be zero or greater");
        }else if (this.quantity < 0){
            throw new IllegalArgumentException("Quantity must be zero or greater");
        }

        this.subTotal = this.unitNetPrice.multiply(BigDecimal.valueOf(this.quantity)).setScale(2,RoundingMode.HALF_UP);

    }

    public void createLineTotal() throws Exception{

        if(this.unitGrossPrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Unit gross price must be zero or greater");
        }else if (this.quantity < 0){
            throw new IllegalArgumentException("Quantity must be zero or greater");
        }
        this.total = this.unitGrossPrice.multiply(BigDecimal.valueOf(this.quantity)).setScale(2, RoundingMode.HALF_UP);
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

    public BigDecimal getUnitNetPrice() {
        return unitNetPrice;
    }

    public void setUnitNetPrice(BigDecimal unitGrossPrice) {
        this.unitNetPrice = unitGrossPrice;
    }

    public BigDecimal getUnitGrossPrice() {
        return unitGrossPrice;
    }

    public void setUnitGrossPrice(BigDecimal unitNetPrice) {
        this.unitGrossPrice = unitNetPrice;
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
