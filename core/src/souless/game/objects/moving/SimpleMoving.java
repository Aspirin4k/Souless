package souless.game.objects.moving;

import souless.game.model.GameMap;
import souless.game.objects.GameObject;

public class SimpleMoving implements EntityMoving {

    @Override
    public boolean move(GameObject obj, Direction d, GameMap map) {
        boolean Result=false;
        
        switch (d)
        {
            case LEFT_UP:
            {
                Result = map.getTileByCoordinates(obj.getPosition().x-1, obj.getPosition().y+1).tryEnter(obj);
                if (Result)
                    obj.changePosition(-1, 1);
                break;
            }
            case UP:
            {
                Result = map.getTileByCoordinates(obj.getPosition().x, obj.getPosition().y+1).tryEnter(obj);
                if (Result)
                    obj.changePosition(0, 1);
                break;
            }
            case RIGHT_UP:
            {
                Result = map.getTileByCoordinates(obj.getPosition().x+1, obj.getPosition().y+1).tryEnter(obj);
                if (Result)
                    obj.changePosition(1, 1);
                break;
            }
            case LEFT:
            {
                Result = map.getTileByCoordinates(obj.getPosition().x-1, obj.getPosition().y).tryEnter(obj);
                if (Result)
                    obj.changePosition(-1, 0);
                break;
            }
            case RIGHT:
            {
                Result = map.getTileByCoordinates(obj.getPosition().x+1, obj.getPosition().y).tryEnter(obj);
                if (Result)
                    obj.changePosition(1, 0);
                break;
            }
            case LEFT_DOWN:
            {
                Result = map.getTileByCoordinates(obj.getPosition().x-1, obj.getPosition().y-1).tryEnter(obj);
                if (Result)
                    obj.changePosition(-1, -1);
                break;
            }
            case DOWN:
            {
                Result = map.getTileByCoordinates(obj.getPosition().x, obj.getPosition().y-1).tryEnter(obj);
                if (Result)
                    obj.changePosition(0, -1);
                break;
            }
            case RIGHT_DOWN:
            {
                Result = map.getTileByCoordinates(obj.getPosition().x+1, obj.getPosition().y-1).tryEnter(obj);
                if (Result)
                    obj.changePosition(1, -1);
                break;
            }
        }
        return Result;
    }
    
}
