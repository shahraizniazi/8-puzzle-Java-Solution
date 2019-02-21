import java.util.LinkedList;
import java.util.Arrays;
import java.util.*;

/**
 * Represents a state (placement of tiles) of the puzzle.
 * Starter code.
 * @author Steven Bogaerts
 */
public class PuzzleState {
    
    /**
     * A 1-D array representation of the tile placement.
     */
    private int[] tiles;
    
    /**
     * Assumes that initialTiles is the valid format:
     * length 9,
     * containing the integers 0 through 8 inclusive in some order
     * with no repeats
     * where 0 represents the blank.
     */
    public PuzzleState(int[] initialTiles) {
        tiles = initialTiles;
    }

    /**
     * Returns the position of a given tile in the puzzle state.
     * Returns -1 if not found. (Should never happen for valid input.)
     */
    public int posOf(int tile) {
        for(int i=0;i< this.tiles.length;i++){
            if (this.tiles[i] == tile){ return i;}
        }
        return -1;
    }
    
    /**
     * Returns the tile value at the given index (0 through 8, inclusive).
     */
    public int getTileAt(int index) {
        if(index < 0 && index > this.tiles.length){return -1;}
        else{return this.tiles[index];}
    }
    
    /**
     * Returns a list of PuzzleStates that can be reached from this PuzzleState
     * in one move. It's easiest to think of this as moving the blank.
     */
    public LinkedList<PuzzleState> expand() {
       
        int indexOfZero = posOf(0);
        int temp;
        LinkedList<PuzzleState> ans = new LinkedList<PuzzleState>();   
        
        
         if ((indexOfZero -3 ) >= 0 && (indexOfZero -3 ) <= 8){
            int[] tempArray = Arrays.copyOf(this.tiles, 9);
            temp = this.tiles[indexOfZero - 3];
            tempArray[indexOfZero] = temp;
            tempArray[indexOfZero -3] = 0;
            ans.add(new PuzzleState(tempArray));
        }
        
        
        
        if ((indexOfZero + 3) >= 0 && (indexOfZero + 3) <= 8){
            int[] tempArray = Arrays.copyOf(this.tiles, 9);
            temp = this.tiles[indexOfZero + 3];
            tempArray[indexOfZero] = temp;
            tempArray[indexOfZero +3] = 0;
            ans.add(new PuzzleState(tempArray));
        }
        
        if ((indexOfZero -1 ) >= 0 && (indexOfZero -1 ) <= 8 && (indexOfZero != 0 && indexOfZero != 3 && indexOfZero != 6)){
            int[] tempArray = Arrays.copyOf(this.tiles, 9);
            temp = this.tiles[indexOfZero -1];
            tempArray[indexOfZero] = temp;
            tempArray[indexOfZero -1] = 0;
            ans.add(new PuzzleState(tempArray));
        }
            
        if ((indexOfZero +1 ) >= 0 && (indexOfZero +1 ) <= 8 && (indexOfZero != 2 && indexOfZero != 5 && indexOfZero != 8)){
            int[] tempArray = Arrays.copyOf(this.tiles, 9);
            temp = this.tiles[indexOfZero +1];
            tempArray[indexOfZero] = temp;
            tempArray[indexOfZero +1] = 0;
            ans.add(new PuzzleState(tempArray));
        }
        
        
        
        return ans;
        
    }
    
    /**
     * Returns true if two PuzzleState objects are equivalent,
     * false otherwise. Required for proper operation of a HashSet of PuzzleStates.
     */
    @Override

    public boolean equals(Object other) {
        if (!(other instanceof PuzzleState))
            return false;
        else
            return Arrays.equals(this.tiles, ((PuzzleState) other).tiles);
    }
  
    
    /**
     * Required to be able to have a HashSet of PuzzleState objects.
     * Objects for which .equals() returns true must have the same hashCode.
     */
    
    
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }
    
    /**
     * Returns a String representation of the state.
     */
    @Override
    public String toString() {
        String result = "\n";
        for(int i = 0; i < tiles.length; i++) {
            if ((i > 0) && i % 3 == 0)
                result += "\n";
                
            if (tiles[i] == 0)
                result += "_ ";
            else
                result += tiles[i] + " ";
        }
                
        return result + "\n";
    }
}