package dk.vv.mtogo.order.msvc.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "Product")
public class ProductDTO {

    private int id;

    private String productName;

    // Pre vat
    private BigDecimal grossPrice;

    // Post vat
    private BigDecimal netPrice;

    private String description;

    private int supplierId;



    public ProductDTO() {
    }

    public ProductDTO(int id, String productName, BigDecimal grossPrice, BigDecimal netPrice, String description, int supplierId) {
        this.id = id;
        this.productName = productName;
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
        this.description = description;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
