package projekt.food;

import java.util.function.UnaryOperator;

/**
 * saucable.
 */
public interface Saucable extends Food {
    /**
     * description of sauce.
     * @return  a string identifying the sauce
     */
     String getSauce();

    /**
     * config the saucable.
     */
     interface Config extends Food.Config {
        /**
         * chain the current sauce with given sauceMutator.
         * and save it internally
         * @param sauceMutator the given sauceMutator
         */
         void sauce(UnaryOperator<String> sauceMutator);

        /**
         * getter method returns the internally saved.
         * sauceMutator
         * @return the internally saved sauceMutator
         */
         UnaryOperator<String> getSauceMutator();
     }

    /**
     * A specific kind of base Sauce.
     */
    interface Variant<F extends Saucable, C extends Saucable.Config> extends Food.Variant<F,C> {
        /**
         * Getter method returns the base sauce.
         * @return the base sauce
         */

         String getBaseSauce();
     }
}
