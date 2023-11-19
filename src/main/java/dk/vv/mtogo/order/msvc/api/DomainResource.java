package dk.vv.mtogo.order.msvc.api;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.vv.common.data.transfer.objects.order.OrderDTO;
import dk.vv.common.data.transfer.objects.order.OrderLineDTO;
import dk.vv.common.data.transfer.objects.product.ProductDTO;
import dk.vv.mtogo.order.msvc.facades.OrderFacade;
import dk.vv.mtogo.order.msvc.message.MessageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

import static dk.vv.mtogo.order.msvc.api.ExamplePayloads.*;

@Path("/api/order")
@Produces("application/json")
@Consumes("application/json")
@ApplicationScoped
public class DomainResource {


    private final OrderFacade orderFacade;

    private final MessageService messageService;

    private final ObjectMapper mapper = new ObjectMapper();

    private final ProductService productService;

    private final Logger logger;

    @Inject
    public DomainResource(OrderFacade orderFacade, MessageService messageService, @RestClient ProductService productService, Logger logger) {
        this.orderFacade = orderFacade;
        this.messageService = messageService;

        this.productService = productService;
        this.logger = logger;
    }


    @POST
    @ResponseStatus(201)
    @Transactional
    @Operation(summary = "Place order", description = "Persists order and sends order creation event, returns the created order")
    @RequestBody(
            required = true,
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class, required = true, requiredProperties = {"productName", "description", "grossPrice", "supplierId"}),
                    examples = @ExampleObject(
                            name = "Order",
                            value = NEW_ORDER,
                            summary = "Order",
                            description = "Order"
                    )
            ))
    @APIResponse(
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class),
                    examples = @ExampleObject(
                            name = "Order",
                            value = CREATED_ORDER,
                            summary = "Order",
                            description = "Order"
                    )
            ))
    public OrderDTO placeOrder(OrderDTO orderDTO) throws Exception {
        logger.infof("order creation: received information about new order with body: %s", mapper.writeValueAsString(orderDTO));

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
    @Operation(summary = "Get orders", description = "Returns a list of all orders")
    @APIResponse(
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class),
                    examples = @ExampleObject(
                            name = "Order",
                            value = LIST_OF_ORDERS,
                            summary = "Order",
                            description = "Order"
                    )
    ))
    public List<OrderDTO> getAllOrders() {
        return orderFacade.getAllOrders();
    }



}
