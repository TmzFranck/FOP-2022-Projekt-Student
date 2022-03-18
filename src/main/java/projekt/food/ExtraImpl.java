package projekt.food;

import java.util.function.Consumer;

public class ExtraImpl<C extends Food.Config> implements Extra<C> {
    /**
     * the name of extra.
     */
    private final String name;
    /**
     * the priority of extra.
     */
    private final int priority;
    /**
     * the config of extra.
     */
    private final Consumer<C> configMutator;

    /**
     * configure the extra portion.
     * @param name name of extra
     * @param priority priority  of extra
     * @param configMutator config of extra
     */
    public ExtraImpl(String name,
                     int priority,
                     Consumer<C> configMutator) {
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
    public void apply(C config) {
        configMutator.accept(config);
    }
}
