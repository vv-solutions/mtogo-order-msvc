package dk.vv.mtogo.order.msvc;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "order.msvc", namingStrategy = ConfigMapping.NamingStrategy.VERBATIM)
public interface Configuration {


    QueueConfig queues();

    public interface QueueConfig {

        interface InQueue {
            String queue();

            String exchange();

        }

        interface OutQueue {
            String exchange();
        }
        InQueue orderStatus();
        OutQueue orderCreation();

    }


}
