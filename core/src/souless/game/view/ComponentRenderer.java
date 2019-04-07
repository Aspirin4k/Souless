package souless.game.view;

import java.util.ArrayList;
import java.util.Arrays;

public class ComponentRenderer implements IComponent {

    private ArrayList<IComponent> components;

    ComponentRenderer(IComponent... components) {
        this.components = new ArrayList<IComponent>(Arrays.asList(components));
    }

    @Override
    public void render() {
        for (IComponent component: this.components) {
            component.render();
        }
    }
}
