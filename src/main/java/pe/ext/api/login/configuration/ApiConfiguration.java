package pe.ext.api.login.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pe.ext.api.login.configuration.properties.AzureProperties;

@Configuration
@ComponentScan("pe.ext.api.login")
public class ApiConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "azure")
    public AzureProperties azureProperties(){
        return new AzureProperties();
    }
}
