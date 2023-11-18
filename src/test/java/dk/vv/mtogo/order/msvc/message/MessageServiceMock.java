package dk.vv.mtogo.order.msvc.message;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
public class MessageServiceMock implements MessageService {


    @Override
    public void onApplicationStart(StartupEvent event) {
    }

    @Override
    public void setupQueues() {
    }

    @Override
    public void listenOnOrderStatusQueue() {
    }

    @Override
    public void sendOrderCreationMessage(Object o) {
    }
}
