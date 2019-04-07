package souless.game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import souless.game.model.ResourcesManager;
import souless.game.model.world.WorldManager;
import souless.game.model.world.exception.WorldNotLoadedException;
import souless.game.view.components.GameComponent;

@Configuration
public class ViewConfiguration {
    @Autowired
    ResourcesManager resourcesManager;
    @Autowired
    WorldManager worldManager;

    @Bean(name = "GameComponent")
    IComponent gameComponent() throws WorldNotLoadedException {
        return new GameComponent(
                this.worldManager.getCurrentWorld(),
                this.resourcesManager
        );
    }

    @Bean(name = "ComponentRenderer")
    IComponent componentRenderer() throws Exception {
        return new ComponentRenderer(
                this.gameComponent()
        );
    }
}
