/**
 * This defines the displacement heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class DisplacementHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public DisplacementHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    
    
    
    public int distance(PuzzleState state) {
       int dis = 0;
        for(int i=0; i < 8; i++){
            if(i+1 != state.getTileAt(i)){dis++;}   
        }
        return dis;
        
    }
    
}