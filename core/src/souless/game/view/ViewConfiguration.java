package souless.game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import souless.game.model.ResourcesManager;
import souless.game.model.world.WorldUploadConsumer;
import souless.game.view.world.WorldComponent;

@Configuration
public class ViewConfiguration {
    @Autowired
    ResourcesManager resourcesManager;

    @Bean(name = "WorldComponent")
    WorldComponent worldComponent() {
        return new WorldComponent(
                this.resourcesManager
        );
    }

    @Bean(name = "ComponentRenderer")
    IComponent componentRenderer() throws Exception {
        return new ComponentRenderer(
                this.worldComponent()
        );
    }

    @Bean(name = "ViewWorldUploadConsumer")
    WorldUploadConsumer worldUploadConsumer() {
        return new WorldUploadConsumer(
                this.worldComponent()
        );
    }
}
