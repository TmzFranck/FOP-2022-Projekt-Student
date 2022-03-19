package projekt.food;

import java.util.ArrayList;
import java.util.List;

/**
 * type food.
 * @param <F>
 * @param <C>
 */
public class FoodTypeImpl<F extends Food, C extends Food.Config> implements FoodType<F,C> {

    private final String name;
    private final List<? extends Extra<? super C>> compatibleExtras;
    private final List<Food.Variant<F,C>> foodVariants;

    /**
     * constructor of the FoodTypeImpl
     * @param name name of the food
     * @param compatibleExtras compatible extras for this category of food
     */
    public FoodTypeImpl(String name, List<? extends Extra<? super C>> compatibleExtras) {
        this.name = name;
        this.compatibleExtras = compatibleExtras;
        this.foodVariants = new ArrayList<>();
    }

    /**
     * The name of this food type.
     *
     * <p>
     * This may be something similar to {@code "Pizza"}.
     * </p>
     *
     * @return The name of this type
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * The {@link Extra Extras} compatible with this food type.
     *
     * @return The {@link Extra Extras} compatible with this food type
     */
    @Override
    public List<? extends Extra<? super C>> getCompatibleExtras() {
        return this.compatibleExtras;
    }

    /**
     * Adds a {@link Food.Variant} to this food type.
     *
     * @param variant The {@link Food.Variant} to add to this food type
     */
    @Override
    public void addFoodVariant(Food.Variant<F, C> variant) {
        this.foodVariants.add(variant);
    }

    /**
     * The {@link Food.Variant food variants} that this food type are part of.
     *
     * <p>
     * For example, if this food type is "Pizza", this method might return the variants named:
     * </p>
     * <ul>
     *     <li>"Pizza Margherita"</li>
     *     <li>"Pizza Hawaii"</li>
     *     <li>"Pizza Rucola"</li>
     *     <li>"Pizza BBQ"</li>
     * </ul>
     *
     * @return The {@link Food.Variant food variants} that this food type are part of
     */
    @Override
    public List<? extends Food.Variant<F, C>> getFoodVariants() {
        return this.foodVariants;
    }
}
