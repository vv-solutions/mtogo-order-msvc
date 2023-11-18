package dk.vv.mtogo.order.msvc.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.vv.mtogo.order.msvc.dtos.OrderDTO;
import dk.vv.mtogo.order.msvc.dtos.OrderLineDTO;
import dk.vv.mtogo.order.msvc.dtos.ProductDTO;
import dk.vv.mtogo.order.msvc.facades.OrderFacade;
import dk.vv.mtogo.order.msvc.message.MessageService;
import dk.vv.mtogo.order.msvc.pojos.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/order")
@Produces("application/json")
@Consumes("application/json")
@ApplicationScoped
public class DomainResource {


    private final OrderFacade orderFacade;

    private final MessageService messageService;

    private final ObjectMapper mapper = new ObjectMapper();

    private final ProductService productService;

    @Inject
    public DomainResource(OrderFacade orderFacade, MessageService messageService, @RestClient ProductService productService) {
        this.orderFacade = orderFacade;
        this.messageService = messageService;

        this.productService = productService;
    }
    

    @POST
    @ResponseStatus(201)
    @Transactional
    public OrderDTO placeOrder(OrderDTO orderDTO) throws Exception {

        // calculate prices
        List<Integer> productIds = orderDTO.getOrderLines().stream().map(OrderLineDTO::getProductId).toList();


        Response response = productService.getProducts(productIds);

        List<ProductDTO> products = mapper.readValue(response.readEntity(String.class), new TypeReference<List<ProductDTO>>(){});


        // calculate prices & save order
        OrderDTO createdOrder = orderFacade.createNewOrderWithPrices(orderDTO,products);

        // send message on order creation exchange
        messageService.sendOrderCreationMessage(orderDTO);

//        orderRepository.persist(order);

        // calculate prices 1.
        // save in db status -- received

        // send on order create queue


        // order creation mediator:

            // send notification -- queue -- order received 1.

            // inform restaurant -- queue
            // wait for response from restaurant

        // if ack:
            // inform delivery
            // inform order -- status accepted --> send fee on queue
            // send notification -- queue -- order accepted

        // if nack
            // inform order -- status denied
            // send noti -- order denied

        return createdOrder;
    }

    @GET
    @ResponseStatus(200)
    @Transactional
    public List<OrderDTO> getAllOrders() {
        return orderFacade.getAllOrders();
    }


    @POST
    @Path("/test")
    public String test() throws JsonProcessingException {
        Response response = productService.getProducts(List.of(1,101));

        System.out.println(response.readEntity(String.class));

        return "ok";
    }


}
