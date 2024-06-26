package projekt.food;

import java.util.function.DoubleUnaryOperator;

/**
 * pasta.
 */
public interface Pasta extends Saucable {
    /**
     *
     * @return the thickness of the pasta.
     */
    double getThickness();

    /**
     * config of pasta.
     */
    interface Config extends Saucable.Config {
        /**
         * chain the current thickness with given thicknessMutator.
         * and save it internally
         * @param thicknessMutator the given thicknessMutator
         */
        void thickness(DoubleUnaryOperator thicknessMutator);

        /**
         * getter method returns the internally saved.
         * thicknessMutator
         * @return internally saved thicknessMutator
         */
        DoubleUnaryOperator getThicknessMutator();
    }

    /**
     * A specific kind of Pasta depending on the thickness.
     */
    interface Variant extends Saucable.Variant<Pasta, Pasta.Config> {
        /**
         * Getter method returns teh base Thickness of the pasta.
         * @return the base thickness.
         */
        double getBaseThickness();
    }
}
