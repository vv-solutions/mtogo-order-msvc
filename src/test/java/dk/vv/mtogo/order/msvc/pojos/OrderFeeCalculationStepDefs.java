package dk.vv.mtogo.order.msvc.pojos;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderFeeCalculationStepDefs {

    Order order;

    List<Exception> exceptions;

    BigDecimal fee;

    @Given("I have an order with a total of {bigdecimal} and a subtotal of {bigdecimal}")
    public void iHaveAnOrderWithATotalOf(BigDecimal arg0, BigDecimal arg1) {
        exceptions = new ArrayList<>();
        order = new Order();
        fee = BigDecimal.ZERO;
        order.setTotal(arg0);
        order.setSubTotal(arg1);

    }

    @When("I calculate the fee")
    public void iCalculateTheFee() {
        try{
            fee = order.createFee();
        }catch (Exception e){
            exceptions.add(e);
        }
    }

    @Then("The fee should be {bigdecimal}")
    public void theFeeShouldBe(BigDecimal arg0) {
        Assertions.assertEquals(arg0,fee);
    }

    @Then("i should get an error with this {string} message")
    public void iShouldGetAnErrorWithThisMessage(String arg0) {
        Assertions.assertFalse(exceptions.isEmpty());
        Assertions.assertEquals(arg0,exceptions.get(0).getMessage());
    }
}
