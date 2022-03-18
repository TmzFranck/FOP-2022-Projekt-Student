package projekt.food;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class Extras {
    public static Extra EXTRA_HAM = new ExtraImpl<Pizza.Config>(
        "Extra ham",
        5,
        c -> {
            c.price(p -> p.add(new BigDecimal(1.0)));
            c.weight(w -> w + 0.1);
        });

    public static Extra EXTRA_OLIVES = new ExtraImpl<Pizza.Config>(
        "Extra Olives",
        5,
        c -> {
            c.price(p -> p.add(new BigDecimal(0.8)));
            c.weight(w -> w + 0.05);
        });

    public static Extra EXTRA_THICK = new ExtraImpl<Pasta.Config>(
        "Extra Thick",
        4,
        c -> {
            c.price(p -> p.add(new BigDecimal(4.0)));
            c.weight(w -> w * 2);
            c.thickness(t -> t * 2);
        });

    public static Extra SPICY_SAUCE = new ExtraImpl<Saucable.Config>(
        "Spicy Sauce",
        3,
        c -> {
            c.price(p -> p.add(new BigDecimal(0.5)));
            c.sauce(s -> "Spicy " + s);
        });

    public static Extra EXTRA_SAUCE = new ExtraImpl<Saucable.Config>(
        "Extra Sauce",
        4,
        c -> {
            c.price(p -> p.multiply(new BigDecimal(1.25)));
            c.weight(w -> w + 0.12);
            c.sauce(s -> "Extra " + s);
        });

    public static Extra NO_SAUCE = new ExtraImpl<Saucable.Config>(
        "No Sauce",
        5,
        c -> {
            c.price(p -> p.subtract(new BigDecimal(1)).max( new BigDecimal(0)));
            c.weight(w -> Math.max(w - 0.1, 0.0));
            c.sauce(s -> null);
        });

    public static Extra RAINBOW_SPRINKLES = new ExtraImpl<IceCream.Config>(
        "Rainbow Sprinkles",
        5,
        c -> {
            c.price(p -> p.add(new BigDecimal(0.5)));
            c.weight(w -> w + 0.03);
        });

    public static Extra EXTRA_SCOOP = new ExtraImpl<IceCream.Config>(
        "Extra Scoop",
        2,
        c -> {
            c.price(p -> p.add(new BigDecimal(3.0)));
            c.weight(w -> w + 0.1);
        });

    public static Extra EXTRA_CHOCOLATE = new ExtraImpl<IceCream.Config>(
        "Extra Chocolate",
        3,
        c -> {
            c.flavor(s ->  "Chocolate " + s);
            c.weight(w -> w + 0.1);
        });

    public static Map<String, Extra<?>> ALL = new HashMap<>() {{
        put("Extra ham", EXTRA_HAM);
        put("Extra Olives", EXTRA_OLIVES);
        put("Extra Thick", EXTRA_THICK);
        put("Spicy Sauce", SPICY_SAUCE);
        put("Extra Sauce", EXTRA_SAUCE);
        put("No Sauce", NO_SAUCE);
        put("Rainbow Sprinkles", RAINBOW_SPRINKLES);
        put("Extra Scoop", EXTRA_SCOOP);
        put("Extra Chocolate", EXTRA_CHOCOLATE);
    }};
}
