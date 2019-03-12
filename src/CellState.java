import java.util.Random;

public enum CellState {
    ALIVE,
    DEAD,
    WILL_DIE,
    WILL_REVIVE;

    private static final CellState[] STATES = {ALIVE, DEAD};
    private static final Random RANDOM = new Random();

    public static CellState randomState(){
        return STATES[RANDOM.nextInt(2)];
    }
}
