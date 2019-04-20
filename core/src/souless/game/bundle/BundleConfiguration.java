package souless.game.bundle;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import souless.game.bundle.world.WorldConfiguration;

@Configuration
@Import(
        WorldConfiguration.class
)
public class BundleConfiguration {
}
