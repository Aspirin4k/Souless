package souless.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import souless.game.model.ResourceManager;
import souless.game.bundle.world.WorldUploadConsumer;

@Configuration
public class ControllerConfiguration {
    @Autowired
    ResourceManager resourceManager;

    @Bean(name = "InputController")
    InputController inputController() {
        return new InputController(
                this.resourceManager
        );
    }

    @Bean(name = "ControllerWorldUploadConsumer")
    WorldUploadConsumer worldUploadConsumer() {
        return new WorldUploadConsumer(
                this.inputController()
        );
    }
}
