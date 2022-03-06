package projekt.food;

import java.math.BigDecimal;
import java.util.List;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class IceCreamImpl implements IceCream {
    /**
     * price of ice cream.
     */
    private final BigDecimal price;
    /**
     * flavor of ice cream.
     */
    private final String flavor;
    /**
     * weight of ice cream.
     */
    private final double weight;
    /**
     * food variant of ice cream.
     */
    private final Food.Variant<IceCream, IceCream.Config> foodVariant;
    /**
     * extra of ice cream.
     */
    private final List<Extra<IceCream.Config>> extras;

    /**
     * config the ice cream.
     * @param price the price of ice cream
     * @param flavor the flavor of ice cream
     * @param weight the weight of ice cream
     * @param foodVariant the food variant of ice cream
     * @param extras extra of ice cream
     */
    public IceCreamImpl(final BigDecimal price, final String flavor,
                        final double weight, final Food.Variant<IceCream,IceCream.Config> foodVariant,
                        final List<Extra<IceCream.Config>> extras) {
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
    public Food.Variant<IceCream, IceCream.Config> getFoodVariant() {
        return foodVariant;
    }

    /**
     * The extras that this food was configured with.
     *
     * @return The extras that this food was configured with
     */
    @Override
    public List<Extra<IceCream.Config>> getExtras() {
        return extras;
    }

    /**
     * @return the flavor of the ice cream
     */
    @Override
    public String getFlavor() {
        return flavor;
    }

    private static class Config implements Food.Config {

        /**
         * the extra price for the configuration.
         */
        private final UnaryOperator<BigDecimal> priceMutator;
        /**
         * the extra weight for the configuration.
         */
        private final DoubleUnaryOperator weightMutator;

        private Config(final UnaryOperator<BigDecimal> priceMutator, final DoubleUnaryOperator weightMutator) {
            this.priceMutator = priceMutator;
            this.weightMutator = weightMutator;
        }

        /**
         * Concatenates the result of all previous calls to this method with the provided {@code priceMutator}.
         *
         * <p>
         * The provided {@link UnaryOperator} accepts the result produced by the function provided to the previous call to this
         * method and produces a new price. The new price does not necessarily have to be different from the previous one, and
         * may even be exactly the same value.
         * </p>
         *
         * @param priceMutator A {@link UnaryOperator} which determines a new price based on the previous value
         */
        @Override
        public void price(final UnaryOperator<BigDecimal> priceMutator) {

        }

        /**
         * The price mutator accepts a base price and produces a configured price.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #price(UnaryOperator)} method.
         * </p>
         *
         * @return The price mutation function
         */
        @Override
        public UnaryOperator<BigDecimal> getPriceMutator() {
            return priceMutator;
        }

        /**
         * Concatenates the result of all previous calls to this method with the provided {@code weightMutator}.
         *
         * <p>
         * The provided {@link DoubleUnaryOperator} accepts the result produced by the function provided to the previous call to
         * this method and produces a new weight. The new weight does not necessarily have to be different from the previous
         * one, and may even be exactly the same value.
         * </p>
         *
         * @param weightMutator A {@link DoubleUnaryOperator} which determines a new weight based on the previous value
         */
        @Override
        public void weight(final DoubleUnaryOperator weightMutator) {

        }

        /**
         * The weight mutator accepts a base weight and produces a configured weight.
         *
         * <p>
         * The function returned by this method is the result of concatenating all previous inputs into the
         * {@link #weight(DoubleUnaryOperator)}  method.
         * </p>
         *
         * @return The weight mutation function
         */
        @Override
        public DoubleUnaryOperator getWeightMutator() {
            return weightMutator;
        }
    }
}
