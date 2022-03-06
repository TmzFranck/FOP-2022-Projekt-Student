package projekt.food;


import java.util.function.UnaryOperator;

/**
 *
 */
public interface IceCream extends Food {
    /**
     *
     * @return the flavor of the ice cream
     */
    String getFlavor();

    /**
     * config the ice cream.
     */
    interface Config extends Food.Config {
        /**
         * chain the current flavor with given flavorMutator.
         * and save it internally
         * @param flavorMutator the given flavorMutator
         */
        void flavor(UnaryOperator<String> flavorMutator);

        /**
         * getter method returns the internally saved.
         * flavorMutator
         * @return internally saved flavorMutator
         */
        UnaryOperator<String> getFlavorMutator();
    }

    /**
     * A specific kind of ice cream.
     */
    interface Variant extends Food.Variant {
        /**
         * Getter method returns the base flavor.
         * @return the base flavor
         */
        String getBaseFlavor();
    }
}
