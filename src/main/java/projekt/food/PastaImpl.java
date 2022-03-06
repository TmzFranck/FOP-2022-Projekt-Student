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

    public PastaImpl(BigDecimal price, double weight, Food.Variant<Pasta, Config> foodVariant,
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
}
