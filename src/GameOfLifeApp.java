import processing.core.PApplet;

public class GameOfLifeApp extends PApplet {
    private static GameOfLifeApp app; // reference to itself
    private Cell[][] cells;
    private boolean evolve;

    private static final int CELL_SIZE = 10;

    public GameOfLifeApp(){
        evolve = false;
    }
    public static void main(String[] args){
//        String[] processingArgs = {"GameOfLifeApp"};
//        GameOfLifeApp mySketch = new GameOfLifeApp();
//        app = mySketch;
//        PApplet.runSketch(processingArgs, mySketch);
        app = new GameOfLifeApp();
        app.runSketch();
    }

    public void settings(){
        size(1000, 500);
    }

    public void setup(){
        frameRate(2);

        // establish rules
        Rules rules = new MooreRules(new int[]{3}, new int[]{2, 3}); // B3/S23 Game of Life
        //Rules rules = new Rules(new int[]{1, 3, 5, 7}, new int[]{1, 3, 5, 7}); // B1357/S1357 Replicator
        //Rules rules = new Rules(new int[]{3, 6}, new int[]{2, 3}); // B36/S23 HighLife
        //Rules rules = new Rules(new int[]{2}, new int[]{}); // B2/S Seeds
        //Rules rules = new Rules(new int[]{1}, new int[]{1, 2}); // B1/S12 single live cell approximates Sierpiński triangle

        // Initialize the board
        cells = new Cell[height/CELL_SIZE][width/CELL_SIZE];
        for (int row = 0; row < cells.length; row++){
            for (int column = 0; column < cells[0].length; column++){
                CellState cellState;
                if (row == 0 || row == (cells.length - 1) || column == 0 || column == (cells[0].length - 1)){
                    cellState = CellState.DEAD; // need to create a neutral border
                } else {
                    //cellState = CellState.randomState();
                    cellState = CellState.DEAD;
                }
                Cell cell = new Cell(column * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, row, column, cellState, rules);
                cells[row][column] = cell;
            }
        }
    }

    public void draw(){
        // draw the next generation of the board (the border remains inert)
        if (evolve == true) {
            applyRules();
            evolve();
        }
        display();
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
        int col = mouseX/CELL_SIZE;
        int row = mouseY/CELL_SIZE;
        cells[row][col].handleClick();
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
        evolve = !evolve;
    }

    public static GameOfLifeApp getApp(){
        return app;
    }

    private void applyRules(){
        // ask each cell within the border to calculate its next state
        for (int row = 1; row < cells.length - 1; row++){
            for (int column = 1; column < cells[0].length - 1; column++){
                cells[row][column].applyRules(cells);
            }
        }
    }

    private void evolve(){
        // only evolve cells within the border
        for (int row = 1; row < cells.length - 1; row++){
            for (int column = 1; column < cells[0].length - 1; column++){
                cells[row][column].evolve();
            }
        }
    }

    private void display(){
        for (int row = 0; row < cells.length; row++){
            for (int column = 0; column < cells[0].length; column++){
                cells[row][column].display();
            }
        }
    }
}
