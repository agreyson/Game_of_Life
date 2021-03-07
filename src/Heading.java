import java.util.Random;

public enum Heading {
    NONE(0, 0), // NONE indicates that the ant is not present
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    public final int ROW_CHANGE;
    public final int COLUMN_CHANGE;

    private static final Random RANDOM = new Random();
    private static final Heading[] HEADINGS = {NORTH, EAST, SOUTH, WEST};

    Heading(int rowChange, int columnChange) {
        this.ROW_CHANGE = rowChange;
        this.COLUMN_CHANGE = columnChange;

    }

    public static Heading randomHeading() {
        return HEADINGS[RANDOM.nextInt(4)];
    }

    public Heading getCounterclockwise() {
        // turn 90 degrees counter-clockwise relative to current heading
        switch (this) {
            case NORTH:
                return Heading.WEST;
            case WEST:
                return Heading.SOUTH;
            case SOUTH:
                return Heading.EAST;
            case EAST:
                return Heading.NORTH;
            default:
                return Heading.NONE;
        }
    }

    public Heading getClockwise(){
        // turn 90 degrees clockwise relative to current heading
        switch (this) {
            case NORTH:
                return Heading.EAST;
            case EAST:
                return Heading.SOUTH;
            case SOUTH:
                return Heading.WEST;
            case WEST:
                return Heading.NORTH;
            default:
                return Heading.NONE;
        }
    }
}