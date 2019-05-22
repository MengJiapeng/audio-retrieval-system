package mjp.mp3searchengine;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class Mp3SearchEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mp3SearchEngineApplication.class, args);
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("solr.properties"), new ClassPathResource("configuration.properties"));
        return configurer;
    }


}
