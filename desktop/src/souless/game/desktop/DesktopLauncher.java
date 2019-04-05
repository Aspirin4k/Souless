package souless.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import souless.game.SoulessGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

//                config.width = 1078;
//                config.height = 720;
		config.width = 2560;
		config.height = 1080;
//                config.fullscreen = true;

		new LwjglApplication(new SoulessGame(), config);
	}
}
