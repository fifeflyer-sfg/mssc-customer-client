package guru.springframework.msscbrewery.web.client;

import guru.springframework.msscbrewery.web.model.CustomerDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class CustomerClient {

    private final CustomerClientProperties properties;
    private final RestTemplate restTemplate;

    public CustomerClient(CustomerClientProperties properties, RestTemplateBuilder restTemplateBuilder) {
        this.properties = properties;
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDTO getCustomer(UUID id) {
        return restTemplate.getForObject(createEndpoint(id), CustomerDTO.class);
    }

    public URI createCustomer(CustomerDTO customer) {
        return restTemplate.postForLocation(createEndpoint(customer.getId()), customer);
    }

    public void updateCustomer(CustomerDTO customer) {
        restTemplate.put(createEndpoint(customer.getId()), customer);
    }

    public void deleteCustomer(UUID id) {
        restTemplate.delete(createEndpoint(id));
    }

    private String createEndpoint(UUID id) {
        return properties.getUrl() + "/api/v1/customer/" + id;
    }
}
