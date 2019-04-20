package souless.game.view.objects;

import souless.game.model.ResourceManager;
import souless.game.objects.GameObject;

public class ViewObjectFactory {
    
    public enum viewType {
        SIMPLE(1),
        WALL_STYLE(0),
        ANIMATED(2);
        
        private final int id;
        viewType(int id)      { this.id = id; }
        public int getValue() { return id; }
        public static viewType getTypeByBalue(int id)
        {
            switch (id)
            {
                case 1:  return SIMPLE;
                case 0:  return WALL_STYLE;
                case 2:  return ANIMATED;
                default: return null;
            }
        }
    }
    
    /**
     * Создает представление тайла и связывает с ним выбранный объект
     * @param tile объект модели, содержащий всю информацию
     * @param resM
     * @return 
     */
    public static ViewObject getViewTile(GameObject tile, ResourceManager resM)
    {
        switch (resM.geTextureType(tile.getTextureId()))
        {
            case SIMPLE:
            {
                return new ViewSimple(tile);
            }
            case WALL_STYLE:
            {
                return new ViewWall(tile);
            }
            case ANIMATED:
            {
                return new ViewAnimated(tile);
            }
        }
        return null;
    }
}
