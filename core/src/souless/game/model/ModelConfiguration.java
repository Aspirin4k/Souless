package souless.game.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import souless.game.model.world.WorldManager;
import souless.game.model.world.WorldUploadConsumer;
import souless.game.view.world.WorldComponent;

@Configuration
public class ModelConfiguration {
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Bean
    ResourcesManager resourcesManager() {
        return new ResourcesManager();
    }

    @Bean
    WorldManager worldManager() {
        return new WorldManager(
                this.resourcesManager(),
                this.eventPublisher
        );
    }
}
