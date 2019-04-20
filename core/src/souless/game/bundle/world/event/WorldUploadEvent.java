package souless.game.bundle.world.event;

import org.springframework.context.ApplicationEvent;
import souless.game.bundle.world.entity.World;

public class WorldUploadEvent extends ApplicationEvent {
    private World newWorld;

    public WorldUploadEvent(Object source, World newWorld) {
        super(source);
        this.newWorld = newWorld;
    }

    public World getNewWorld() {
        return this.newWorld;
    }
}
