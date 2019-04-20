package souless.game.bundle.world;

import org.springframework.context.ApplicationEventPublisher;
import souless.game.model.ResourceManager;
import souless.game.bundle.world.map.WorldObjectMapFactory;
import souless.game.bundle.world.entity.World;
import souless.game.bundle.world.entity.WorldFactory;
import souless.game.bundle.world.event.WorldUploadEvent;

public class WorldManager {
    // TODO: вычислить размеры в пикселях
    public final static float MAP_OBJECT_SIZE = 5f;
    private final int DEFAULT_WIDTH = 40;
    private final int DEFAULT_HEIGHT = 40;

    private World currentWorld;

    private ResourceManager resourceManager;
    private WorldObjectMapFactory worldObjectMapFactory;
    private WorldFactory worldFactory;
    private ApplicationEventPublisher eventPublisher;

    public WorldManager(
            ResourceManager resourceManager,
            WorldObjectMapFactory worldObjectMapFactory,
            WorldFactory worldFactory,
            ApplicationEventPublisher eventPublisher
    ) {
        this.currentWorld = null;

        this.resourceManager = resourceManager;
        this.worldObjectMapFactory = worldObjectMapFactory;
        this.worldFactory = worldFactory;
        this.eventPublisher = eventPublisher;
    }

    public void loadWorld(String saveFilename) {
//        this.currentWorld = this.resourceManager.loadWorld(saveFilename);
        this.currentWorld = this.worldFactory.getTestWorld();
        this.eventPublisher.publishEvent(new WorldUploadEvent(this, this.currentWorld));
    }
}
