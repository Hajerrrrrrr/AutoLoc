package tn.esprit.autoloc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

// Active la prise en compte des méthodes annotées @Scheduled
@Configuration
@EnableScheduling
public class SchedulingConfig {
}
