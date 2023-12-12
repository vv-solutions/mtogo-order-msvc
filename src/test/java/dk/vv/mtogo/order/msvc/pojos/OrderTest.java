package dk.vv.mtogo.order.msvc.pojos;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@QuarkusTest
public class OrderTest {

    // ===== Calculate fee Test =====

    @Test
    void when_order_total_is_100_and_order_subtotal_is_80_then_fee_should_be_4_point_80() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(100.00));
        order.setSubTotal(BigDecimal.valueOf(80.00));

        //ACT
        BigDecimal fee = order.createFee();

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(4.80).setScale(2), fee);
    }

    @Test
    void when_order_total_is_100_point_99_and_order_subtotal_is_80_point_79_then_fee_should_be_4_point_85() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(100.99));
        order.setSubTotal(BigDecimal.valueOf(80.79));

        //ACT
        BigDecimal fee = order.createFee();

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(4.85).setScale(2), fee);
    }


    @Test
    void when_order_total_is_75_and_order_subtotal_is_60_then_fee_should_be_3_point_60() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(75.00));
        order.setSubTotal(BigDecimal.valueOf(60.00));

        //ACT
        BigDecimal fee = order.createFee();

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(3.60).setScale(2), fee);
    }

    @Test
    void when_order_total_is_74_point_99_and_order_subtotal_is_59_point_99_then_an_error_should_be_thrown() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(74.99));
        order.setSubTotal(BigDecimal.valueOf(59.99));

        Exception exception = null;

        //ACT
        try {
            BigDecimal fee = order.createFee();
        }catch (Exception e) {
            exception = e;
        }

        //Assert
        Assertions.assertEquals("Total must be greater than 74.99", exception.getMessage());
    }


    @Test
    void when_order_total_is_101_and_order_subtotal_is_80_point_80_then_fee_should_be_4_point_04() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(101));
        order.setSubTotal(BigDecimal.valueOf(80.80));

        //ACT
        BigDecimal fee = order.createFee();

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(4.04).setScale(2), fee);
    }



    @Test
    void when_order_total_is_500_point_99_and_order_subtotal_is_400_point_79_then_fee_should_be_20_point_04() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(500.99));
        order.setSubTotal(BigDecimal.valueOf(400.79));

        //ACT
        BigDecimal fee = order.createFee();

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(20.04).setScale(2), fee);
    }


    @Test
    void when_order_total_is_1000_and_order_subtotal_is_800_then_fee_should_be_32() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(1000.00));
        order.setSubTotal(BigDecimal.valueOf(800.00));

        //ACT
        BigDecimal fee = order.createFee();

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(32).setScale(2), fee);
    }



    @Test
    void when_order_total_is_650_and_order_subtotal_is_520_then_fee_should_be_20_point_8() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(650));
        order.setSubTotal(BigDecimal.valueOf(520));

        //ACT
        BigDecimal fee = order.createFee();

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(20.80).setScale(2), fee);
    }

    @Test
    void when_order_total_is_600_and_order_subtotal_is_480_then_fee_should_be_19_point_2() {
        //ARRANGE
        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(600));
        order.setSubTotal(BigDecimal.valueOf(480));

        //ACT
        BigDecimal fee = order.createFee();

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(19.20).setScale(2), fee);
    }
    @Test
    void when_order_total_is_1001_and_order_subtotal_is_800_point_80_then_fee_should_be_24_point_02() {
           // ARRANGE
           Order order = new Order();
           order.setTotal(BigDecimal.valueOf(1001.00));
           order.setSubTotal(BigDecimal.valueOf(800.80));

           // ACT
           BigDecimal fee = order.createFee();

           // ASSERT
           Assertions.assertEquals(BigDecimal.valueOf(24.02).setScale(2), fee);
       }



    @Test
    void when_order_total_is_1000_point_01_and_order_subtotal_is_800_then_fee_should_be_24() {
           // ARRANGE
           Order order = new Order();
           order.setTotal(BigDecimal.valueOf(1000.01));
           order.setSubTotal(BigDecimal.valueOf(800.00));

           // ACT
           BigDecimal fee = order.createFee();

           // ASSERT
           Assertions.assertEquals(BigDecimal.valueOf(24.00).setScale(2), fee);
       }


  @Test
   void when_order_total_is_8000_and_order_subtotal_is_6400_then_fee_should_be_192() {
       // ARRANGE
       Order order = new Order();
       order.setTotal(BigDecimal.valueOf(8000.00));
       order.setSubTotal(BigDecimal.valueOf(6400.00));

       // ACT
       BigDecimal fee = order.createFee();

       // ASSERT
       Assertions.assertEquals(BigDecimal.valueOf(192.00).setScale(2), fee);
   }

    @Test
    void when_order_has_3_orderlines_with_subtotals_100_and_120_and_180_then_order_subtotal_should_be_400() {
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(100.00));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setSubTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setSubTotal(BigDecimal.valueOf(180.00));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        // ACT
        order.createSubTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(400.00).setScale(2), order.getSubTotal());
    }


    // ===== Calculate subtotal Test =====
    @Test
    void when_order_has_1_orderline_with_subtotal_100_then_order_subtotal_should_be_100() {
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(100.00));

        order.addOrderLine(orderLine1);

        // ACT
        order.createSubTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(100.00).setScale(2), order.getSubTotal());
    }

    @Test
    void when_order_has_18_order_lines_with_the_following_subtotals_then_subtotal_should_be_1804(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(100.00));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setSubTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setSubTotal(BigDecimal.valueOf(91.00));
        OrderLine orderLine4 = new OrderLine();
        orderLine4.setSubTotal(BigDecimal.valueOf(30.00));
        OrderLine orderLine5 = new OrderLine();
        orderLine5.setSubTotal(BigDecimal.valueOf(130.00));
        OrderLine orderLine6 = new OrderLine();
        orderLine6.setSubTotal(BigDecimal.valueOf(180.00));
        OrderLine orderLine7 = new OrderLine();
        orderLine7.setSubTotal(BigDecimal.valueOf(110.00));
        OrderLine orderLine8 = new OrderLine();
        orderLine8.setSubTotal(BigDecimal.valueOf(150.00));
        OrderLine orderLine9 = new OrderLine();
        orderLine9.setSubTotal(BigDecimal.valueOf(138.00));
        OrderLine orderLine10 = new OrderLine();
        orderLine10.setSubTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine11 = new OrderLine();
        orderLine11.setSubTotal(BigDecimal.valueOf(90.00));
        OrderLine orderLine12 = new OrderLine();
        orderLine12.setSubTotal(BigDecimal.valueOf(55.00));
        OrderLine orderLine13 = new OrderLine();
        orderLine13.setSubTotal(BigDecimal.valueOf(153.00));
        OrderLine orderLine14 = new OrderLine();
        orderLine14.setSubTotal(BigDecimal.valueOf(112.00));
        OrderLine orderLine15 = new OrderLine();
        orderLine15.setSubTotal(BigDecimal.valueOf(117.00));
        OrderLine orderLine16 = new OrderLine();
        orderLine16.setSubTotal(BigDecimal.valueOf(32.00));
        OrderLine orderLine17 = new OrderLine();
        orderLine17.setSubTotal(BigDecimal.valueOf(65.00));
        OrderLine orderLine18 = new OrderLine();
        orderLine18.setSubTotal(BigDecimal.valueOf(11.00));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);
        order.addOrderLine(orderLine4);
        order.addOrderLine(orderLine5);
        order.addOrderLine(orderLine6);
        order.addOrderLine(orderLine7);
        order.addOrderLine(orderLine8);
        order.addOrderLine(orderLine9);
        order.addOrderLine(orderLine10);
        order.addOrderLine(orderLine11);
        order.addOrderLine(orderLine12);
        order.addOrderLine(orderLine13);
        order.addOrderLine(orderLine14);
        order.addOrderLine(orderLine15);
        order.addOrderLine(orderLine16);
        order.addOrderLine(orderLine17);
        order.addOrderLine(orderLine18);

        // ACT
        order.createSubTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(1804.00).setScale(2), order.getSubTotal());
    }


    @Test
    void when_order_has_4_order_lines_with_subtotals_100_point_7_and_120_point_86_and_1_point_01_and_19_point_01_then_subtotal_should_be_241_point_58(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(100.70));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setSubTotal(BigDecimal.valueOf(120.86));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setSubTotal(BigDecimal.valueOf(1.01));
        OrderLine orderLine4 = new OrderLine();
        orderLine4.setSubTotal(BigDecimal.valueOf(19.01));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);
        order.addOrderLine(orderLine4);

        // ACT
        order.createSubTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(241.58).setScale(2), order.getSubTotal());
    }
    @Test
    void when_order_has_1_orderline_with_subtotal_100_point_7_then_subtotal_should_be_100_point_7(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(100.70));

        order.addOrderLine(orderLine1);

        // ACT
        order.createSubTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(100.70).setScale(2), order.getSubTotal());
    }



    @Test
    void when_order_has_3_order_lines_with_subtotals_100_point_7_and_120_and_1_point_01_then_subtotal_should_be_221_point_71(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(100.70));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setSubTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setSubTotal(BigDecimal.valueOf(1.01));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        // ACT
        order.createSubTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(221.71).setScale(2), order.getSubTotal());
    }

    @Test
    void when_order_has_3_order_lines_with_subtotals_100_point_7_and_120_and_minus_1_point_01_then_an_error_should_be_thrown(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(100.70));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setSubTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setSubTotal(BigDecimal.valueOf(-1.01));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        Exception exception = null;

        // ACT
        try {
            order.createSubTotal();
        } catch (Exception e){
            exception = e;
        }

        // ASSERT
        Assertions.assertEquals("Subtotals must be greater than zero", exception.getMessage());
    }


    @Test
    void when_order_has_3_order_lines_with_subtotals_minus_100_and_minus_120_and_minus_1_then_an_error_should_be_thrown(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(-100.00));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setSubTotal(BigDecimal.valueOf(-120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setSubTotal(BigDecimal.valueOf(-1.00));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        Exception exception = null;

        // ACT
        try {
            order.createSubTotal();
        } catch (Exception e){
            exception = e;
        }

        // ASSERT
        Assertions.assertEquals("Subtotals must be greater than zero", exception.getMessage());
    }

    @Test
    void when_order_has_3_order_lines_with_subtotals_0_and_0_and_0_then_subtotal_should_be_0(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(0.00));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setSubTotal(BigDecimal.valueOf(0.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setSubTotal(BigDecimal.valueOf(0.00));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        // ACT
        order.createSubTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), order.getSubTotal());
    }

    @Test
    void when_order_has_1_orderline_with_subtotal_0_then_subtotal_should_be_0(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setSubTotal(BigDecimal.valueOf(0.00));

        order.addOrderLine(orderLine1);

        // ACT
        order.createSubTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), order.getSubTotal());
    }


    // ===== Calculate total Test =====

    @Test
    void when_order_has_3_order_lines_with_totals_100_and_120_and_180_then_total_should_be_400(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(100.00));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setTotal(BigDecimal.valueOf(180.00));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        // ACT
        order.createTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(400.00).setScale(2), order.getTotal());
    }

    @Test
    void when_order_has_1_orderline_with_total_100_then_total_should_be_100(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(100.00));

        order.addOrderLine(orderLine1);

        // ACT
        order.createTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(100.00).setScale(2), order.getTotal());
    }

    @Test
    void when_order_has_18_order_lines_with_the_following_totals_then_total_should_be_1804(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(100.00));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setTotal(BigDecimal.valueOf(91.00));
        OrderLine orderLine4 = new OrderLine();
        orderLine4.setTotal(BigDecimal.valueOf(30.00));
        OrderLine orderLine5 = new OrderLine();
        orderLine5.setTotal(BigDecimal.valueOf(130.00));
        OrderLine orderLine6 = new OrderLine();
        orderLine6.setTotal(BigDecimal.valueOf(180.00));
        OrderLine orderLine7 = new OrderLine();
        orderLine7.setTotal(BigDecimal.valueOf(110.00));
        OrderLine orderLine8 = new OrderLine();
        orderLine8.setTotal(BigDecimal.valueOf(150.00));
        OrderLine orderLine9 = new OrderLine();
        orderLine9.setTotal(BigDecimal.valueOf(138.00));
        OrderLine orderLine10 = new OrderLine();
        orderLine10.setTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine11 = new OrderLine();
        orderLine11.setTotal(BigDecimal.valueOf(90.00));
        OrderLine orderLine12 = new OrderLine();
        orderLine12.setTotal(BigDecimal.valueOf(55.00));
        OrderLine orderLine13 = new OrderLine();
        orderLine13.setTotal(BigDecimal.valueOf(153.00));
        OrderLine orderLine14 = new OrderLine();
        orderLine14.setTotal(BigDecimal.valueOf(112.00));
        OrderLine orderLine15 = new OrderLine();
        orderLine15.setTotal(BigDecimal.valueOf(117.00));
        OrderLine orderLine16 = new OrderLine();
        orderLine16.setTotal(BigDecimal.valueOf(32.00));
        OrderLine orderLine17 = new OrderLine();
        orderLine17.setTotal(BigDecimal.valueOf(65.00));
        OrderLine orderLine18 = new OrderLine();
        orderLine18.setTotal(BigDecimal.valueOf(11.00));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);
        order.addOrderLine(orderLine4);
        order.addOrderLine(orderLine5);
        order.addOrderLine(orderLine6);
        order.addOrderLine(orderLine7);
        order.addOrderLine(orderLine8);
        order.addOrderLine(orderLine9);
        order.addOrderLine(orderLine10);
        order.addOrderLine(orderLine11);
        order.addOrderLine(orderLine12);
        order.addOrderLine(orderLine13);
        order.addOrderLine(orderLine14);
        order.addOrderLine(orderLine15);
        order.addOrderLine(orderLine16);
        order.addOrderLine(orderLine17);
        order.addOrderLine(orderLine18);

        // ACT
        order.createTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(1804.00).setScale(2), order.getTotal());
    }

   @Test
    void when_order_has_4_order_lines_with_totals_100_point_7_and_120_point_86_and_1_point_01_and_19_point_01_then_total_should_be_241_point_58(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(100.70));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setTotal(BigDecimal.valueOf(120.86));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setTotal(BigDecimal.valueOf(1.01));
        OrderLine orderLine4 = new OrderLine();
        orderLine4.setTotal(BigDecimal.valueOf(19.01));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);
        order.addOrderLine(orderLine4);

        // ACT
        order.createTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(241.58).setScale(2), order.getTotal());
    }

    @Test
    void when_order_has_1_orderline_with_total_100_point_7_then_total_should_be_100_point_7(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(100.70));

        order.addOrderLine(orderLine1);

        // ACT
        order.createTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(100.70).setScale(2), order.getTotal());
    }


    @Test
    void when_order_has_3_order_lines_with_totals_100_point_7_and_120_and_1_point_01_then_total_should_be_221_point_71(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(100.70));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setTotal(BigDecimal.valueOf(1.01));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        // ACT
        order.createTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(221.71).setScale(2), order.getTotal());
    }


    @Test
    void when_order_has_3_order_lines_with_totals_100_point_7_and_120_and_minus_1_point_01_then_an_error_should_be_thrown(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(100.70));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setTotal(BigDecimal.valueOf(120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setTotal(BigDecimal.valueOf(-1.01));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        Exception exception = null;

        // ACT
        try {
            order.createTotal();
        } catch (Exception e){
            exception = e;
        }

        // ASSERT
        Assertions.assertEquals("Totals must be greater than zero", exception.getMessage());
    }



    @Test
    void when_order_has_3_order_lines_with_totals_minus_100_and_minus_120_and_minus_1_then_an_error_should_be_thrown(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(-100.00));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setTotal(BigDecimal.valueOf(-120.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setTotal(BigDecimal.valueOf(-1.00));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        Exception exception = null;

        // ACT
        try {
            order.createTotal();
        } catch (Exception e){
            exception = e;
        }

        // ASSERT
        Assertions.assertEquals("Totals must be greater than zero", exception.getMessage());
    }


    @Test
    void when_order_has_3_order_lines_with_totals_0_and_0_and_0_then_total_should_be_0(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(0.00));
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setTotal(BigDecimal.valueOf(0.00));
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setTotal(BigDecimal.valueOf(0.00));

        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);
        order.addOrderLine(orderLine3);

        // ACT
        order.createTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), order.getTotal());
    }

    @Test
    void when_order_has_1_orderline_with_total_0_then_total_should_be_0(){
        // ARRANGE
        Order order = new Order();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setTotal(BigDecimal.valueOf(0.00));

        order.addOrderLine(orderLine1);

        // ACT
        order.createTotal();

        // ASSERT
        Assertions.assertEquals(BigDecimal.valueOf(0.00).setScale(2), order.getTotal());
    }



}
