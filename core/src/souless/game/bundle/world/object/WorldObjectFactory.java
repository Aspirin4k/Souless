package souless.game.bundle.world.object;

import souless.game.bundle.world.object.core.WorldObjectCoreFactory;
import souless.game.bundle.world.object.view.WorldObjectViewFactory;

public class WorldObjectFactory {
    private final WorldObjectCoreFactory worldObjectCoreFactory;
    private final WorldObjectViewFactory worldObjectViewFactory;

    public WorldObjectFactory(
            WorldObjectCoreFactory worldObjectCoreFactory,
            WorldObjectViewFactory worldObjectViewFactory
    ) {
        this.worldObjectCoreFactory = worldObjectCoreFactory;
        this.worldObjectViewFactory = worldObjectViewFactory;
    }

    public WorldObject getTestTile() {
        return new WorldObject(
            this.worldObjectCoreFactory.getVoidCore(),
            this.worldObjectViewFactory.getSimpleView("dungeon_floor")
        );
    }
}
