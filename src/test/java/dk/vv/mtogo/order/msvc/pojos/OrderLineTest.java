package dk.vv.mtogo.order.msvc.pojos;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@QuarkusTest
public class OrderLineTest {

    //===== Calculate line total =====
    @Test
    void when_gross_price_is_10_and_quantity_is_2_then_the_order_line_total_should_be_20() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(10.00));
        // ACT
        orderLine.createLineTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(20.00).setScale(2), orderLine.getTotal());
    }


    @Test
    void when_gross_price_is_50_and_quantity_is_2_then_the_order_line_total_should_be_100() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(50.00));
        // ACT
        orderLine.createLineTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(100.00).setScale(2), orderLine.getTotal());
    }


    @Test
    void when_gross_price_is_10_point_70_and_quantity_is_2_then_the_order_line_total_should_be_21_point_40() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(10.70));
        // ACT
        orderLine.createLineTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(21.40).setScale(2), orderLine.getTotal());
    }


    @Test
    void when_gross_price_is_50_point_30_and_quantity_is_9_then_the_order_line_total_should_be_452_point_70() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(9);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(50.30));
        // ACT
        orderLine.createLineTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(452.70).setScale(2), orderLine.getTotal());
    }

    @Test
    void when_gross_price_is_minus_10_point_70_and_quantity_is_2_then_an_error_should_be_thrown() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(-10.70));
        Exception exception= null;
        // ACT
        try {
            orderLine.createLineTotal();
        } catch (Exception e){
            exception = e;
        }
        // ASSERT
        Assertions.assertEquals("Unit gross price must be zero or greater", exception.getMessage());
    }

    @Test
    void when_gross_price_is_10_point_70_and_quantity_is_minus_2_then_an_error_should_be_thrown() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(-2);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(10.70));
        Exception exception= null;
        // ACT
        try {
            orderLine.createLineTotal();
        } catch (Exception e){
            exception = e;
        }
        // ASSERT
        Assertions.assertEquals("Quantity must be zero or greater", exception.getMessage());
    }


    @Test
    void when_gross_price_is_0_and_quantity_is_2_then_the_order_line_total_should_be_0() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(0.00));
        // ACT
        orderLine.createLineTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), orderLine.getTotal());
    }

    @Test
    void when_gross_price_is_10_point_70_and_quantity_is_0_then_the_order_line_total_should_be_0() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(0);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(10.70));
        // ACT
        orderLine.createLineTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), orderLine.getTotal());
    }

    @Test
    void when_gross_price_is_0_and_quantity_is_0_then_the_order_line_total_should_be_0() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(0);
        orderLine.setUnitGrossPrice(BigDecimal.valueOf(0.00));
        // ACT
        orderLine.createLineTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), orderLine.getTotal());
    }

    //===== Calculate line subtotal ====

    @Test
    void when_net_price_is_10_and_quantity_is_2_then_the_order_line_subtotal_should_be_20() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(10.00));
        // ACT
        orderLine.createLineSubTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(20.00).setScale(2), orderLine.getSubTotal());
    }

    @Test
    void when_net_price_is_50_and_quantity_is_2_then_the_order_line_subtotal_should_be_100() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(50.00));
        // ACT
        orderLine.createLineSubTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(100.00).setScale(2), orderLine.getSubTotal());
    }

    @Test
    void when_net_price_is_10_point_70_and_quantity_is_2_then_the_order_line_subtotal_should_be_21_point_40() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(10.70));
        // ACT
        orderLine.createLineSubTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(21.40).setScale(2), orderLine.getSubTotal());
    }


    @Test
    void when_net_price_is_50_point_30_and_quantity_is_9_then_the_order_line_subtotal_should_be_452_point_70() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(9);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(50.30));
        // ACT
        orderLine.createLineSubTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(452.70).setScale(2), orderLine.getSubTotal());
    }


    @Test
    void when_net_price_is_minus_10_point_70_and_quantity_is_2_then_an_error_should_be_thrown() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(-10.70));
        Exception exception= null;
        // ACT
        try {
            orderLine.createLineSubTotal();
        } catch (Exception e){
            exception = e;
        }
        // ASSERT
        Assertions.assertEquals("Unit net price must be zero or greater", exception.getMessage());
    }


    @Test
    void when_net_price_is_10_point_70_and_quantity_is_minus_2_then_an_error_should_be_thrown() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(-2);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(10.70));
        Exception exception= null;
        // ACT
        try {
            orderLine.createLineSubTotal();
        } catch (Exception e){
            exception = e;
        }
        // ASSERT
        Assertions.assertEquals("Quantity must be zero or greater", exception.getMessage());
    }


    @Test
    void when_net_price_is_0_and_quantity_is_2_then_the_order_line_subtotal_should_be_0() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(0.00));
        // ACT
        orderLine.createLineSubTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), orderLine.getSubTotal());
    }


    @Test
    void when_net_price_is_10_point_7_and_quantity_is_0_then_the_order_line_subtotal_should_be_0() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(0);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(10.70));
        // ACT
        orderLine.createLineSubTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), orderLine.getSubTotal());
    }


    @Test
    void when_net_price_is_0_and_quantity_is_0_then_the_order_line_subtotal_should_be_0() throws Exception {
        // ARRANGE
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(0);
        orderLine.setUnitNetPrice(BigDecimal.valueOf(0));
        // ACT
        orderLine.createLineSubTotal();
        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), orderLine.getSubTotal());
    }




}
