package dk.vv.mtogo.order.msvc.pojos;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderLineTotalStepDefs {

    private OrderLine orderline;

    private List<Exception> exceptions;

    @Given("I have an order line with a quantity of {int} and a unit net price of {bigdecimal}")
    public void iHaveAnOrderLineWithAQuantityOfAndAUnitNetPriceOf( int arg1, BigDecimal arg2) {
        exceptions = new ArrayList<>();
        orderline = new OrderLine();
        this.orderline.setQuantity(arg1);
        this.orderline.setUnitNetPrice(arg2);

    }

    @When("I calculate the total")
    public void iCalculateTheTotal() {
        try{
            this.orderline.createLineTotal();
        }catch (Exception e){
            this.exceptions.add(e);
        }
    }

    @Then("the total should be {bigdecimal}")
    public void theTotalShouldBe(BigDecimal arg0) {
        Assertions.assertEquals(arg0,this.orderline.getTotal());
    }


    @Then("I should get the following error message {string}")
    public void iShouldGetThisErrorMessage(String arg0) {
        Assertions.assertFalse(exceptions.isEmpty());
        Assertions.assertEquals(arg0,exceptions.get(0).getMessage());
    }
}
