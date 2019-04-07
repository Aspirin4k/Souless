/*
 * Класс отвечает за обработку нажатий клавиш
 */
package souless.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import souless.game.model.ResourcesManager;
import souless.game.model.world.entity.WorldResource;
import souless.game.objects.moving.EntityMoving;

public class InputController implements InputProcessor {

    private final WorldResource WORLD;
//    private final ObjectRenderer objectRenderer;
    private final ResourcesManager resManager;
    
    public InputController(WorldResource world, ResourcesManager resM)
    {
        WORLD = world;
//        objectRenderer = objR;
        resManager = resM;
    }
    
    @Override
    /**
     * Нажатие клавиши
     */
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Keys.NUMPAD_7:
            {
                WORLD.getPlayer().move(EntityMoving.Direction.LEFT_UP, WORLD.getMap());
//                resManager.getLogData().add("Координаты: " + WORLD.getPlayer().getPosition().x + ", " + WORLD.getPlayer().getPosition().y );
                break;
            }
            case Keys.NUMPAD_8:
            {
                WORLD.getPlayer().move(EntityMoving.Direction.UP, WORLD.getMap());
//                resManager.getLogData().add("Координаты: " + WORLD.getPlayer().getPosition().x + ", " + WORLD.getPlayer().getPosition().y );
                break;
            }
            case Keys.NUMPAD_9:
            {
                WORLD.getPlayer().move(EntityMoving.Direction.RIGHT_UP, WORLD.getMap());
//                resManager.getLogData().add("Координаты: " + WORLD.getPlayer().getPosition().x + ", " + WORLD.getPlayer().getPosition().y );
                break;
            }
            case Keys.NUMPAD_4:
            {
                WORLD.getPlayer().move(EntityMoving.Direction.LEFT, WORLD.getMap());
//                resManager.getLogData().add("Координаты: " + WORLD.getPlayer().getPosition().x + ", " + WORLD.getPlayer().getPosition().y );
                break;
            }
            case Keys.NUMPAD_6:
            {
                WORLD.getPlayer().move(EntityMoving.Direction.RIGHT, WORLD.getMap());
//                resManager.getLogData().add("Координаты: " + WORLD.getPlayer().getPosition().x + ", " + WORLD.getPlayer().getPosition().y );
                break;
            }
            case Keys.NUMPAD_1:
            {
                WORLD.getPlayer().move(EntityMoving.Direction.LEFT_DOWN, WORLD.getMap());
//                resManager.getLogData().add("Координаты: " + WORLD.getPlayer().getPosition().x + ", " + WORLD.getPlayer().getPosition().y );
                break;
            }
            case Keys.NUMPAD_2:
            {
                WORLD.getPlayer().move(EntityMoving.Direction.DOWN, WORLD.getMap());
//                resManager.getLogData().add("Координаты: " + WORLD.getPlayer().getPosition().x + ", " + WORLD.getPlayer().getPosition().y );
                break;
            }
            case Keys.NUMPAD_3:
            {
                WORLD.getPlayer().move(EntityMoving.Direction.RIGHT_DOWN, WORLD.getMap());
//                resManager.getLogData().add("Координаты: " + WORLD.getPlayer().getPosition().x + ", " + WORLD.getPlayer().getPosition().y );
                break;
            }
            case Keys.PAGE_UP:
            {
//                objectRenderer.listConsole(1);
                break;
            }
            case Keys.PAGE_DOWN:
            {
//                objectRenderer.listConsole(-1);
                break;
            }
            case Keys.ESCAPE:
            {
                Gdx.app.exit();
                break;
            }
            default:
            {
//                resManager.getLogData().add("Нажата клавиша " + keycode);
                break;
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
    
}
