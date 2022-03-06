package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class PizzaImpl implements Pizza {
    private final double diameter;
    private final BigDecimal price;
    private final String sauce;
    private final double weight;
    private final Food.Variant<Pizza,Pizza.Config> pizzaVariant;
    private final List<Extra<Pizza.Config>> extras;

    public PizzaImpl(double diameter, BigDecimal price, String sauce, double weight, Food.Variant<Pizza,
                    Pizza.Config> pizzaVariant, List<Extra<Pizza.Config>> extras, List<Config> extras1) {
        this.diameter = diameter;
        this.price = price;
        this.sauce = sauce;
        this.weight = weight;
        this.pizzaVariant = pizzaVariant;
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
        return pizzaVariant;
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
     * @return the diameter of a pizza
     */
    @Override
    public double getDiameter() {
        return diameter;
    }

    /**
     * description of sauce
     *
     * @return a string identifying the sauce
     */
    @Override
    public String getSauce() {
        return sauce;
    }
}
