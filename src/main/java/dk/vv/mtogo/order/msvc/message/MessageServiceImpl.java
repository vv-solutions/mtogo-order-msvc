package dk.vv.mtogo.order.msvc.message;//package dk.vv.mtogo.order.msvc.message;
//
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import dk.vv.common.data.transfer.objects.order.OrderDTO;
import dk.vv.mtogo.order.msvc.Configuration;
import dk.vv.mtogo.order.msvc.facades.OrderFacade;
import io.quarkiverse.rabbitmqclient.RabbitMQClient;
import io.quarkus.arc.profile.UnlessBuildProfile;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
@UnlessBuildProfile("test")
public class MessageServiceImpl implements MessageService {

    @Inject
    private  Logger logger;

    @Inject
    private  RabbitMQClient rabbitMQClient;


    private  Configuration configuration;

    private Channel channel;

    private final OrderFacade orderFacade;

    private ObjectMapper mapper = new ObjectMapper();


    public MessageServiceImpl(Logger logger, RabbitMQClient rabbitMQClient, Configuration configuration, OrderFacade orderFacade) {
        this.logger = logger;
        this.rabbitMQClient = rabbitMQClient;
        this.configuration = configuration;
        this.orderFacade = orderFacade;
    }

    @Override
    public void onApplicationStart(@Observes StartupEvent event) {
        // on application start prepare the queues and message listener
        setupQueues();
        listenOnOrderStatusQueue();
    }

    @Override
    public void setupQueues() {
        try {
            // create a connection
            Connection connection = rabbitMQClient.connect();

            // create a channel
            channel = connection.createChannel();

            // declare queue
            channel.queueDeclare(configuration.queues().orderStatus().queue(), true, false, false, null);

            // bind queue to exchange
            channel.queueBind(configuration.queues().orderStatus().queue(),configuration.queues().orderStatus().exchange(),"#");

        } catch (IOException e) {

            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void listenOnOrderStatusQueue() {
        try {
            channel.basicConsume(configuration.queues().orderStatus().queue(), true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    var order = mapper.readValue(body, OrderDTO.class);

                    logger.infof("status: received information about order [%d] with status [%s]",order.getId(),order.getStatusId());

                    // update order status
                    orderFacade.handleStatusUpdate(order);
                }
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void sendOrderCreationMessage(OrderDTO o) {
        try {
            // send a message to the exchange

            String message = mapper.writeValueAsString(o);

            logger.infof("order creation: Sent order creation message for order: [%d]",o.getId());

            channel.basicPublish(configuration.queues().orderCreation().exchange(), "#", null, message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}



