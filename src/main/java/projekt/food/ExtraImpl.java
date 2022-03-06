package projekt.food;

import java.util.function.Consumer;

public class ExtraImpl<C extends Food.Config> implements Extra{

    private final String name;
    private final int priority;
    private final Consumer<? extends Food.Config> configMutator;

    public ExtraImpl(String name, int priority, Consumer<? extends Food.Config> configMutator) {
        this.name = name;
        this.priority = priority;
        this.configMutator = configMutator;
    }

    /**
     * The name of this extra.
     *
     * @return The name of this extra
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * The priority of the extra, lower is calculated first.
     */
    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * Applies the modifications of this extra to the provided {@link C config}.
     *
     * @param config
     */
    @Override
    public void apply(Food.Config config) {
    }
}
