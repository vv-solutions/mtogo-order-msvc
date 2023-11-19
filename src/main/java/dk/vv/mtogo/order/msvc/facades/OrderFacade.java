package dk.vv.mtogo.order.msvc.facades;

import dk.vv.common.data.transfer.objects.order.OrderDTO;
import dk.vv.common.data.transfer.objects.product.ProductDTO;
import dk.vv.mtogo.order.msvc.enums.OrderStatus;
import dk.vv.mtogo.order.msvc.pojos.Order;
import dk.vv.mtogo.order.msvc.pojos.OrderLine;
import dk.vv.mtogo.order.msvc.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderFacade {

    private final OrderRepository orderRepository;

    public OrderFacade(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<OrderDTO> getAllOrders(){
        return orderRepository.findAll().list().stream().map(Order::toDTO).collect(Collectors.toList());
    }




    public OrderDTO createNewOrderWithPrices(OrderDTO orderDTO, List<ProductDTO> productDTOS) throws Exception {
        Order order = new Order(orderDTO);

        //Calculate order line prices
        for (OrderLine orderLine : order.getOrderLines()) {
            for (ProductDTO productDTO : productDTOS) {
                if(orderLine.getProductId() == productDTO.getId()){
                    orderLine.setUnitGrossPrice(productDTO.getGrossPrice());
                    orderLine.setUnitNetPrice(productDTO.getNetPrice());
                    orderLine.createLineSubTotal();
                    orderLine.createLineTotal();
                }
            }
        }


        // calculate order prices
        order.createSubTotal();
        order.createTotal();
        order.setStatusId(OrderStatus.RECEIVED.value());

        // save order
        orderRepository.persist(order);

        return order.toDTO();
    }

    public void handleStatusUpdate(OrderDTO orderDTO){
        orderRepository.update("statusId = ?1 where id= ?2",orderDTO.getStatusId(),orderDTO.getId());
    }


    //Order
    //    private int customerId;
    //    private String comment;

    //
    //    private int statusId;
    //
    //    private BigDecimal subTotal;
    //
    //    private BigDecimal total;
    //
    //    private int addressId;
    //
    //    private int supplierId;

    //ORDERLINES
    //    private int productId;
    //    private int quantity;



}
