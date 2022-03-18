package projekt.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class PastaImpl implements Pasta {

    public final static Pasta.Variant SPAGHETTI= new PastaImpl.Variant(
        "Spaghetti", BigDecimal.valueOf(12.5), 0.2, null, 2
    );
    public final static Pasta.Variant RIGATONI= new PastaImpl.Variant(
        "Rigatoni", BigDecimal.valueOf(11.5), 0.2, null, 10
    );
    public final static Pasta.Variant RAVIOLI= new PastaImpl.Variant(
        "Ravioli", BigDecimal.valueOf(11.5), 0.2, null, 40
    );
    public final static Pasta.Variant FUSILLI= new PastaImpl.Variant(
        "Fusilli", BigDecimal.valueOf(11.5), 0.2, null, 15
    );

    static FoodBuilder<Pasta, Pasta.Config, Pasta.Variant> BUILDER =
        (c, v, e) -> new PastaImpl(c, v, e);
    /**
     * food config of pasta.
     */
    private final Pasta.Config pastaConfig;
    /**
     * the pizza variant.
     */
    private final Pasta.Variant pastaVariant;
    /**
     * extra of pasta.
     */
    private final List<? extends Extra<? super Pasta.Config>> extras;

    /**
     * config the pasta.
     *
     * @param pastaConfig  the weight of pasta
     * @param pastaVariant the food variant of pasta
     * @param extras       tha extra of pasta
     */
    public PastaImpl(Pasta.Config pastaConfig,
                     Pasta.Variant pastaVariant,
                     List<? extends Extra<? super Pasta.Config>> extras) {

        this.pastaConfig = pastaConfig;
        this.pastaVariant = pastaVariant;
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
        return pastaConfig.getPriceMutator().apply(pastaVariant.getBasePrice());
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
        return pastaConfig.getWeightMutator().applyAsDouble(pastaVariant.getBaseWeight());
    }

    /**
     * The food variant.
     *
     * @return The food variant
     */
    @Override
    public Food.Variant<Pasta, Pasta.Config> getFoodVariant() {
        return pastaVariant;
    }

    /**
     * The extras that this food was configured with.
     *
     * @return The extras that this food was configured with
     */
    @Override
    public List<? extends Extra<? super Pasta.Config>> getExtras() {
        return extras;
    }

    /**
     * @return the thickness of the pasta
     */
    @Override
    public double getThickness() {
        return pastaConfig.getThicknessMutator().applyAsDouble(pastaVariant.getBaseThickness());
    }

    /**
     * description of sauce
     *
     * @return a string identifying the sauce
     */
    @Override
    public String getSauce() {
        return pastaConfig.getSauceMutator().apply(pastaVariant.getBaseSauce());
    }

    private static class Config implements Pasta.Config {
        /**
         * the extra price of config.
         */
        private List<UnaryOperator<BigDecimal>> priceMutators = new ArrayList<>();
        /**
         * the extra weight of config.
         */
        private List<DoubleUnaryOperator> weightMutators = new ArrayList<>();
        /**
         * the extra sauce of config
         */
        private List<UnaryOperator<String>> sauceMutators = new ArrayList<>();
        /**
         * the extra thickness of config
         */
        private List<DoubleUnaryOperator> thicknessMutators = new ArrayList<>();

        private Config(UnaryOperator<BigDecimal> priceMutator,
                       DoubleUnaryOperator weightMutator,
                       UnaryOperator<String> sauceMutator,
                       DoubleUnaryOperator thicknessMutator) {
            this.priceMutators.add(priceMutator);
            this.weightMutators.add(weightMutator);
            this.sauceMutators.add(sauceMutator);
            this.thicknessMutators.add(thicknessMutator);
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
            return preis -> {
                BigDecimal p = preis;
                for (UnaryOperator<BigDecimal> pm : this.priceMutators) {
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
            return weight -> {
                double w = weight;
                for (DoubleUnaryOperator wm : this.weightMutators) {
                    w = wm.applyAsDouble(w);
                }
                return w;
            };
        }

        /**
         * chain the current sauce with given sauceMutator
         * and save it internally
         *
         * @param sauceMutator the given sauceMutator
         */
        @Override
        public void sauce(UnaryOperator<String> sauceMutator) {
            sauceMutators.add(sauceMutator);
        }

        /**
         * getter method returns the internally saved
         * sauceMutator
         *
         * @return the internally saved sauceMutator
         */
        @Override
        public UnaryOperator<String> getSauceMutator() {
            return sauce -> {
                if (sauce == null){
                    return null;
                }
                String s = new String(sauce);
                for (UnaryOperator<String> sm : this.sauceMutators) {
                    s = sm.apply(s);
                }
                return s;
            };
        }

        /**
         * chain the current thickness with given thicknessMutator.
         * and save it internally
         *
         * @param thicknessMutator the given thicknessMutator
         */
        @Override
        public void thickness(DoubleUnaryOperator thicknessMutator) {
            thicknessMutators.add(thicknessMutator);
        }

        /**
         * getter method returns the internally saved.
         * thicknessMutator
         *
         * @return internally saved thicknessMutator
         */
        @Override
        public DoubleUnaryOperator getThicknessMutator() {
            return thickness -> {
                double t = thickness;
                for (DoubleUnaryOperator tm : this.thicknessMutators) {
                    t = tm.applyAsDouble(t);
                }
                return t;
            };
        }
    }

    private static class Variant implements Pasta.Variant {

        private String name;
        private BigDecimal basePrice;
        private double baseWeight;
        private String baseSauce;
        private double baseThickness;
        private FoodType<Pasta, Pasta.Config> foodType;

        public Variant(String name, BigDecimal basePrice, double baseWeight, String baseSauce, double baseThickness) {
            this.name = name;
            this.basePrice = basePrice;
            this.baseWeight = baseWeight;
            this.baseSauce = baseSauce;
            this.baseThickness = baseThickness;
            this.foodType = FoodTypes.PASTA;
        }

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
        public FoodType getFoodType() {
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
        public Pasta.Config createEmptyConfig() {
            return new PastaImpl.Config(p -> p, w -> w, s -> s, t -> t);
        }

        public Pasta create(List<? extends Extra<? super Pasta.Config>> extras) {
            Pasta.Config config = createEmptyConfig();
            Extra.writeToConfig(config, extras);
            return BUILDER.build(config, this, extras);
        }


        /**
         * Getter method returns teh base Thickness of the pasta.
         *
         * @return the base thickness.
         */
        @Override
        public double getBaseThickness() {
            return this.baseThickness;
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
