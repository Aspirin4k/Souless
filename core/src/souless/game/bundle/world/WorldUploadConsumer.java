package souless.game.bundle.world;

import org.springframework.context.ApplicationListener;
import souless.game.bundle.world.event.WorldUploadEvent;

public class WorldUploadConsumer implements ApplicationListener<WorldUploadEvent> {
    IWorldUploadListener[] listeners;

    public WorldUploadConsumer(IWorldUploadListener... listeners) {
        this.listeners = listeners;
    }

    public void onApplicationEvent(WorldUploadEvent event) {
        for (IWorldUploadListener listener: this.listeners) {
            listener.onUploadWorld(event.getNewWorld());
        }
    }
}