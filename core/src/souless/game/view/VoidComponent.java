package souless.game.view;

import com.badlogic.gdx.graphics.GL20;

/**
 * Данный класс отвечается за очищение сцены
 */
public class VoidComponent implements IComponent  {
    private final GL20 gl20;

    public VoidComponent(GL20 gl20) {
        this.gl20 = gl20;
        this.gl20.glClearColor(0,0,0,1);
    }

    @Override
    public void render() {
        this.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
