package dk.vv.mtogo.order.msvc.api;

import dk.vv.mtogo.order.msvc.dtos.OrderDTO;
import dk.vv.mtogo.order.msvc.dtos.OrderLineDTO;
import dk.vv.mtogo.order.msvc.message.MessageServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DomainResourceIT {
    @Inject
    protected Flyway flyway;

    @BeforeEach
    public void before() {
        flyway.migrate();
    }


    @AfterEach
    public void restoreDatabase() {
        flyway.clean();
    }


    @Test
    void when_posting_an_order_status_code_should_be_201_and_the_order_id_returned_should_be_11(){

        // Setup
        OrderLineDTO orderLine1 = new OrderLineDTO();
        orderLine1.setProductId(1);
        orderLine1.setUnitGrossPrice(BigDecimal.valueOf(100));
        orderLine1.setUnitNetPrice(BigDecimal.valueOf(80));

        orderLine1.setQuantity(1);

        OrderLineDTO orderLine2 = new OrderLineDTO();
        orderLine2.setProductId(101);
        orderLine2.setUnitGrossPrice(BigDecimal.valueOf(125));
        orderLine2.setUnitNetPrice(BigDecimal.valueOf(100));
        orderLine2.setQuantity(2);


        OrderDTO order = new OrderDTO();
        order.setCustomerId(1);
        order.setSupplierId(2);
        order.setAddressId(14);


        order.addOrderLine(orderLine1);
        order.addOrderLine(orderLine2);


        // ACT
        OrderDTO orderDTO = given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/api/order/")
                .then()
        // Assert
                .assertThat()
                .statusCode(201)
                .extract()
                .body()
                .jsonPath()
                .getObject("", OrderDTO.class);

        Assertions.assertEquals(11,orderDTO.getId());

    }


    @Test
    void when_getting_all_orders_status_code_should_be_200_and_the_size_should_be_10(){
        // ACT
        List<OrderDTO> orders = given()
                .when()
                .get("/api/order/")
                .then()

                // Assert
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", OrderDTO.class);

        Assertions.assertEquals(10,orders.size());

    }

}
