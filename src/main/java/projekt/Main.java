package projekt;

import projekt.food.Extra;
import projekt.food.Extras;
import projekt.food.IceCream;
import projekt.food.IceCreamImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<Extra<IceCream.Config>> extras = new ArrayList<>();
        // EXTRA_CHOCOLATE
        // add chocolate to flavor
        // add 0.1 to weight
        extras.add(Extras.EXTRA_CHOCOLATE);
        // RAINBOW_SPRINKLES
        // add price 0.5
        // add weight 0.03
        extras.add(Extras.RAINBOW_SPRINKLES);
        // Vanilla base variant
        // base price 1.5, base weight 0.2, base flavor Vanilla
        IceCream iceCream = IceCreamImpl.VANILLA.create(extras);
        // price = 1.5 + 0.5
        System.out.println(iceCream.getPrice());
        // weight = 0.2 + 0.1 + 0.03
        System.out.println(iceCream.getWeight());
        // flavor = Chocolate Vanilla
        System.out.println(iceCream.getFlavor());

    }
}
