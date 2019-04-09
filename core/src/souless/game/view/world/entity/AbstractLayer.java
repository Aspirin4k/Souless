package souless.game.view.world.entity;

abstract public class AbstractLayer {
    protected IViewWorldMap viewWorldMap;

    abstract void getView();

    public AbstractLayer(IViewWorldMap viewWorldMap) {
        this.viewWorldMap = viewWorldMap;
    }
}
