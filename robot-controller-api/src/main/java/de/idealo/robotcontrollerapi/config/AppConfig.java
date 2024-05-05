package de.idealo.robotcontrollerapi.config;

import de.idealo.robotcontrollerapi.config.AppConfig.GridConstraint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GridConstraint.class)
public class AppConfig {

    @ConfigurationProperties(prefix = "app.constraints")
    public record GridConstraint(int gridXLimit, int gridYLimit) {
    }
}
