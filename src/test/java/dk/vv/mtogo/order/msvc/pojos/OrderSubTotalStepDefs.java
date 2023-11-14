package dk.vv.mtogo.order.msvc.pojos;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderSubTotalStepDefs {

    Set<OrderLine> orderLines;

    Order order;

    List<Exception> exceptions;


    @Given("I have an order with the following orderline subtotals:")
    public void iHaveAnOrderWithTheFollowingOrderlineSubtotals(DataTable dataTable) {
        exceptions = new ArrayList<>();
        orderLines = new HashSet<>();

        dataTable.asList(BigDecimal.class).forEach(bd -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setSubTotal(bd);
            orderLines.add(orderLine);

        });
            order = new Order(){{
            this.setOrderLines(orderLines);
        }};

    }

    @When("I calculate the order sub total")
    public void iCalculateTheOrderSubTotal() {
        try{
            order.createSubTotal();
        } catch (Exception e){
            exceptions.add(e);
        }
    }

    @Then("The order sub total should be {bigdecimal}")
    public void theOrderSubTotalShouldBe(BigDecimal arg0) {
        Assertions.assertEquals(arg0,order.getSubTotal());
    }

    @Then("Then i should get an error message saying {string}")
    public void thenIShouldGetAnErrorMessageSaying(String arg0) {
        Assertions.assertFalse(exceptions.isEmpty());
        Assertions.assertEquals(arg0,exceptions.get(0).getMessage());
    }

}
