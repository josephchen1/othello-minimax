import java.util.concurrent.TimeUnit;


public class Main {
	public static void main (String[] args) throws InterruptedException
	{
		final int boardSize = 8;
		//use other types of players later.
		OthelloPlayer p1 = new MinimaxPlayer();
		OthelloPlayer p2 = new RandomPlayer();
		OthelloPlayer curr = p1;

		//Generate the initial state of the game.
		OthelloState start = new OthelloState(boardSize);

		//Until the game ends, players take turns moving.
		while(!start.gameOver()){
			//select a move and play it
			OthelloMove nextMove = curr.getMove(start);
			System.out.println(nextMove);
			start = start.applyMoveCloning(nextMove);
			System.out.println(start);

			TimeUnit.MILLISECONDS.sleep(10);

			//switch players
			if (curr == p1)
				curr = p2;
			else
				curr = p1;
		}

		//final game board
		System.out.println("Final Game Board:");
		System.out.println(start);

		//scores for each player
		System.out.println("Score of P1: " + start.getP1Score());
		System.out.println("Score of P2: " + start.getP2Score());

		//declare the winner
		if (start.score() > 0)
		{
			System.out.println("Player 1 is the winner.");
		}
		else if (start.score() < 0)
		{
			System.out.println("Player 2 is the winner.");
		}
		else
		{
			System.out.println("There's been a tie.");
		}
	}
}
