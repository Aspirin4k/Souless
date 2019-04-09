package souless.game.model.world.object;

import souless.game.view.world.object.IViewWorldObject;

/**
 * Объект класса представляет собой поверхность.
 * Поверхность не оказывает никакого взаимодействия с другими объектами, но содержит определенную текстуру.
 */
public class Tile extends AbstractWorldObject {
    private IViewWorldObject view;

    public Tile(IViewWorldObject view) {
        this.view = view;
    }

    public IViewWorldObject getView() {
        return this.view;
    }
}
