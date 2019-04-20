package souless.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import souless.game.bundle.world.view.WorldComponent;
import souless.game.model.ResourceManager;
import souless.game.bundle.world.WorldUploadConsumer;

@Configuration
public class ViewConfiguration {
    @Autowired
    ResourceManager resourceManager;
    @Autowired
    WorldComponent worldComponent;

    @Bean
    Graphics graphics() {
        return Gdx.graphics;
    }

    @Bean
    GL20 gl20() {
        return Gdx.gl;
    }

    @Bean(name = "WorldCamera")
    Camera worldCamera() {
        return new OrthographicCamera();
    }

    @Bean(name = "SpriteBatch")
    SpriteBatch spriteBatch() {
        return new SpriteBatch();
    }

    @Bean(name = "WorldViewPort")
    Viewport worldViewPort() {
        return new ScreenViewport(this.worldCamera());
    }



    @Bean(name = "VoidComponent")
    VoidComponent voidComponent() {
        return new VoidComponent(
                this.gl20()
        );
    }

    @Bean(name = "ComponentRenderer")
    IComponent componentRenderer() {
        return new ComponentRenderer(
                this.voidComponent(),
                this.worldComponent
        );
    }

    @Bean(name = "ViewWorldUploadConsumer")
    WorldUploadConsumer worldUploadConsumer() {
        return new WorldUploadConsumer(
                this.worldComponent
        );
    }
}
