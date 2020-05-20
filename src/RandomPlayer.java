import java.lang.Math;

public class RandomPlayer extends OthelloPlayer {
    public RandomPlayer() {
        
    }
    public OthelloMove getMove(OthelloState state) {
		state.generateMoves(1);
		int randomMove = (int) (Math.random()*state.generateMoves(1).size());
//		state.applyMove(state.generateMoves(1).get(randomMove));
//	
		if (state.generateMoves(1).size()==0) {
			return null;
		} else {
			return state.generateMoves(1).get(randomMove);
		}
	}
}