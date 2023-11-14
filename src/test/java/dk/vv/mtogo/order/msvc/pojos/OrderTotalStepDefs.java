package dk.vv.mtogo.order.msvc.pojos;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderTotalStepDefs {
    
    Set<OrderLine> orderLines;
    Order order;
    List<Exception> exceptions;
    
    
    @Given("I have an order with the following orderline totals:")
    public void iHaveAnOrderWithTheFollowingOrderlineTotals(DataTable dataTable) {
        orderLines = new HashSet<>();

        exceptions = new ArrayList<>();
        
        dataTable.asList(BigDecimal.class).forEach(ot -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setTotal(ot);
            orderLines.add(orderLine);
        });
        order = new Order(){{
            this.setOrderLines(orderLines);
        }};
        

    }

    @When("I calculate the order total")
    public void iCalculateTheOrderTotal() {
        try{
            order.createTotal();
        }catch (Exception e){
            exceptions.add(e);
        }
        
    }

    @Then("The order total should be {bigdecimal}")
    public void theOrderTotalShouldBe(BigDecimal arg0) {
        Assertions.assertEquals(arg0,order.getTotal());
    }

    @Then("Then i should get an error message saying this {string}")
    public void thenIShouldGetAnErrorMessageSayingThis(String arg0) {
        Assertions.assertFalse(exceptions.isEmpty());
        Assertions.assertEquals(arg0,exceptions.get(0).getMessage());
    }
}
