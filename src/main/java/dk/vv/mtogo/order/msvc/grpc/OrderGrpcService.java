package dk.vv.mtogo.order.msvc.grpc;

import dk.vv.mtogo.order.msvc.facades.OrderFacade;
import dk.vv.mtogo.order.msvc.pojos.OrderMetric;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class OrderGrpcService implements OrderGrpc {


    private final OrderFacade orderFacade;

    @Inject
    public OrderGrpcService(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }


    @Override
    @Transactional
    public Uni<OrderMetrics> getOrderMetrics(Empty request) {
        OrderMetric orderMetric = orderFacade.getOrderMetrics();
        return Uni.createFrom().item("test").map(met -> OrderMetrics.newBuilder()
                .setAcceptedOrders((int) orderMetric.getAcceptedCount())
                .setDeniedOrders((int) orderMetric.getDeniedCount())
                .setErrorOrders((int) orderMetric.getErrorCount())
                .setTotalOrders((int) orderMetric.getTotalCount())
                .setReceivedOrders((int) orderMetric.getReceivedCount())
                .setFinishedOrders((int) orderMetric.getFinishedCount())
                .build());
    }

    @Override
    @Transactional
    public Uni<OrderPrices> getOrderPrices(Empty request) {
        return Uni.createFrom().item("test").map(met -> OrderPrices.newBuilder()
                .addAllOrderTotalPrice(orderFacade.getOrderPrices().stream().map(BigDecimal::doubleValue).collect(Collectors.toList()))
                .build());
    }

    @Override
    @Transactional
    public Uni<ProductInformationCollection> getProductInformation(Empty request) {

        var productMetricsMap = orderFacade.getProductMetrics();

        List<ProductInformation> productInformationList = new ArrayList<>();

        for (Integer productId : productMetricsMap.keySet()) {
            productInformationList.add(ProductInformation.newBuilder().setProductId(productId).setPurchaseCount(productMetricsMap.get(productId).intValue()).build());
        }

        return Uni.createFrom().item("test").map(pfc -> ProductInformationCollection.newBuilder().addAllProductInformation(productInformationList).build());
    }

    @Override
    @Transactional
    public Uni<SupplierInformationCollection> getSupplierInformation(Empty request) {
        var supplierMetricsMap = orderFacade.getSupplierMetrics();

        List<SupplierInformation> supplierInformationList = new ArrayList<>();

        for (Integer supplierId : supplierMetricsMap.keySet()) {
            supplierInformationList.add(SupplierInformation.newBuilder().setSupplierId(supplierId).setOrderCount(supplierMetricsMap.get(supplierId).intValue()).build());
        }

        return Uni.createFrom().item("test").map(cic -> SupplierInformationCollection.newBuilder().addAllSupplierInformation(supplierInformationList).build());
    }

    @Override
    @Transactional
    public Uni<CustomerInformationCollection> getCustomerInformation(Empty request) {
        var customerMetricsMap = orderFacade.getCustomerMetrics();

        List<CustomerInformation> customerInformations = new ArrayList<>();

        for (Integer customerId : customerMetricsMap.keySet()) {
            customerInformations.add(CustomerInformation.newBuilder().setCustomerId(customerId).setOrderCount(customerMetricsMap.get(customerId).intValue()).build());
        }

        return Uni.createFrom().item(CustomerInformationCollection.newBuilder().addAllCustomerInformation(customerInformations).build());
    }
}

