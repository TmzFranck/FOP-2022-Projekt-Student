package projekt.food;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class PastaImpl implements Pasta{
    private final BigDecimal price;
    private final double weight;
    private final Food.Variant<Pasta, Pasta.Config>foodVariant;
    private final List<Extra<Pasta.Config>> extras;
    private final double thickness;
    private final String sauce;

    public PastaImpl(BigDecimal price, double weight, Food.Variant<Pasta, Pasta.Config> foodVariant,
                     List<Extra<Pasta.Config>> extras, double thickness, String sauce) {
        this.price = price;
        this.weight = weight;
        this.foodVariant = foodVariant;
        this.extras = extras;
        this.thickness = thickness;
        this.sauce = sauce;
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
    public Food.Variant<Pasta, Pasta.Config> getFoodVariant() {
        return foodVariant;
    }

    /**
     * The extras that this food was configured with.
     *
     * @return The extras that this food was configured with
     */
    @Override
    public List<Extra<Pasta.Config>> getExtras() {
        return extras;
    }

    /**
     * @return the thickness of the pasta
     */
    @Override
    public double getThickness() {
        return thickness;
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

    private static class Config implements Saucable.Config{
        private final UnaryOperator<BigDecimal> priceMutator;
        private final DoubleUnaryOperator weightMutator;
        private final UnaryOperator<String> sauceMutator;

        private Config(UnaryOperator<BigDecimal> priceMutator, DoubleUnaryOperator weightMutator,
                       UnaryOperator<String> sauceMutator) {
            this.priceMutator = priceMutator;
            this.weightMutator = weightMutator;
            this.sauceMutator = sauceMutator;
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
        public void price(UnaryOperator<BigDecimal> priceMutator) {

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
        public void weight(DoubleUnaryOperator weightMutator) {

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

        /**
         * chain the current sauce with given sauceMutator
         * and save it internally
         *
         * @param sauceMutator the given sauceMutator
         */
        @Override
        public void sauce(UnaryOperator<String> sauceMutator) {

        }

        /**
         * getter method returns the internally saved
         * sauceMutator
         *
         * @return the internally saved sauceMutator
         */
        @Override
        public UnaryOperator<String> getSauceMutator() {
            return sauceMutator;
        }
    }
}
