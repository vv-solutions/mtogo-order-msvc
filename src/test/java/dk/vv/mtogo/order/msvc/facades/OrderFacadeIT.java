package dk.vv.mtogo.order.msvc.facades;

import dk.vv.common.data.transfer.objects.order.OrderDTO;
import dk.vv.common.data.transfer.objects.product.ProductDTO;
import dk.vv.mtogo.order.msvc.pojos.Order;
import dk.vv.mtogo.order.msvc.pojos.OrderLine;
import dk.vv.mtogo.order.msvc.repositories.OrderRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

@QuarkusTest
public class OrderFacadeIT {

    @Inject
    protected Flyway flyway;

    @Inject
    protected OrderFacade orderFacade;

    @Inject
    protected OrderRepository orderRepository;
    @BeforeEach
    public void before() {
        flyway.migrate();
    }


    @AfterEach
    public void restoreDatabase() {
        flyway.clean();
    }


    @Test
    void when_update_status_on_order_10_with_status_2_order_status_should_be_2(){
        //Arrange
        int id = 10;
        int status = 2;


        //Act
        orderFacade.handleStatusUpdate(id, status);

        //Assert
        var order = orderRepository.findById((long) id);

        Assertions.assertEquals(status,order.getStatusId());
    }




    @Test
    @Transactional
    void when_create_new_order_with_prices_order_should_be_persisted_with_prices() throws Exception {
        //Arrange

        Order order = new Order();
        order.setSupplierId(1);
        order.setCustomerId(1);

        OrderLine orderLine = new OrderLine();
        orderLine.setProductId(1);
        orderLine.setQuantity(5);

        order.addOrderLine(orderLine);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setGrossPrice(BigDecimal.valueOf(100));
        productDTO.setNetPrice(BigDecimal.valueOf(80));

        //Act
        OrderDTO orderDTO = orderFacade.createNewOrderWithPrices(order.toDTO(), List.of(productDTO));


        //Assert
        Assertions.assertEquals(11,orderDTO.getId());
        Assertions.assertEquals(BigDecimal.valueOf(400).setScale(2),orderDTO.getSubTotal());
        Assertions.assertEquals(BigDecimal.valueOf(500).setScale(2),orderDTO.getTotal());

    }

    @Test
    void when_get_by_id_5_total_should_be_22_point_00(){
        //Act
        var order = orderFacade.getOrderById(5);

        //Assert
        Assertions.assertEquals(BigDecimal.valueOf(22).setScale(2),order.getTotal());
    }

}
