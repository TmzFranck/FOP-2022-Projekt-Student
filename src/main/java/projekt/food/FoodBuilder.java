package projekt.food;

import java.util.List;

public interface FoodBuilder<F extends Food, C extends Food.Config, V extends Food.Variant<F,C>> {
    F build(C config, V variant, List<? extends Extra<? super C>> extras);
}
