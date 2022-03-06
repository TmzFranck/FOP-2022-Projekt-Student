package projekt.food;

import java.util.function.UnaryOperator;

/**
 *
 */
public interface Saucable extends Food {
    /**
     * description of sauce
     * @return  a string identifying the sauce
     */
     String getSauce();

    /**
     * TODO: add description
     */
     interface Config extends Food.Config {
        /**
         * chain the current sauce with given sauceMutator
         * and save it internally
         * @param sauceMutator the given sauceMutator
         */
         void sauce(UnaryOperator<String> sauceMutator);

        /**
         * getter method returns the internally saved
         * sauceMutator
         * @return the internally saved sauceMutator
         */
         UnaryOperator<String> getSauceMutator();
     }
}
