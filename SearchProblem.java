import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Comparator;

/**
 * Represents a search problem to be solved.
 * 
 * This is somewhat specific to the 8-Puzzle, since a more general solution would require increased use of
 * generics, which we're avoiding in this course for now.
 * @author Steven Bogaerts
 */
public class SearchProblem {

    // *************************** TO DO - define whatever fields you need here
    private Queue<PuzzlePath> frontier;
    private PuzzleState initState;
    private PuzzleState goalState;
    private int goalCheckLimit;    
    private Heuristic h;
    /**
     * This Comparator object is an instance of an anonymous class.
     * It compares two paths based on cost, for use in the PriorityQueue, for ordering.
     */
    public static Comparator<PuzzlePath> pathComparator = new Comparator<PuzzlePath>() {
        public int compare(PuzzlePath p1, PuzzlePath p2) {
            return p1.getCost() - p2.getCost();
        }
    };
    
    /**
     * Constructs a new SearchProblem.
     * 
     * @param queueType For our purposes for now, assume that queueType is either "FIFO" (meaning BFS will be done)
     * or "Ordered" (meaning A* will be done).
     * 
     * @param goalCheckLimit Check if some state is a goal up to this many times, after which you give up the search.
     * This is useful for problems that turn out to be impossible to solve.
     * 
     * @param h The Heuristic, should be an instance of the NoHeuristic class if you're using FIFO (BFS) as the queueType.
     * Otherwise pass an appropriate Heuristic object.
     */
    public SearchProblem(PuzzleState initState, PuzzleState goalState, String queueType, int goalCheckLimit, Heuristic h) {
        // *************************** TO DO - do whatever initialization you need here
        this.initState = initState;
        this.goalState = goalState;
        
        if(queueType.equals("FIFO")){
            frontier = new LinkedList<PuzzlePath>();
        } 
        
        else if(queueType.equals("Ordered")){
           
            Comparator<PuzzlePath> pathComparator = new Comparator<PuzzlePath>()
            {
            public int compare(PuzzlePath p1, PuzzlePath p2) {
                return p1.getCost() - p2.getCost();
            }
            };
            frontier =  new PriorityQueue<PuzzlePath>(100, pathComparator);
        
        }
        
        else {
            System.err.println("Not a valid queue type");
        }
        
        this.goalCheckLimit = goalCheckLimit;
        this.h = h;
        
        // Check the value of queueType, and set the frontier to the correct type.
    }
    
    /**
     * Solve this search problem.
     */
    public boolean solve() {
        // *************************** TO DO
        int counter =0;
        LinkedList<PuzzleState> expandedStates = null; 
        PuzzlePath temp = null;
        PuzzlePath temp2 = null;
        frontier.offer(new PuzzlePath(initState, h));
        HashSet<PuzzleState> expanded = new HashSet<PuzzleState>();
        
        while(frontier.size() !=0 && counter!=goalCheckLimit){
            temp = frontier.poll();
            counter++;
            
            if(temp.stateAtEndOfPath().equals(goalState)){
                System.out.println("States Checked: " + counter);
                System.out.println("Solution length: " + temp.length());
                System.out.println("Solution");
                System.out.println(temp);
                return true;
            }
            
            expanded.add(temp.stateAtEndOfPath());
            expandedStates = temp.stateAtEndOfPath().expand(); 
           
            for(PuzzleState p: expandedStates){
               temp2 = temp.makeCopy();
               temp2.addState(p);
               
               if(!expanded.contains(temp2.stateAtEndOfPath())){
                   frontier.offer(temp2);
                }
            }
            
        
        }
        System.out.println("No valid path");
        return false;
    }
        
}