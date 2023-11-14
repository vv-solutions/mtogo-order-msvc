package dk.vv.mtogo.order.msvc.pojos;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Column(name = "order_lines")
    private Set<OrderLine> orderLines;

    @Column(name = "status_id")
    private int statusId;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "address_id")
    private int addressId;


    @Column(name = "supplier_id")
    private int supplierId;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_stamp")
    private LocalDateTime createStamp;


    // ===== Constructors =====
    public Order() {

    }

    // ===== Methods =====

    //firstBigDecimal.compareTo(secondBigDecimal) < 0 // "<"
    //firstBigDecimal.compareTo(secondBigDecimal) > 0 // ">"
    //firstBigDecimal.compareTo(secondBigDecimal) == 0 // "=="
    //firstBigDecimal.compareTo(secondBigDecimal) >= 0 // ">="
    public BigDecimal createFee() {
        if (this.getTotal().compareTo(BigDecimal.valueOf(101)) < 0 && this.getTotal().compareTo(BigDecimal.valueOf(75)) >= 0) {
            return this.subTotal.multiply(BigDecimal.valueOf(0.06)).setScale(2, RoundingMode.HALF_UP);
        }
        if (this.getTotal().compareTo(BigDecimal.valueOf(100.99)) >= 0 && this.total.compareTo(BigDecimal.valueOf(501)) < 0) {
            return this.subTotal.multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_UP);
        }
        if (this.getTotal().compareTo(BigDecimal.valueOf(500.99)) > 0 && this.total.compareTo(BigDecimal.valueOf(1000.01)) < 0){
            return this.subTotal.multiply(BigDecimal.valueOf(0.04)).setScale(2, RoundingMode.HALF_UP);
        }
        if(this.getTotal().compareTo(BigDecimal.valueOf(1000)) > 0 ){
            return this.subTotal.multiply(BigDecimal.valueOf(0.03)).setScale(2, RoundingMode.HALF_UP);
        }

        throw new IllegalArgumentException("Total must be greater than 74.99");
    }


    public void createSubTotal(){
        BigDecimal tempSubTotal = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLines) {
            if(orderLine.getSubTotal().compareTo(BigDecimal.ZERO) < 0){
                throw new IllegalArgumentException("Subtotals must be greater than zero");
            }
            tempSubTotal = tempSubTotal.add(orderLine.getSubTotal());
        }

        this.subTotal = tempSubTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public void createTotal(){
        BigDecimal tempTotal = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLines) {
            if(orderLine.getTotal().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Totals must be greater than zero");
            }
            tempTotal = tempTotal.add(orderLine.getTotal());
        }
        this.total = tempTotal.setScale(2, RoundingMode.HALF_UP);

    }

    // ===== Getters and Setters =====


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDateTime getCreateStamp() {
        return createStamp;
    }

    public void setCreateStamp(LocalDateTime createStamp) {
        this.createStamp = createStamp;
    }


}