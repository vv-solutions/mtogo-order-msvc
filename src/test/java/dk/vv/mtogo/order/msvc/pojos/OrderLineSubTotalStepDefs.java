package dk.vv.mtogo.order.msvc.pojos;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderLineSubTotalStepDefs {

    private OrderLine orderLine;

    private List<Exception> exceptions;


    @Given("I have an order line with a quantity of {int} and a unit gross price of {bigdecimal}")
    public void iHaveAnOrderLineWithAQuantityOfAndAUnitGrossPriceOf(int arg0, BigDecimal arg2) {
        orderLine = new OrderLine();
        exceptions = new ArrayList<>();
        this.orderLine.setQuantity(arg0);
        this.orderLine.setUnitGrossPrice(arg2);

        System.out.println("=======1========");
        System.out.println(orderLine.getQuantity());
        System.out.println(orderLine.getUnitGrossPrice());
    }


    @When("I calculate the sub total")
    public void iCalculateTheSubTotal() {

        System.out.println("=======2========");
        System.out.println(orderLine.getQuantity());
        System.out.println(orderLine.getUnitGrossPrice());

        try{
            this.orderLine.createLineSubTotal();
        } catch (Exception e){
            this.exceptions.add(e);
        }
    }

    @Then("the sub total should be {bigdecimal}")
    public void theSubTotalShouldBe(BigDecimal arg1) {

        System.out.println("=======3========");
        System.out.println(orderLine.getQuantity());
        System.out.println(orderLine.getUnitGrossPrice());
        System.out.println(orderLine.getSubTotal());

        Assertions.assertEquals(arg1, this.orderLine.getSubTotal());
    }

    @Then("I should get this error message {string}")
    public void iShouldGetThisErrorMessage(String arg0) {
        Assertions.assertFalse(this.exceptions.isEmpty());
        Assertions.assertEquals(arg0,this.exceptions.get(0).getMessage());
    }
}
