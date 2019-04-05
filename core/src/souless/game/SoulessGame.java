package souless.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import souless.game.controller.InputController;
import souless.game.model.ConsoleLogData;
import souless.game.model.ResourcesManager;
import souless.game.model.World;
import souless.game.view.ObjectRenderer;

public class SoulessGame extends ApplicationAdapter {
	ObjectRenderer objectRenderer;
	World world;
	InputController input_controller;
	ResourcesManager resManager;

	@Override
	/**
	 * Происходит при создании формы
	 */
	public void create () {
		resManager = new ResourcesManager(ResourcesManager.ManagerMode.GAMEMODE);
		resManager.setLogData(new ConsoleLogData());
		world = resManager.loadWorld("test_world");
		objectRenderer = new ObjectRenderer(world, resManager);
		input_controller = new InputController(world, objectRenderer, resManager);
		Gdx.input.setInputProcessor(input_controller);
	}

	@Override
	/**
	 * Рендер
	 */
	public void render () {
		objectRenderer.render();
	}

	@Override
	public void resize(int width, int height)
	{
		this.objectRenderer.resize(width, height);
	}

	@Override
	/**
	 * Происходит при закрытии игыр
	 */
	public void dispose () {
		resManager.saveWorld(world);
	}
}
