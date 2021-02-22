public class Rules {
    /*
    The Rules class specifies rules using birthRules and survivalRules, boolean arrays
    of length 9, where the index of each element that's set to true indicates the quantity
    of neighbors that satisfies that aspect of the rules.

    For example, if the elements at index 2 and 3 in the survivalRules array are true,
    it means that a live cell survives if it has 2 or 3 live neighbors surrounding it.
    */

    private static final int numNeighbors = 9;
    private boolean[] birthRules;
    private boolean[] survivalRules;

    public Rules(int[] birthNeighbors, int[] survivalNeighbors){
        birthRules = new boolean[numNeighbors];
        survivalRules = new boolean[numNeighbors];

        for (int neighbors : birthNeighbors) {
            birthRules[neighbors] = true;
        }
        for (int neighbors : survivalNeighbors) {
            survivalRules[neighbors] = true;
        }
    }

    public CellState applyRules(CellState cellState, int liveNeighbors){
        if (cellState == CellState.DEAD && birthRules[liveNeighbors]){
            return CellState.WILL_REVIVE;
        } else if (cellState == CellState.ALIVE && !survivalRules[liveNeighbors]){
            return CellState.WILL_DIE;
        } else {
            return cellState;
        }
    }
}
