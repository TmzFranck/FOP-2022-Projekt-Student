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
     * TODO: add description
     */
    interface Config extends Food.Config {
        /**
         * chain the current flavor with given flavorMutator
         * and save it internally
         * @param flavorMutator the given flavorMutator
         */
        void flavor(UnaryOperator<String> flavorMutator);

        /**
         * getter method returns the internally saved
         * flavorMutator
         * @return internally saved flavorMutator
         */
        UnaryOperator<String> getFlavorMutator();
    }
}
