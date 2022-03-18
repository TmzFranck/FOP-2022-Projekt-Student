package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class IceCreamImpl implements IceCream {
    public final static IceCream.Variant VANILLA= new IceCreamImpl.Variant(
        "Vanilla", BigDecimal.valueOf(1.5), 0.2, "Vanilla");

    /**
     * food config of ice cream.
     */
    private final IceCream.Config foodConfig;
    /**
     * food variant of ice cream.
     */
    private final IceCream.Variant foodVariant;
    /**
     * extra of ice cream.
     */
    private final List<? extends Extra<? super IceCream.Config>> extras;

    static FoodBuilder<IceCream, IceCream.Config, IceCream.Variant> BUILDER =
        (c, v, e) -> new IceCreamImpl(c, v, e);

    /**
     * config the ice cream.
     * @param foodVariant the food config of ice cream
     * @param foodVariant the food variant of ice cream
     * @param extras extra of ice cream
     */
    public IceCreamImpl(IceCream.Config foodConfig,
                        IceCream.Variant foodVariant,
                        List<? extends Extra<? super IceCream.Config>> extras) {
        this.foodConfig = foodConfig;
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
        return foodConfig.getPriceMutator().apply(foodVariant.getBasePrice());
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
        return foodConfig.getWeightMutator().applyAsDouble(foodVariant.getBaseWeight());
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
    public List<? extends Extra<? super IceCream.Config>> getExtras() {
        return extras;
    }

    /**
     * @return the flavor of the ice cream
     */
    @Override
    public String getFlavor() {
        String baseFlavor = foodVariant.getBaseFlavor();
        String flavor = foodConfig.getFlavorMutator().apply(baseFlavor);
        return flavor;
    }

    private static class Config implements IceCream.Config {

        /**
         * the extra price for the configuration.
         */
        private List<UnaryOperator<BigDecimal>> priceMutators = new ArrayList<>();
        /**
         * the extra weight for the configuration.
         */
        private List<DoubleUnaryOperator> weightMutators = new ArrayList<>();

        private List<UnaryOperator<String>> flavorMutators = new ArrayList<>();

        private Config(UnaryOperator<BigDecimal> priceMutator, DoubleUnaryOperator weightMutator, UnaryOperator<String> flavorMutator) {
            this.priceMutators.add(priceMutator);
            this.weightMutators.add(weightMutator);
            this.flavorMutators.add(flavorMutator);
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
            priceMutators.add(priceMutator);
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
            return preis ->{
                BigDecimal p = preis;
                for (UnaryOperator<BigDecimal> pm: this.priceMutators){
                    p = pm.apply(p);
                }
                return p;
            };
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
            weightMutators.add(weightMutator);
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
            return weight ->{
                double w = weight;
                for (DoubleUnaryOperator wm: this.weightMutators){
                    w = wm.applyAsDouble(w);
                }
                return w;
            };
        }

        /**
         * chain the current flavor with given flavorMutator.
         * and save it internally
         *
         * @param flavorMutator the given flavorMutator
         */
        @Override
        public void flavor(UnaryOperator<String> flavorMutator) {
            this.flavorMutators.add(flavorMutator);
        }

        /**
         * getter method returns the internally saved.
         * flavorMutator
         *
         * @return internally saved flavorMutator
         */
        @Override
        public UnaryOperator<String> getFlavorMutator() {
            return flavor -> {
                String f = flavor;
                for (UnaryOperator<String> fm: this.flavorMutators){
                    f = fm.apply(f);
                }
                return f;
            };
        }
    }

    private static class Variant implements IceCream.Variant {

        public Variant(String name, BigDecimal basePrice, double baseWeight, String baseFlavor) {
            this.baseFlavor = baseFlavor;
            this.name = name;
            this.basePrice = basePrice;
            this.baseWeight = baseWeight;
            this.foodType = FoodTypes.ICE_CREAM;
        }

        private String name;
        private BigDecimal basePrice;
        private double baseWeight;
        private String baseFlavor;
        private FoodType<IceCream, IceCream.Config> foodType;
        /**
         * The name of this variant.
         *
         * <p>
         * This may be something similar to {@code "Pizza Margherita"}.
         * </p>
         *
         * @return The name of this variant
         */
        @Override
        public String getName() {
            return this.name;
        }

        /**
         * The food type in which this variant is grouped.
         *
         * <p>
         * For example, if this variant was named {@code "Pizza Margherita"}, the matching food type would be {@code "Pizza"}.
         * </p>
         *
         * @return The food type of this variant
         */
        @Override
        public FoodType<IceCream, IceCream.Config> getFoodType() {
            return this.foodType;
        }

        /**
         * The base price of this variant.
         *
         * @return The base price of this variant
         */
        @Override
        public BigDecimal getBasePrice() {
            return this.basePrice;
        }

        /**
         * The base weight of this variant.
         *
         * @return The weight price of this variant
         */
        @Override
        public double getBaseWeight() {
            return this.baseWeight;
        }

        /**
         * Creates an empty {@link Config} for this variant.
         *
         * @return An empty {@link Config} for this variant
         */
        @Override
        public IceCream.Config createEmptyConfig() {
            return new IceCreamImpl.Config(p -> p, w -> w, f -> f) {
            };
        }

        /**
         * Creates a new instance of {@link Food} described by this variant, its base values and modifications defined by the
         * provided list of {@link Extra Extras}.
         *
         * <p>
         * The provided extras are applied to an instance of {@link Config}. After this config has
         * been fully "configured" by the extras, the base values from this variant are supplied to the config's mutators to
         * calculate the food's concrete values. Providing an empty list will create a food with the base values for this
         * variant.
         * </p>
         *
         * @param extras The list of {@link Extra Extras} to configure the resultant {@link Food}
         * @return An instance of {@link Food} based on the values from this variant and configured by the provided extras
         */
        @Override
        public IceCream create(List<? extends Extra<? super IceCream.Config>> extras) {
            IceCream.Config config = createEmptyConfig();
            Extra.writeToConfig(config, extras);
            return BUILDER.build(config, this, extras);
        }


        /**
         * Getter method returns the base flavor.
         *
         * @return the base flavor
         */
        @Override
        public String getBaseFlavor() {
            return baseFlavor;
        }
    }


}
