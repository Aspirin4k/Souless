package souless.game.bundle.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import souless.game.bundle.world.entity.WorldFactory;
import souless.game.bundle.world.map.WorldObjectMapEntryFactory;
import souless.game.bundle.world.map.WorldObjectMapFactory;
import souless.game.bundle.world.map.view.TileLayerRenderer;
import souless.game.bundle.world.object.WorldObjectFactory;
import souless.game.bundle.world.object.core.WorldObjectCoreFactory;
import souless.game.bundle.world.object.view.WorldObjectViewFactory;
import souless.game.bundle.world.view.WorldComponent;
import souless.game.model.ResourceManager;
import souless.game.model.TextureManager;

@Configuration
public class WorldConfiguration {
    @Autowired
    ResourceManager resourceManager;
    @Autowired
    TextureManager textureManager;
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    Batch spriteBatch;

    @Bean
    WorldManager worldManager() {
        return new WorldManager(
                this.resourceManager,
                this.worldObjectMapFactory(),
                this.worldFactory(),
                this.eventPublisher
        );
    }

    @Bean
    WorldObjectCoreFactory worldObjectCoreFactory() {
        return new WorldObjectCoreFactory();
    }

    @Bean
    WorldObjectViewFactory worldObjectViewFactory() {
        return new WorldObjectViewFactory(
                this.textureManager
        );
    }

    @Bean
    WorldObjectFactory worldObjectFactory() {
        return new WorldObjectFactory(
                this.worldObjectCoreFactory(),
                this.worldObjectViewFactory()
        );
    }

    @Bean
    WorldObjectMapEntryFactory worldObjectMapEntryFactory() {
        return new WorldObjectMapEntryFactory();
    }

    @Bean
    WorldObjectMapFactory worldObjectMapFactory() {
        return new WorldObjectMapFactory(
                this.worldObjectFactory(),
                this.worldObjectMapEntryFactory()
        );
    }

    @Bean
    WorldFactory worldFactory() {
        return new WorldFactory(
                this.worldObjectMapFactory()
        );
    }

    @Bean
    TileLayerRenderer tileLayerRenderer() {
        return new TileLayerRenderer(
                this.spriteBatch
        );
    }

    @Bean(name = "WorldComponent")
    WorldComponent worldComponent() {
        return new WorldComponent(
                this.resourceManager,
                this.spriteBatch,
                this.tileLayerRenderer()
        );
    }
}
