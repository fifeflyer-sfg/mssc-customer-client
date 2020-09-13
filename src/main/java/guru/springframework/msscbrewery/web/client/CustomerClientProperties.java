package guru.springframework.msscbrewery.web.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sfg.brewery.api", ignoreUnknownFields = false)
@Data
public class CustomerClientProperties {

    private String url;
}
