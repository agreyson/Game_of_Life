public class LangtonCell extends Cell {
    private final static LangtonRules rules = new LangtonRules();

    private Heading heading;
    private AntState antState;

    public LangtonCell(int x, int y, int size, int row, int column, CellState cellState, boolean isAnt) {
        super(x, y, size, row, column, cellState, rules);
        if (isAnt) {
            heading = Heading.randomHeading();
            antState = AntState.IS_ANT;
        } else {
            heading = Heading.NONE;
            antState = AntState.IS_NOT_ANT;
        }
    }

    public void applyRules(Cell[][] cells) {
        // Only the ant obeys the rules; non-ants are inactive
        if (antState == AntState.IS_ANT) {
            LangtonRules rules = (LangtonRules) getRules();
            Heading nextHeading = rules.nextHeading(heading, getCellState());

            // calculate the row and column of the next ant
//            if (nextHeading == Heading.WEST) {
//                column = column - 1;
//            } else if (nextHeading == Heading.SOUTH) {
//                row = row + 1;
//            } else if (nextHeading == Heading.EAST) {
//                column = column + 1;
//            } else { // Heading.NORTH
//                row = row - 1;
//            }
            int nextRow = getRow() + nextHeading.ROW_CHANGE;
            int nextColumn = getColumn() + nextHeading.COLUMN_CHANGE;
            LangtonCell nextCell = (LangtonCell) cells[nextRow][nextColumn];

            // Set its heading now to prepare for being the ant upon evolution
            nextCell.willBeAnt(nextHeading);

            // Prepare to give up being the ant and flip state upon evolution
            heading = Heading.NONE;
            antState = AntState.IS_NOT_ANT;
            setCellState(rules.applyRules(getCellState(), 0)); // neighbors are irrelevant
        }
    }

    public void evolve() {
        // If it will be the ant, it is not the new ant
        if (antState == AntState.WILL_BE_ANT) {
            antState = AntState.IS_ANT;
        } else { // otherwise, evolve as indicated by CellState
            super.evolve();
        }
    }

    public void display() {
        if (antState == AntState.IS_ANT) {
            GameOfLifeApp app = GameOfLifeApp.getApp();
            app.fill(255, 0, 0);
            app.rect(getX(), getY(), getSize(), getSize());
        } else {
            super.display();
        }
    }

    private void willBeAnt(Heading nextHeading) {
        heading = nextHeading;
        antState = AntState.WILL_BE_ANT;
    }
}
