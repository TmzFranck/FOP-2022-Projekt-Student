package projekt.base;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * the determination of the permissible delivery times.
 */
public class TimeInterval {
    /**
     * local date time start.
     */
    private final LocalDateTime start;
    /**
     * local date time end.
     */
    private final LocalDateTime end;

    /**
     * a constructor.
     * @param start the start time
     * @param end the end time
     * @throws IllegalAccessException when start after end is
     *
     */
    public TimeInterval(final LocalDateTime start, final LocalDateTime end)
        throws IllegalAccessException {
        if (start == null) {
            throw new NullPointerException("start");
        } else if (end == null) {
            throw new NullPointerException("end");
        }
        if (start.isAfter(end)) {
            throw new IllegalAccessException(String.format(
                "Start %s is after end %s", start, end));
        }
        this.start = start;
        this.end = end;
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
     * @return a duration time between start and end
     */
    public Duration getDuration() {
        return Duration.between(getStart(), getEnd());
    }
}
