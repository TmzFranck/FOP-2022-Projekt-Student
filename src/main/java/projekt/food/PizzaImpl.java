package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class PizzaImpl implements Pizza {
    static FoodBuilder<Pizza, Pizza.Config, Pizza.Variant> BUILDER =
        (c, v, e) -> new PizzaImpl(c, v, e);

    public final static Pizza.Variant MARGHERITA= new PizzaImpl.Variant(
        "Margherita", BigDecimal.valueOf(9.75), 0.8, "Tomato", 30.0
    );
    public final static Pizza.Variant HAWAII= new PizzaImpl.Variant(
        "Hawaii", BigDecimal.valueOf(13.75), 1.0, "Tomato", 30.0
    );
    public final static Pizza.Variant RUCOLA= new PizzaImpl.Variant(
        "Rucola", BigDecimal.valueOf(14.50), 0.9, "Tomato", 30.0
    );
    public final static Pizza.Variant BBQ= new PizzaImpl.Variant(
        "BBQ", BigDecimal.valueOf(14.50), 1.1, "BBQ", 30.0
    );

    /**
     * food config of pizza.
     */
    private final Pizza.Config pizzaConfig;
    /**
     * the pizza variant.
     */
    private final Pizza.Variant pizzaVariant;
    /**
     * the extra of pizza.
     */
    private final List<? extends Extra<? super Pizza.Config>> extras;

    /**
     * config the pizza.
     * @param pizzaConfig the pizza config
     * @param pizzaVariant the pizza variant
     * @param extras the extra of pizza
     */
    public PizzaImpl(Pizza.Config pizzaConfig,
                     Pizza.Variant pizzaVariant,
                     List<? extends Extra<? super Pizza.Config>> extras) {
        this.pizzaConfig = pizzaConfig;
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
        return pizzaConfig.getPriceMutator().apply(pizzaVariant.getBasePrice());
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
        return pizzaConfig.getWeightMutator().applyAsDouble(pizzaVariant.getBaseWeight());
    }

    /**
     * The food variant.
     *
     * @return The food variant
     */
    @Override
    public Food.Variant<Pizza, Pizza.Config> getFoodVariant() {
        return pizzaVariant;
    }

    /**
     * The extras that this food was configured with.
     *
     * @return The extras that this food was configured with
     */
    @Override
    public List<? extends Extra<? super Pizza.Config>> getExtras() {
        return extras;
    }

    /**
     * @return the diameter of a pizza
     */
    @Override
    public double getDiameter() {
        return pizzaConfig.getDiameterMutator().applyAsDouble(pizzaVariant.getBaseDiameter());
    }

    /**
     * description of sauce.
     *
     * @return a string identifying the sauce
     */
    @Override
    public String getSauce() {
        return pizzaConfig.getSauceMutator().apply(pizzaVariant.getBaseSauce());
    }

    /**
     *
     */
    private static class Config implements Pizza.Config {
        /**
         * the extra price of config.
         */
        private List<UnaryOperator<BigDecimal>> priceMutators = new ArrayList<>();
        /**
         * the extra weight of config.
         */
        private List<DoubleUnaryOperator> weightMutators = new ArrayList<>();
        /**
         * the extra sauce of config.
         */
        private List<UnaryOperator<String>> sauceMutators =  new ArrayList<>();

        /**
         * the extra diameter of config
         */
        private List<DoubleUnaryOperator> diameterMutators = new ArrayList<>();

        /**
         * config a pizza.
         * @param priceMutator the extra price of config
         * @param weightMutator the extra weight of config
         * @param sauceMutator the extra sauce of config
         */
        public Config(UnaryOperator<BigDecimal> priceMutator,
                      DoubleUnaryOperator weightMutator,
                      UnaryOperator<String> sauceMutator,
                      DoubleUnaryOperator diameterMutator) {
            this.priceMutators.add(priceMutator);
            this.weightMutators.add(weightMutator);
            this.sauceMutators.add(sauceMutator);
            this.diameterMutators.add(diameterMutator);
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
         * chain the current sauce with given sauceMutator.
         * and save it internally
         *
         * @param sauceMutator the given sauceMutator
         */
        @Override
        public void sauce(UnaryOperator<String> sauceMutator) {
            sauceMutators.add(sauceMutator);
        }

        /**
         * getter method returns the internally saved.
         * sauceMutator
         *
         * @return the internally saved sauceMutator
         */
        @Override
        public UnaryOperator<String> getSauceMutator() {
            return sauce -> {
                String s = new String(sauce);
                for (UnaryOperator<String> sm: this.sauceMutators){
                    s = sm.apply(s);
                }
                return s;
            };
        }

        /**
         * chain the current diameter with given diameterMutator.
         * and save it internally
         *
         * @param diameterMutator the given diameterMutator
         */
        @Override
        public void diameter(DoubleUnaryOperator diameterMutator) {
            diameterMutators.add(diameterMutator);
        }

        /**
         * getter method returns the internally saved.
         * diameterMutator
         *
         * @return internally saved diameterMutator
         */
        @Override
        public DoubleUnaryOperator getDiameterMutator() {
            return diameter ->{
                double d = diameter;
                for (DoubleUnaryOperator dm: this.diameterMutators){
                    d = dm.applyAsDouble(d);
                }
                return d;
            };
        }
    }

    private static class Variant implements Pizza.Variant{
        public Variant(String name, BigDecimal basePrice, double baseWeight, String baseSauce, double baseDiameter) {
            this.name = name;
            this.basePrice = basePrice;
            this.baseWeight = baseWeight;
            this.baseSauce = baseSauce;
            this.baseDiameter = baseDiameter;
            this.foodType = FoodTypes.PIZZA;
        }

        private String name;
        private BigDecimal basePrice;
        private double baseWeight;
        private String baseSauce;
        private double baseDiameter;
        private FoodType<Pizza, Pizza.Config> foodType;

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
        public FoodType<Pizza, Pizza.Config> getFoodType() {
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
        public Pizza.Config createEmptyConfig() {
            return new PizzaImpl.Config(p -> p, w -> w, s -> s, d -> d);
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
        public Pizza create(List<? extends Extra<? super Pizza.Config>> extras) {
            Pizza.Config config = createEmptyConfig();
            Extra.writeToConfig(config, extras);
            return BUILDER.build(config, this, extras);
        }

        /**
         * Getter method returns the base diameter.
         *
         * @return the base diameter.
         */
        @Override
        public double getBaseDiameter() {
            return this.baseDiameter;
        }

        /**
         * Getter method returns the base sauce.
         *
         * @return the base sauce
         */
        @Override
        public String getBaseSauce() {
            return this.baseSauce;
        }
    }
}
