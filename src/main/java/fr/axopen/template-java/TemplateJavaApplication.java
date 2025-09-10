package fr.axopen.template-java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("fr.axopen.template-java.config.properties")
public class TemplateJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateJavaApplication.class, args);
    }

}
