import java.util.ArrayList;
import java.util.*;

public class GreedyPlayer extends OthelloPlayer {
    public GreedyPlayer() {
        
    }
    public OthelloMove getMove(OthelloState state) {
    	ArrayList<Integer> scoreCheck = new ArrayList<Integer>();
    	OthelloState check = state.clone();
    	state.generateMoves(1);
    	for (int i = 0; i < state.generateMoves(1).size(); i++) {
    		check.applyMove(state.generateMoves(1).get(i));
    		scoreCheck.add(check.getP2Score());
    		check = state.clone();
    	}
    	
    	if (state.generateMoves(1).size()==0) {
			return null;
		} else {
			return state.generateMoves(1).get(scoreCheck.indexOf(Collections.max(scoreCheck)));
		}

	}
}
