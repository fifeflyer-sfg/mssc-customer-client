package guru.springframework.msscbrewery.web.client;

import guru.springframework.msscbrewery.web.model.CustomerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerClientTest {

    private static final UUID CUSTOMER_ID = UUID.randomUUID();
    private static final String CUSTOMER_NAME = "Bruce Wayne";

    @Autowired
    private CustomerClient customerClient;

    @Test
    public void getCustomer() {
        CustomerDTO customer = customerClient.getCustomer(CUSTOMER_ID);

        assertThat(customer.getCustomerName(), is(equalTo(CUSTOMER_NAME)));
    }

    @Test
    public void createCustomer() {
        URI uri = customerClient.createCustomer(buildCustomer());

        assertThat(uri.toString(), is(equalTo("http://localhost:8080/api/v1/customer/" + CUSTOMER_ID)));
    }

    @Test
    public void updateCustomer() {
        customerClient.updateCustomer(buildCustomer());
    }

    @Test
    public void deleteCustomer() {
        customerClient.deleteCustomer(CUSTOMER_ID);
    }

    private CustomerDTO buildCustomer() {
        return CustomerDTO.builder()
            .id(CUSTOMER_ID)
            .customerName(CUSTOMER_NAME)
            .build();
    }
}