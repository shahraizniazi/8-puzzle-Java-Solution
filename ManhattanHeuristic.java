import java.util.*;
import java.lang.*;

/**
 * This defines the Manhattan heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class ManhattanHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public ManhattanHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    
    public int distance(PuzzleState state) {
        
        int distance =0;
        int curr_r, curr_c;
        int to_be_r, to_be_c;
        
        for(int i =0; i<9; i++){ 
        
       
            if (state.getTileAt(i) != 0){
                to_be_r = (state.getTileAt(i) -1) / 3;
                to_be_c = (state.getTileAt(i) -1) % 3;
                
                curr_r = i/3;
                curr_c = i%3; 
                
                distance = distance + (Math.abs(to_be_r - curr_r)) + (Math.abs(to_be_c - curr_c)); 
            }
           
        }
         return distance;  
    }
         
            
}
        
     
    

