public abstract class Rules {
    public Rules(){

    }
    public abstract CellState applyRules(CellState cellState, int liveNeighbors);
}
