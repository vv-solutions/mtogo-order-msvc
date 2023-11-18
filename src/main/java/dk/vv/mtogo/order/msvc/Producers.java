package dk.vv.mtogo.order.msvc;

//import dk.vv.mtogo.order.msvc.message.MessageService;
import dk.vv.mtogo.order.msvc.facades.OrderFacade;
import dk.vv.mtogo.order.msvc.repositories.OrderRepository;

import io.quarkiverse.rabbitmqclient.RabbitMQClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;


@ApplicationScoped
public class Producers {

    @Produces
    public OrderRepository getOrderRepository() {
        return new OrderRepository();
    }

    @Inject
    Configuration configuration;

    @Inject
    RabbitMQClient rabbitMQClient;

    @Produces
    public OrderFacade getOrderFacade(OrderRepository repository){
        return new OrderFacade(repository);
    }


//    @Produces
//    public MessageService getMessageService(Logger logger){
//        return new MessageService(logger,rabbitMQClient,configuration);
//
//    }
}
