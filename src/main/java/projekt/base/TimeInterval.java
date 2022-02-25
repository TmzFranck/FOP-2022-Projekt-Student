package projekt.base;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * the determination of the permissible delivery times.
 */
public class TimeInterval {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * a constructor.
     * @param starts the start time
     * @param ends the end time
     * @throws IllegalAccessException when start after end is
     */
    public TimeInterval(final LocalDateTime starts, final LocalDateTime ends)
        throws IllegalAccessException {
        if (starts == null) {
            throw new NullPointerException("start");
        } else if (ends == null) {
            throw new NullPointerException("end");
        }
        if (starts.isAfter(ends)) {
            throw new IllegalAccessException(String.format(
                "Start %s is after end %s", starts, ends));
        }
        this.start = starts;
        this.end = ends;
    }

    /**
     *
     * @return start time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     *
     * @return end time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     *
     * @return a duration time between two start and end
     */
    public Duration getDuration() {
        return Duration.between(getStart(), getEnd());
    }
}
