package souless.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import souless.game.controller.InputController;
import souless.game.model.ConsoleLogData;
import souless.game.model.ResourcesManager;
import souless.game.model.World;
import souless.game.view.IComponent;

public class SoulessGame extends ApplicationAdapter {
	private ApplicationContext context;
	private ResourcesManager resourcesManager;
	private IComponent renderer;
	World world;
	InputController input_controller;

	@Override
	public void create () {
		this.context = new AnnotationConfigApplicationContext(SoulessConfiguration.class);
		this.resourcesManager = this.context.getBean(ResourcesManager.class);
		this.renderer = (IComponent)this.context.getBean("GameComponent");

		resourcesManager.setLogData(new ConsoleLogData());
		world = resourcesManager.loadWorld("test_world");
		input_controller = new InputController(world, resourcesManager);
		Gdx.input.setInputProcessor(input_controller);
	}

	@Override
	/**
	 * Рендер
	 */
	public void render ()
	{
		this.renderer.render();
	}

//	@Override
//	public void resize(int width, int height)
//	{
//		this.renderer.resize(width, height);
//	}

	@Override
	/**
	 * Происходит при закрытии игыр
	 */
	public void dispose () {
		resourcesManager.saveWorld(world);
	}
}
