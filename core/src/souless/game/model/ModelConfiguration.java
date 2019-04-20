package souless.game.model;

import com.badlogic.gdx.assets.AssetManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {

    @Bean
    ResourceManager resourcesManager() {
        return new ResourceManager(
                this.assetManager(),
                this.textureManager()
        );
    }

    @Bean
    AssetManager assetManager() {
        return new AssetManager();
    }

    @Bean
    TextureManager textureManager() {
        return new TextureManager(this.assetManager());
    }
}
