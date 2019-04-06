package souless.game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import souless.game.model.ResourcesManager;
import souless.game.model.World;
import souless.game.view.components.GameComponent;

@Configuration
public class ViewConfiguration {
    @Autowired
    ResourcesManager resourcesManager;

    @Bean(name = "GameComponent")
    IComponent gameComponent() {
        World world = resourcesManager.loadWorld("test_world");
        return new GameComponent(world, this.resourcesManager);
    }
}
