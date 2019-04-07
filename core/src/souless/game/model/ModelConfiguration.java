package souless.game.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import souless.game.model.world.WorldManager;

@Configuration
public class ModelConfiguration {
    @Bean
    ResourcesManager resourcesManager() {
        return new ResourcesManager();
    }

    @Bean
    WorldManager worldManager() {
        WorldManager worldManager = new WorldManager(
                this.resourcesManager()
        );
        // TODO: Костыль
        worldManager.loadWorld("test_world");

        return worldManager;
    }
}
