package souless.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import souless.game.controller.InputController;
import souless.game.model.ConsoleLogData;
import souless.game.model.ResourcesManager;
import souless.game.model.World;
import souless.game.view.ObjectRenderer;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class SoulessGame extends ApplicationAdapter {
	private ApplicationContext context;
	private ResourcesManager resourcesManager;
	ObjectRenderer objectRenderer;
	World world;
	InputController input_controller;

	@Override
	public void create () {
		this.context = new AnnotationConfigApplicationContext(SoulessGame.class);
		this.resourcesManager = (ResourcesManager)this.context.getBean("ResourceManager");

		resourcesManager.setLogData(new ConsoleLogData());
		world = resourcesManager.loadWorld("test_world");
		objectRenderer = new ObjectRenderer(world, resourcesManager);
		input_controller = new InputController(world, objectRenderer, resourcesManager);
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
		resourcesManager.saveWorld(world);
	}
}
