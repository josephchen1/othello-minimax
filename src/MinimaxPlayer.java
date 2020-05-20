import java.util.*;

public class MinimaxPlayer extends OthelloPlayer {
	ArrayList<OthelloState> queue = new ArrayList<OthelloState>();

	// 2 depth
	// assume mnimaxplayer = 0 or player 1
	// checks first depth to see possible moves
	// apply possible moves and applies them to create new OthelloStates
	// after n amojnt of depth; see which score has max and min
	public MinimaxPlayer() {

	}

	public OthelloMove getMove(OthelloState state) {
		ArrayList<Integer> minmaxScoreCheck = new ArrayList<Integer>();
		ArrayList<ScoreIndex> minmaxScoreCheck1 = new ArrayList<ScoreIndex>();
		ArrayList<Integer> minScoreCheck = new ArrayList<Integer>();
		ArrayList<Integer> finalScoreCheck = new ArrayList<Integer>();
		ArrayList<ScoreIndex> minScoreCheck1 = new ArrayList<ScoreIndex>();
		ArrayList<ScoreIndex> finalScoreCheck1 = new ArrayList<ScoreIndex>();
		ArrayList<Integer> emerg_minmaxScoreCheck = new ArrayList<Integer>();
		ArrayList<Integer> emerg_indexTrack = new ArrayList<Integer>();
		ArrayList<Integer> indexTrack = new ArrayList<Integer>();

		OthelloState check = state.clone();
		OthelloState check3 = new OthelloState(8);
		Boolean gateway = true;
		Boolean gateway2 = true;
		int d2moves = 0;
		int d3moves = 0;
		int d1moves = 0;

		//checks up to depth 3 to see which move is the most optimal using minimax method
		
		//checks depth 1
		for (int i = 0; i < check.generateMoves(0).size(); i++) {

			d1moves = check.generateMoves(0).size();
			check = check.applyMoveCloning(check.generateMoves(0).get(i));
			
			//emerg functions act as storage of information when the code can't run past the 
			//first loop due to lack of actions avaliable 
			emerg_minmaxScoreCheck.add(check.getP1Score() - check.getP2Score());
			emerg_indexTrack.add(i);

			//checks depth 2
			for (int j = 0; j < check.generateMoves(1).size(); j++) {
				//keeps track of how many search options there are
				d2moves = check.generateMoves(1).size();

				check = check.applyMoveCloning(check.generateMoves(1).get(j));

				//checks depth 3
				for (int k = 0; k < check.generateMoves(0).size(); k++) {
					//keeps track of how many search options there are
					d3moves = check.generateMoves(0).size();
					
					//gateway checks whether there are more actions in the resulting state
					gateway2 = false;
					OthelloState check2 = check.applyMoveCloning(check.generateMoves(0).get(k));
					check3 = check2;

					//gateway checks whether there are more actions in the resulting state
					gateway = false;

					//Calculates the Score that is used to apply the minimax algorithm
					minmaxScoreCheck1.add(new ScoreIndex(check2.getP1Score() - check2.getP2Score(),i));
					//tracks index of first depth so we can remember which path to take
					indexTrack.add(i);

				}
			}
			//resets state for next search
			check = state.clone();
		}

		//Applying the max of the last depth, 3.
		//d3moves is used to slice the amount of search options there are for each node
		for (int n = 0; n < minmaxScoreCheck1.size(); n += d3moves) {
			//Removes unnecessary out of bound errors
			if (n + d3moves < minmaxScoreCheck1.size() && (d3moves) != 0) {
				
				//Slicing the array minmaxScoreCheck from (n to n+d3moves) 
				// to match the amount of search options
				List<Integer> d2;
				for (int i = 0; i<minmaxScoreCheck1.size(); i++) {
				//	d2.add(minmaxScoreCheck1.get(i).getScore());
				}
				//Get the max of each sliced array
				//int max = Collections.max(d2);
				//Append this to a new array, minScoreCheck, to represent the scores in depth 2
			//	minScoreCheck1.add(max);
				//If out of index, break
			} else {
				break;
			}
			//Used to slice array without repeating indices (e.g slicing [1-5] then [6-10] then [11- 15])
			n++;
		}

		
		//Applying the min of depth 2
		//d2moves is used to slice the amount of search options in depth 2
		for (int b = 0; b < minScoreCheck.size(); b += d2moves) {
			//Removes unnecessary out of bounds errors
			if (b + d2moves < minScoreCheck.size() && (d2moves) != 0) {
				//Slicing the array minScoreCheck from (b, b + d2moves)
				// to match the amount of search options in depth 2
				List<Integer> d3 = minScoreCheck.subList(b, b + d2moves);
				//Get the min of each sliced array
				int final2 = Collections.min(d3);
				//Append this to a new array, finalScoreCheck, to represent the scores in depth 1
				finalScoreCheck.add(final2);
				//If out of index, break
			} else {
				break;
			}
			//Used to slice array without repeating indices (e.g slicing [1-5] then [6-10] then [11-15])
			b++;
		}
		
		System.out.println(d1moves);
		System.out.println(finalScoreCheck.size());


		if (state.generateMoves(0).size() > 0) {
			if (check3.generateMoves(1).size() == 0 || gateway == true || gateway2 == true
					|| finalScoreCheck.size() == 0) {
				int something = Collections.max(emerg_minmaxScoreCheck);
				int index = emerg_minmaxScoreCheck.indexOf(something);
				return state.generateMoves(0).get(emerg_indexTrack.get(index));
			} else {
				//Retrieves the Max of the first depth ( or the best move )
				int something = Collections.max(finalScoreCheck);
				//Get the index of that value
				int index = finalScoreCheck.indexOf(something);
				
				index *= 4;
				return state.generateMoves(0).get(indexTrack.get(index));
			}
		} else {
			return null;
		}

	}
}