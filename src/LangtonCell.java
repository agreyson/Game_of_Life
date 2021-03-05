public class LangtonCell extends Cell {
    private Heading heading;
    private boolean isAnt;

    public LangtonCell(int x, int y, int size, int row, int column, CellState cellState, boolean isAnt) {
        super(x, y, size, row, column, cellState, new LangtonRules());
        this.isAnt = isAnt;
        if (isAnt) {
            heading = Heading.randomHeading();
        } else {
            heading = Heading.NONE;
        }
    }

    public void applyRules(Cell[][] cells) {
        // Only the ant obeys the rules; non-ants are inactive
        if (isAnt) {
            LangtonRules rules = (LangtonRules) getRules();
            Heading nextHeading = rules.nextHeading(heading, getCellState());

            // calculate the row and column of the next ant
            int row = getRow();
            int column = getColumn();
            if (nextHeading == Heading.WEST) {
                column = column - 1;
            } else if (nextHeading == Heading.SOUTH) {
                row = row + 1;
            } else if (nextHeading == Heading.EAST) {
                column = column + 1;
            } else { // Heading.NORTH
                row = row - 1;
            }
            LangtonCell nextCell = (LangtonCell) cells[row][column];

            // Set its heading now as a flag to become the ant upon evolution
            nextCell.willBeAnt(nextHeading);

            // Prepare to give up being the ant and flip state upon evolution
            heading = Heading.NONE;
            isAnt = false;
            setCellState(rules.applyRules(getCellState(), 0)); // neighbors are irrelevant
        }
    }

    public void evolve() {
        // If its heading was set during applyRules, it is the new ant
        if (heading != Heading.NONE) {
            isAnt = true;
        } else { // otherwise, evolve as indicated by CellState
            super.evolve();
        }
    }

    public void display() {
        if (isAnt) {
            GameOfLifeApp app = GameOfLifeApp.getApp();
            app.fill(255, 0, 0);
            app.rect(getX(), getY(), getSize(), getSize());
        } else {
            super.display();
        }
    }

    private void willBeAnt(Heading nextHeading) {
        heading = nextHeading;
    }
}
