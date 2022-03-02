package projekt.base;

/**
 * the chessboard  distance from the pizzeria to the customer.
 */
public class ChessboardDistanceCalculator implements DistanceCalculator {
    /**
     * calculate the chessboard distance between two locations.
     * @param location1 a location gives
     * @param location2 a location gives
     * @return the chessboard distance between two locations
     */
    @Override
    public double calculateDistance(final Location location1,
                                    final Location location2) {
        return Math.max(Math.abs(location1.getX()
            - location2.getX()), Math.abs(location1.getY() - location2.getY()));
    }
}
