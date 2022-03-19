package projekt.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * food types.
 */
public final class FoodTypes {
    final static FoodType PIZZA = new FoodTypeImpl<Pizza, Pizza.Config>(
        "Pizza",
        new ArrayList<>(){{
            add(Extras.EXTRA_HAM);
            add(Extras.EXTRA_OLIVES);
            add(Extras.SPICY_SAUCE);
            add(Extras.EXTRA_SAUCE);
            add(Extras.NO_SAUCE);
        }});
    final static FoodType PASTA = new FoodTypeImpl<Pasta, Pasta.Config>(
        "Pasta",
        new ArrayList<>(){{
            add(Extras.EXTRA_THICK);
            add(Extras.SPICY_SAUCE);
            add(Extras.EXTRA_SAUCE);
            add(Extras.NO_SAUCE);
        }});
    final static FoodType ICE_CREAM = new FoodTypeImpl<IceCream, IceCream.Config>(
        "Ice Cream",
        new ArrayList<>(){{
            add(Extras.RAINBOW_SPRINKLES);
            add(Extras.EXTRA_SCOOP);
        }});

    public static Map<String, FoodType<?,?>> ALL = new HashMap<>() {{
        put("Pizza", PIZZA);
        put("Pasta", PASTA);
        put("Ice Cream", ICE_CREAM);
    }};


    /**
     *     implement static initializer for the menu in the H2.13
     */
    static {
        ICE_CREAM.addFoodVariant(IceCreamImpl.VANILLA);
        ICE_CREAM.addFoodVariant(IceCreamImpl.STRAWBERRY);
        ICE_CREAM.addFoodVariant(IceCreamImpl.CHOCOLATE);
        ICE_CREAM.addFoodVariant(IceCreamImpl.STRACCIATELLA);

        PASTA.addFoodVariant(PastaImpl.SPAGHETTI);
        PASTA.addFoodVariant(PastaImpl.RIGATONI);
        PASTA.addFoodVariant(PastaImpl.RAVIOLI);
        PASTA.addFoodVariant(PastaImpl.FUSILLI);



    }
}
