package souless.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import souless.game.model.ResourcesManager;
import souless.game.model.world.WorldUploadConsumer;

@Configuration
public class ControllerConfiguration {
    @Autowired
    ResourcesManager resourcesManager;

    @Bean(name = "InputController")
    InputController inputController() {
        return new InputController(
                this.resourcesManager
        );
    }

    @Bean(name = "ControllerWorldUploadConsumer")
    WorldUploadConsumer worldUploadConsumer() {
        return new WorldUploadConsumer(
                this.inputController()
        );
    }
}
