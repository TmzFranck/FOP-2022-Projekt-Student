package projekt.food;

import java.math.BigDecimal;
import java.util.List;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class IceCreamImpl implements IceCream {
    private final BigDecimal price;
    private final String flavor;
    private final double weight;
    private final Food.Variant<IceCream,IceCream.Config> foodVariant;
    private final List<Extra<IceCream.Config>> extras;

    public IceCreamImpl(BigDecimal price, String flavor,
                        double weight, Food.Variant<IceCream, Config> foodVariant, List<Extra<Config>> extras) {
        this.price = price;
        this.flavor = flavor;
        this.weight = weight;
        this.foodVariant = foodVariant;
        this.extras = extras;
    }

    /**
     * The price of this food.
     *
     * <p>
     * This is defined using the base value from {@link Variant#getBasePrice()} and configured
     * using {@link Config#price(UnaryOperator)}.
     * </p>
     *
     * @return The price of this food
     */
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * The weight of this food.
     *
     * <p>
     * This is defined using the base value from {@link Variant#getBaseWeight()} and configured
     * using {@link Config#weight(DoubleUnaryOperator)}.
     * </p>
     *
     * @return The weight of this food
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * The food variant.
     *
     * @return The food variant
     */
    @Override
    public Food.Variant<?, ?> getFoodVariant() {
        return foodVariant;
    }

    /**
     * The extras that this food was configured with.
     *
     * @return The extras that this food was configured with
     */
    @Override
    public List<? extends Extra<?>> getExtras() {
        return extras;
    }

    /**
     * @return the flavor of the ice cream
     */
    @Override
    public String getFlavor() {
        return flavor;
    }
}
