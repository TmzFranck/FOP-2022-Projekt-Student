package projekt.base;

/**
 * the coordinates of the destinations.
 */
public class Location {
    private final int x;
    private final int y;

    /**
     * create location with coordinate.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Location(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    /**
     *
     * @return the value of x
     */
    public int getX() {
        return x;
    }
    /**
     *
     * @return the value of y
     */
    public int getY() {
        return y;
    }
    /**
     * They sum their own coordinates and those of
     * the passed Location object and return a new
     * Location object with the respective results.
     * @param location a location gives
     * @return a new location
     */
    public Location add(final Location location) {
        return new Location(location.getX() + this.getX(),
            location.getY() + this.getY());
    }
    /**
     * They form the difference between their own
     * coordinates and those of the supplied Location
     * object and return a new Location object with the
     * respective results.
     * @param location a location gives
     * @return a new location
     */
    public Location subtract(final Location location) {
        return new Location(this.getX() - location.getX(),
            this.getY() - location.getY());
    }
}
