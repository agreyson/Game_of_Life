public class LangtonRules extends Rules {
    public LangtonRules() {
    }

    public boolean shouldBeBorn(int liveNeighbors) {
        return true; // a dead cell under an ant should always be born
    }

    public boolean shouldSurvive(int liveNeighbors) {
        return false; // a live cell under an ant should always die
    }

    public Heading nextHeading(Heading heading, CellState cellState) {
        if (cellState == CellState.ALIVE) {
            // turn 90 degrees counter-clockwise relative to current heading
            return heading.getCounterclockwise();
        } else {
            // turn 90 degrees clockwise relative to current heading
            return heading.getClockwise();
        }
    }
}
