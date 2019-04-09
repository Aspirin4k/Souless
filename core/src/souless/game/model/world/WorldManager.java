package souless.game.model.world;

import org.springframework.context.ApplicationEventPublisher;
import souless.game.model.ResourcesManager;
import souless.game.model.world.entity.WorldResource;
import souless.game.model.world.event.WorldUploadEvent;
import souless.game.model.world.exception.WorldNotLoadedException;

public class WorldManager {
    private WorldResource currentWorld;

    private ResourcesManager resourcesManager;
    private ApplicationEventPublisher eventPublisher;

    public WorldManager(
            ResourcesManager resourcesManager,
            ApplicationEventPublisher eventPublisher
    ) {
        this.currentWorld = null;

        this.resourcesManager = resourcesManager;
        this.eventPublisher = eventPublisher;
    }

    public WorldResource getCurrentWorld() throws WorldNotLoadedException {
        if (null == this.currentWorld) {
            throw new WorldNotLoadedException("WorldManager was not initialized");
        }

        return this.currentWorld;
    }

    public void loadWorld(String saveFilename) {
        this.currentWorld = this.resourcesManager.loadWorld(saveFilename);
        this.eventPublisher.publishEvent(new WorldUploadEvent(this, this.currentWorld));
    }
}
