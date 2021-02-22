public class MooreRules extends Rules {
    /*
    The Rules class specifies rules using birthRules and survivalRules, boolean arrays
    of length 9, where the index of each element that's set to true indicates the quantity
    of neighbors that satisfies that aspect of the rules.

    For example, if the elements at index 2 and 3 in the survivalRules array are true,
    it means that a live cell survives if it has 2 or 3 live neighbors surrounding it.
    */

    private static final int NUM_NEIGHBORS = 9;
    private boolean[] birthRules;
    private boolean[] survivalRules;

    public MooreRules(int[] birthNeighbors, int[] survivalNeighbors){
        super();
        birthRules = new boolean[NUM_NEIGHBORS];
        survivalRules = new boolean[NUM_NEIGHBORS];

        // use each value of neighbors as an index to toggle that rule element to true
        for (int neighbors: birthNeighbors){
            birthRules[neighbors] = true;
        }
        for (int neighbors: survivalNeighbors){
            survivalRules[neighbors] = true;
        }
    }

    public boolean shouldBeBorn(int liveNeighbors) {
        return birthRules[liveNeighbors];
    }

    public boolean shouldSurvive(int liveNeighbors) {
        return survivalRules[liveNeighbors];
    }

}