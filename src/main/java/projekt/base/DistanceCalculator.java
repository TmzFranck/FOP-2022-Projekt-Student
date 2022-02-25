package projekt.base;

public interface DistanceCalculator {
    /**
     * calculate the distance between two locations
     * @param location1 a location gives
     * @param location2 a location gives
     * @return the distance between two locations
     */
    public double calculateDistance(Location location1, Location location2);
}
