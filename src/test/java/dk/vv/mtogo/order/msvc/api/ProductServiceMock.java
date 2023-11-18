package dk.vv.mtogo.order.msvc.api;

import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
@Mock
@RestClient
public class ProductServiceMock implements ProductService{
    @Override
    public Response getProducts(List<Integer> ids) {
        return Response.ok("[{\"id\":1,\"productName\":\"test\",\"grossPrice\":187.50,\"netPrice\":150.00,\"description\":\"This is a test product\",\"supplierId\":2},{\"id\":101,\"productName\":\"Test product 2\",\"grossPrice\":100.00,\"netPrice\":125.00,\"description\":\"this is also a test product\",\"supplierId\":3}]").build();
    }
}
