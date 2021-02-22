public class Cell {
    private final int x;
    private final int y;
    private final int size;
    private CellState cellState;
    private final int row;
    private final int column;
    private Rules rules;

    Cell(int x, int y, int size, int row, int column, CellState cellState, Rules rules){
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    public void applyRules(Cell[][] cells){
        int liveNeighbors = countLiveNeighbors(cells);
        cellState = rules.applyRules(cellState, liveNeighbors);
//        if (cellState == CellState.ALIVE && (liveNeighbors < 2 || liveNeighbors > 3)){ // live cell dies if under or overpopulated
//            cellState = CellState.WILL_DIE;
//        } else if (cellState == CellState.DEAD && liveNeighbors == 3){ // dead cell with 3 neighbors comes alive
//            cellState = CellState.WILL_REVIVE;
//        }
    }

    public void evolve(){
        // complete the transition to alive or dead
        if (cellState == CellState.WILL_REVIVE){
            cellState = CellState.ALIVE;
        } else if (cellState == CellState.WILL_DIE){
            cellState = CellState.DEAD;
        }
    }

    public void display(){
        GameOfLifeApp app = GameOfLifeApp.getApp();
        if (cellState == CellState.ALIVE){
            app.fill(0);
            app.stroke(255);
        } else {
            app.fill(255);
            app.stroke(0);
        }
        app.rect(x, y, size, size);
    }

    public void handleClick(){
        if (cellState == CellState.ALIVE){
            cellState = CellState.DEAD;
        } else if (cellState == CellState.DEAD){
            cellState = CellState.ALIVE;
        }
    }

    private int countLiveNeighbors(Cell[][] cells){
        int liveNeighbors = 0;
        if ((cells[row-1][column].cellState == CellState.ALIVE) || (cells[row-1][column].cellState == CellState.WILL_DIE)){ // upper cell
            liveNeighbors += 1;
        }
        if ((cells[row-1][column-1].cellState == CellState.ALIVE) || (cells[row-1][column-1].cellState == CellState.WILL_DIE)){ // upper left cell
            liveNeighbors += 1;
        }
        if ((cells[row-1][column+1].cellState == CellState.ALIVE) || (cells[row-1][column+1].cellState == CellState.WILL_DIE)){ // upper right cell
            liveNeighbors += 1;
        }
        if ((cells[row][column+1].cellState == CellState.ALIVE) || (cells[row][column+1].cellState == CellState.WILL_DIE)){ // right cell
            liveNeighbors += 1;
        }
        if ((cells[row][column-1].cellState == CellState.ALIVE) || (cells[row][column-1].cellState == CellState.WILL_DIE)){ // left cell
            liveNeighbors+=1;
        }
        if ((cells[row+1][column].cellState == CellState.ALIVE) || (cells[row+1][column].cellState == CellState.WILL_DIE)){ // lower cell
            liveNeighbors += 1;
        }
        if ((cells[row+1][column-1].cellState == CellState.ALIVE) || (cells[row+1][column-1].cellState == CellState.WILL_DIE)){ // lower left cell
            liveNeighbors += 1;
        }
        if ((cells[row+1][column+1].cellState == CellState.ALIVE) || (cells[row+1][column+1].cellState == CellState.WILL_DIE)){ // lower right cell
            liveNeighbors += 1;
        }
        return liveNeighbors;
    }

}
