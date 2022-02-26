package projekt.base;

/**
 * the manhattan distance from the pizzeria to the customer.
 */

public class ManhattanDistanceCalculator implements DistanceCalculator {
    /**
     * calculate the manhattan distance between two locations.
     * @param location1 a location gives
     * @param location2 a location gives
     * @return the manhattan distance between two locations
     */
    @Override
    public double calculateDistance(final Location location1, final Location location2) {
        return Math.abs(location1.getX() - location2.getX()) + Math.abs(location1.getY() - location2.getY());
    }
}
