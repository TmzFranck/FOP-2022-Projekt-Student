package projekt.food;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * pizza.
 */
public interface Pizza extends Saucable {
    /**
     *
     * @return the diameter of a pizza
     */
    double getDiameter();

    /**
     * config of pizza.
     */
    interface Config extends Saucable.Config {
        /**
         * chain the current diameter with given diameterMutator.
         * and save it internally
         * @param diameterMutator the given diameterMutator
         */
        void diameter(DoubleUnaryOperator diameterMutator);

        /**
         * getter method returns the internally saved.
         * diameterMutator
         * @return internally saved diameterMutator
         */
        DoubleUnaryOperator getDiameterMutator();
    }

    /**
     * A specific kind of pizza depending on the diameter.
     */
    interface Variant extends Saucable.Variant<Pizza, Pizza.Config> {
        /**
         * Getter method returns the base diameter.
         * @return the base diameter.
         */
        double getBaseDiameter();
    }
}
