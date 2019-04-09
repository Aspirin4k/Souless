package souless.game.model.world.event;

import org.springframework.context.ApplicationEvent;
import souless.game.model.world.entity.WorldResource;

public class WorldUploadEvent extends ApplicationEvent {
    private WorldResource newWorld;

    public WorldUploadEvent(Object source, WorldResource newWorld) {
        super(source);
        this.newWorld = newWorld;
    }

    public WorldResource getNewWorld() {
        return this.newWorld;
    }
}
