package souless.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import souless.game.model.ResourcesManager;
import souless.game.model.world.WorldManager;

@Configuration
public class ControllerConfiguration {
    @Autowired
    WorldManager worldManager;
    @Autowired
    ResourcesManager resourcesManager;

    @Bean(name = "InputController")
    InputController inputController() throws Exception {
        return new InputController(
                this.worldManager.getCurrentWorld(),
                this.resourcesManager
        );
    }
}
