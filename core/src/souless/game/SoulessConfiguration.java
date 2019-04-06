package souless.game;

import org.springframework.context.annotation.*;
import souless.game.model.ModelConfiguration;
import souless.game.view.ViewConfiguration;

@Configuration
@Import({
        ViewConfiguration.class,
        ModelConfiguration.class
})
public class SoulessConfiguration {

}
