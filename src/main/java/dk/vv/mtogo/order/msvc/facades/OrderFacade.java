package dk.vv.mtogo.order.msvc.facades;

import dk.vv.common.data.transfer.objects.order.OrderDTO;
import dk.vv.common.data.transfer.objects.product.ProductDTO;
import dk.vv.mtogo.order.msvc.enums.OrderStatus;
import dk.vv.mtogo.order.msvc.pojos.Order;
import dk.vv.mtogo.order.msvc.pojos.OrderLine;
import dk.vv.mtogo.order.msvc.pojos.OrderMetric;
import dk.vv.mtogo.order.msvc.repositories.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ApplicationScoped
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

    @Transactional
    public void handleStatusUpdate(int id, int status){
        orderRepository.update("statusId = ?1 where id= ?2",status,id);
    }

    public OrderDTO getOrderById(int id){
        return orderRepository.findById((long) id).toDTO();
    }



    public OrderMetric getOrderMetrics(){
        return orderRepository.getEntityManager().createQuery("SELECT new dk.vv.mtogo.order.msvc.pojos.OrderMetric( " +
                "SUM(CASE WHEN o.statusId = :errorStatus THEN 1 ELSE 0 END),  " +
                "SUM(CASE WHEN o.statusId = :receivedStatus THEN 1 ELSE 0 END), " +
                "SUM(CASE WHEN o.statusId = :acceptedStatus THEN 1 ELSE 0 END), " +
                "SUM(CASE WHEN o.statusId = :finishedStatus THEN 1 ELSE 0 END)," +
                "SUM(CASE WHEN o.statusId = :deniedStatus THEN 1 ELSE 0 END), " +
                "COUNT(*)) " +
                "FROM Order o", OrderMetric.class)
                    .setParameter("errorStatus",OrderStatus.ERROR.value())
                    .setParameter("receivedStatus",OrderStatus.RECEIVED.value())
                    .setParameter("acceptedStatus",OrderStatus.ACCEPTED.value())
                    .setParameter("finishedStatus",OrderStatus.FINISHED.value())
                    .setParameter("deniedStatus",OrderStatus.DENIED.value())
                .getSingleResult();
    }


    public Map<Integer, Long> getProductMetrics(){
        return orderRepository.getEntityManager().createQuery("select o.productId as productId, SUM(o.quantity) as quantity From OrderLine o group by o.productId", Tuple.class).getResultStream().collect(Collectors.toMap(
                tuple -> (Integer) tuple.get("productId"),
                tuple -> (Long) tuple.get("quantity")));
    }

    public List<BigDecimal> getOrderPrices(){
        return orderRepository.getEntityManager().createQuery("select o.total From Order o",BigDecimal.class).getResultList();
    }

    public Map<Integer, Long> getSupplierMetrics(){
        return orderRepository.getEntityManager().createQuery("select o.supplierId as supplierId, COUNT(o) as totalOrders From Order o group by o.supplierId", Tuple.class).getResultStream().collect(Collectors.toMap(
                tuple -> (Integer) tuple.get("supplierId"),
                tuple -> (Long) tuple.get("totalOrders")));
    }

    public Map<Integer, Long> getCustomerMetrics(){
        return orderRepository.getEntityManager().createQuery("select o.customerId as customerId, COUNT(o) as totalOrders From Order o group by o.customerId", Tuple.class).getResultStream().collect(Collectors.toMap(
                tuple -> (Integer) tuple.get("customerId"),
                tuple -> (Long) tuple.get("totalOrders")));
    }

}

