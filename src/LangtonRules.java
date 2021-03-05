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
        Heading newHeading;
        if (cellState == CellState.ALIVE) {
            // turn 90 degrees counter-clockwise relative to current heading
            if (heading == Heading.NORTH) {
                newHeading = Heading.WEST;
            } else if (heading == Heading.WEST) {
                newHeading = Heading.SOUTH;
            } else if (heading == Heading.SOUTH) {
                newHeading = Heading.EAST;
            } else {
                newHeading = Heading.NORTH;
            }
        } else {
            // turn 90 degrees clockwise relative to current heading
            if (heading == Heading.NORTH) {
                newHeading = Heading.EAST;
            } else if (heading == Heading.EAST) {
                newHeading = Heading.SOUTH;
            } else if (heading == Heading.SOUTH) {
                newHeading = Heading.WEST;
            } else {
                newHeading = Heading.NORTH;
            }
        }
        return newHeading;
    }
}
