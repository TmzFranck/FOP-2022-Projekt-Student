package projekt;

import projekt.food.*;

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
        System.out.println("Icecream test");
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
        System.out.println("Pasta Test");
        List<Extra<Saucable.Config>> PastaExtras = new ArrayList<>();
        // EXTRA_THICK
        // priority  4 -> THIRD APPLY (ALPHABETIC)
        // price + 4.0, weight *2, thickness *2
        PastaExtras.add(Extras.EXTRA_THICK);
        // SPICY_SAUCE
        // proiority 3 -> FIRST APPLY
        // price + 0.5 append Spicy to sauce
        PastaExtras.add(Extras.SPICY_SAUCE);
        // EXTRA_SAUCE
        // priority 4 -> SECOND APPLY (ALPHABETIC)
        // price * 1.25, weight + 0.12 append Extra to sauce
        PastaExtras.add(Extras.EXTRA_SAUCE);
        // SPAGHETTI
        // base price  12.5
        // base weight 0.2
        // base sauce null -> no sauce can be added on top
        // base thickness 2
        Pasta pasta = PastaImpl.SPAGHETTI.create(PastaExtras);
        // (12.5 + 0.5)*1.25 + 4 = 20.25
        System.out.println(pasta.getPrice());
        // (0.2 + 0.12)*2 = 0.64
        System.out.println(pasta.getWeight());
        // null since no base sauce
        System.out.println(pasta.getSauce());
        // 2 * 2 = 4
        System.out.println(pasta.getThickness());
        System.out.println("Pizza Test");
        List<Extra<Saucable.Config>> PizzaExtras = new ArrayList<>();
        PizzaExtras.add(Extras.SPICY_SAUCE);
        PizzaExtras.add(Extras.EXTRA_SAUCE);
        Pizza pizza = PizzaImpl.HAWAII.create(PizzaExtras);
        System.out.println(pizza.getPrice());
        System.out.println(pizza.getWeight());
        // Extra Spicy Tomato
        System.out.println(pizza.getSauce());
        System.out.println(pizza.getDiameter());

    }
}
