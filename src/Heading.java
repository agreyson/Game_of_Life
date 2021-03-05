import java.util.Random;

public enum Heading {
    NONE, // NONE indicates that the ant is not present
    NORTH,
    SOUTH,
    EAST,
    WEST;

    private static final Heading[] HEADINGS = {NORTH, EAST, SOUTH, WEST};
    private static final Random RANDOM = new Random();

    public static Heading randomHeading() {
        return HEADINGS[RANDOM.nextInt(4)];
    }
}