package projekt.food;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A modification that targets configurable values in a {@link Food.Config}.
 *
 * @param <C> The target {@link Food.Config} type
 */
public interface Extra<C extends Food.Config> {

    /**
     * The name of this extra.
     *
     * @return The name of this extra
     */
    String getName();

    /**
     * The priority of the extra, lower is calculated first.
     */
    int getPriority();

    /**
     * Applies the modifications of this extra to the provided {@link C config}.
     */
    void apply(C config);

    /**
     *
     * @param config
     * @param extras
     * @param <C>
     */
    static <C> void writeToConfig(C config, List<? extends Extra<? super C>> extras) {
        List<? extends Extra<? super C>> extrasCopy = new ArrayList<>(extras);
        extrasCopy.sort((Comparator<Extra<? super C>>) (o1, o2) -> {
            int diff = o1.getPriority() - o2.getPriority();
            if (diff != 0) {
                return diff;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Extra<? super C> e : extrasCopy) {
            e.apply(config);
        }
    }
}
