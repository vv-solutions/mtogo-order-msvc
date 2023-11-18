package dk.vv.mtogo.order.msvc.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dk.vv.mtogo.order.msvc.pojos.Order;
import dk.vv.mtogo.order.msvc.pojos.OrderLine;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class OrderLineDTO {
    private int id;

    @JsonBackReference
    private OrderDTO order;

    private int productId;

    private BigDecimal unitNetPrice;

    private BigDecimal unitGrossPrice;

    private int quantity;

    private BigDecimal total;

    private BigDecimal subTotal;

    public OrderLineDTO() {
    }

    public OrderLineDTO(OrderLine orderLine) {
        this.id = orderLine.getId();
        this.productId = orderLine.getProductId();
        this.unitNetPrice = orderLine.getUnitNetPrice();
        this.unitGrossPrice = orderLine.getUnitGrossPrice();
        this.quantity = orderLine.getQuantity();
        this.total = orderLine.getTotal();
        this.subTotal = orderLine.getSubTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
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

    public void setUnitNetPrice(BigDecimal unitNetPrice) {
        this.unitNetPrice = unitNetPrice;
    }

    public BigDecimal getUnitGrossPrice() {
        return unitGrossPrice;
    }

    public void setUnitGrossPrice(BigDecimal unitGrossPrice) {
        this.unitGrossPrice = unitGrossPrice;
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

    @Override
    public String toString() {
        return "OrderLineDTO{" +
                "id=" + id +
                ", order=" + order +
                ", productId=" + productId +
                ", unitNetPrice=" + unitNetPrice +
                ", unitGrossPrice=" + unitGrossPrice +
                ", quantity=" + quantity +
                ", total=" + total +
                ", subTotal=" + subTotal +
                '}';
    }
}
