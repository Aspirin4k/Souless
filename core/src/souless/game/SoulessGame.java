package souless.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import souless.game.controller.InputController;
import souless.game.view.IComponent;

public class SoulessGame extends ApplicationAdapter {
	private ApplicationContext context;
	private InputController inputController;
	private IComponent renderer;

	@Override
	public void create () {
		this.context = new AnnotationConfigApplicationContext(SoulessConfiguration.class);
		this.renderer = (IComponent)this.context.getBean("ComponentRenderer");
		this.inputController = (InputController)this.context.getBean("InputController");

		Gdx.input.setInputProcessor(this.inputController);
	}

	@Override
	public void render ()
	{
		this.renderer.render();
	}

//	@Override
//	public void resize(int width, int height)
//	{
//		this.renderer.resize(width, height);
//	}

//	@Override
//	public void dispose () {
//		resourcesManager.saveWorld(world);
//	}
}
