package projekt.food;

import java.util.List;

/**
 * build Food config.
 * @param <F> food typ
 * @param <C> config typ
 * @param <V> variant typ
 */
@FunctionalInterface
public interface FoodBuilder<F extends Food, C extends Food.Config, V extends Food.Variant<F, C>> {
    /**
     * build Food.
     * @param config configuration of food
     * @param variant of food
     * @param extras of food
     * @return food.
     */
    F build(C config, V variant, List<? extends Extras> extras);

}
