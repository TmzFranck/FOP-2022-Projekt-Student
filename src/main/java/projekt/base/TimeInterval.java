package projekt.base;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeInterval {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * a constructor
     * @param start the start time
     * @param end the end time
     * @throws IllegalAccessException when start after end is
     */
    public TimeInterval(LocalDateTime start, LocalDateTime end) throws IllegalAccessException {
        if (start == null) throw new NullPointerException("start");
        else if(end == null) throw new NullPointerException("end");
        if (start.isAfter(end)) throw new IllegalAccessException(String.format("Start %s is after end %s", start, end));
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
     * duration
     * @return a duration time between two start and end
     */
    public Duration getDuration(){
        return Duration.between(getStart(), getEnd());
    }
}
