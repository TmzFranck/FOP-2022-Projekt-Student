package projekt.food;

import java.util.function.DoubleUnaryOperator;

/**
 *
 */
public interface Pasta extends Saucable {
    /**
     *
     * @return the thickness of the pasta
     */
    double getThickness();

    /**
     * TODO: add description
     */
    interface Config extends Saucable.Config {
        /**
         * chain the current thickness with given thicknessMutator
         * and save it internally
         * @param thicknessMutator the given thicknessMutator
         */
        void thickness (DoubleUnaryOperator thicknessMutator);

        /**
         * getter method returns the internally saved
         * thicknessMutator
         * @return internally saved thicknessMutator
         */
        DoubleUnaryOperator getThicknessMutator();
    }
}
