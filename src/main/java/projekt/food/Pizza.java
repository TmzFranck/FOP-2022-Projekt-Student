package projekt.food;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

/**
 *
 */
public interface Pizza extends Saucable {
    /**
     *
     * @return the diameter of a pizza
     */
    double getDiameter();

    /**
     * TODO: add description
     */
    interface Config extends Saucable.Config {
        /**
         * chain the current diameter with given diameterMutator
         * and save it internally
         * @param diameterMutator the given diameterMutator
         */
        void diameter(DoubleUnaryOperator diameterMutator);

        /**
         * getter method returns the internally saved
         * diameterMutator
         * @return internally saved diameterMutator
         */
        DoubleUnaryOperator getDiameterMutator();
    }
}
