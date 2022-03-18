package projekt.base;

/**
 * the euclidean distance from the pizzeria to the customer.
 */
public class EuclideanDistanceCalculator implements DistanceCalculator {
    /**
     * calculate the euclidean distance between two locations.
     * @param location1 a location gives
     * @param location2 a location gives
     * @return the euclidean distance between two locations
     */
    @Override
    public double calculateDistance(final Location location1,
                                    final Location location2) {
        return Math.sqrt((Math.pow(location1.getX()
            - location2.getX(), 2) + Math.pow(location1.getY()
            - location2.getY(), 2)));
    }
}
