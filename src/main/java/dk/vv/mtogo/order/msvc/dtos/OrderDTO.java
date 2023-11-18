package dk.vv.mtogo.order.msvc.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dk.vv.mtogo.order.msvc.dtos.OrderLineDTO;
import dk.vv.mtogo.order.msvc.pojos.Order;
import dk.vv.mtogo.order.msvc.pojos.OrderLine;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO {

    private int id;

    private int customerId;
    private String comment;

    @JsonManagedReference
    private Set<OrderLineDTO> orderLines = new HashSet<>();

    private int statusId;

    private BigDecimal subTotal;

    private BigDecimal total;

    private int addressId;

    private int supplierId;

    public OrderDTO(int id) {
        this.id = id;
    }


    public OrderDTO(Order order) {
        this.id = order.getId();
        this.customerId = order.getCustomerId();
        this.comment = order.getComment();
        this.statusId = order.getStatusId();
        this.subTotal = order.getSubTotal();
        this.total = order.getTotal();
        this.addressId = order.getAddressId();
        this.supplierId = order.getSupplierId();
        if(!order.getOrderLines().isEmpty()){
            for (OrderLine orderLine : order.getOrderLines()) {
                this.addOrderLine(new OrderLineDTO(orderLine));

            }
        }

    }

    public OrderDTO() {
    }

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

    public Set<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLineDTO> orderLines) {
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

    public void addOrderLine(OrderLineDTO orderLine){

        this.orderLines.add(orderLine);

        if(orderLine.getOrder()!= this){
            orderLine.setOrder(this);
        }
    }
}