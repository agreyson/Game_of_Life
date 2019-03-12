public class Rules {
    private int[] birthRules;
    private int[] survivalRules;

    public Rules(int[] birthRules, int[] survivalRules){
        this.birthRules = birthRules;
        this.survivalRules = survivalRules;
    }

    public CellState applyRules(CellState cellState, int liveNeighbors){
        CellState newCellState = cellState;
        if (cellState == CellState.DEAD){
            for (int rule = 0; rule < birthRules.length; rule++){
                if (liveNeighbors == birthRules[rule]){
                    newCellState = CellState.WILL_REVIVE;
                }
            }
        } else if (cellState == CellState.ALIVE){
            boolean survives = false;
            for (int rule = 0; rule < survivalRules.length; rule++){
                if (liveNeighbors == survivalRules[rule]){
                    survives = true;
                }
            }
            if (!survives){
                newCellState = CellState.WILL_DIE;
            }
        }
        return newCellState;
    }
}
