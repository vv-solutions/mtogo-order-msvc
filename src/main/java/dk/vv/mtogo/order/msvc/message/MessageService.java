package dk.vv.mtogo.order.msvc.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import dk.vv.mtogo.order.msvc.Configuration;
import dk.vv.mtogo.order.msvc.facades.OrderFacade;
import io.quarkiverse.rabbitmqclient.RabbitMQClient;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

public interface MessageService {
    void onApplicationStart(@Observes StartupEvent event);
    void setupQueues();
    void listenOnOrderStatusQueue();

    void sendOrderCreationMessage(Object o);

}
