package souless.game.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {
    @Bean
    ResourcesManager resourcesManager() {
        return new ResourcesManager();
    }
}
