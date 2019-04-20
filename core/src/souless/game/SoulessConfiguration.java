package souless.game;

import org.springframework.context.annotation.*;
import souless.game.bundle.BundleConfiguration;
import souless.game.controller.ControllerConfiguration;
import souless.game.model.ModelConfiguration;
import souless.game.view.ViewConfiguration;

@Configuration
@Import({
        ViewConfiguration.class,
        ModelConfiguration.class,
        ControllerConfiguration.class,
        BundleConfiguration.class
})
public class SoulessConfiguration {
}
