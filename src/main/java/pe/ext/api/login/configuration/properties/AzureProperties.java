package pe.ext.api.login.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "azure")
public class AzureProperties {
    private String authority;
    private String scope;
    private String clientId;
}
