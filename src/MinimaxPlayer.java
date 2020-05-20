public class MinimaxPlayer extends OthelloPlayer {
	
	OthelloMove BEST_MOVE = null;
	int SEARCH_DEPTH = 5;
	
	MinimaxPlayer() {
	}
	
	public OthelloMove getMove(OthelloState state) {
		minimax(SEARCH_DEPTH, true, state);
		return BEST_MOVE;
		
	}
	
	public int minimax(int depth, boolean isMinimaxPlayer, OthelloState game) {
		
		if (depth <= 0) {
			return 0;
		}
		
		if (isMinimaxPlayer) {
			int max = -100000;
			for (int moves = 0; moves < game.generateMoves(0).size(); moves++) {
				OthelloState clone = game.applyMoveCloning(game.generateMoves(0).get(moves));
				int score = minimax(depth - 1, !isMinimaxPlayer, clone);
				if (score > max) {
					max = score;
					BEST_MOVE = game.generateMoves(0).get(moves);
				}
			}
			return max;
		} else {
			int min = 100000;
			for (int moves = 0; moves < game.generateMoves(1).size(); moves++) {
				OthelloState clone = game.applyMoveCloning(game.generateMoves(1).get(moves));
				int score = minimax(depth - 1, !isMinimaxPlayer, clone);
				if (score < min) {
					min = score;
					BEST_MOVE = game.generateMoves(1).get(moves);
				}
			}
			return min;
		}
		
	
	}
}
