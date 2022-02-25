package projekt.base;

/**
 * the distance from the pizzeria to the customer.
 */
public interface DistanceCalculator {
    /**
     * calculate the distance between two locations.
     * @param location1 a location gives
     * @param location2 a location gives
     * @return the distance between two locations
     */
    double calculateDistance(Location location1, Location location2);
}
