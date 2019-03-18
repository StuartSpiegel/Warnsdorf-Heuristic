import java.util.Random;

/**
 * @author stuart spiegel
 *  Date : 11/4/2018
 */
public class WarnsdorffHeuristic {
	// dimension of the board
	private static final int dimension = 8;

	// saved move patterns for the knight based on change in x and y coordinates
	private static int changeXMoves[] = { 1, 1, 2, 2, -1, -1, -2, -2 };
	private static int changeYMoves[] = { 2, -2, 1, -1, 2, -2, 1, -1 };

	private static int[][] board;
	public static void main(String[] args) {
		int numCalls = 0;
		
		board = new int[dimension][dimension];
		while(!findClosedTour()) {
			findClosedTour();
			numCalls++;
			System.out.print(numCalls);
			System.out.println();
		}
		
		
	}
	/**
	 * 
	 * @param theMoves holds the candidate moves
	 * @param x coordinate of knight
	 * @param y coordinate of knight
	 * @return whether the move is valid in the context of the board
	 * 
	 * function ensures the knight remains on the board
	 */
	public static boolean isValid(int[] theMoves, int x, int y) {

		return ((x >= 0 && y >= 0) && (x < dimension && y < dimension));

	}

	/**
	 * 
	 * @returns the number of empty squares adjacent to the knights curr position
	 */
	public static int getNumSquares(int[] numMoves, int x, int y) {

		int count = 0;
		for (int i = 0; i < dimension; ++i)
			if (isValid(numMoves, (x + changeXMoves[i]), (y + changeYMoves[i])))
				count++;

		return count;
	}

	/**
	 * 
	 * @param theMoves
	 *            holds the candidate moves
	 * @param x
	 *            coordinate of the knight
	 * @param y
	 *            coordinate of the knight
	 * @return whether the move is possible
	 * 
	 *         Try all N adjacent to (x, y) starting from random position and finds
	 *         the adjacent move with minimum degree
	 * 
	 */
	public static boolean nextMove( int x, int y, int totalMovesSoFar, int[][] board) {

		int delta;
		int min_DEGREE_INDEX = -1;
		int minDegree = dimension + 1;
		int nextX;
		int nextY;
		int[][] moveBoard = new int[dimension][dimension];
		moveBoard[x][y] = totalMovesSoFar;
		if(totalMovesSoFar == dimension*dimension) {
			return true;
		}

		// sort valid next moves by their value on the board
		
		// for each move
		
			// decrease valid moves for each of its neighbors
			// recursively call on that move
			// if it returns true
				
			// else increase valid moves for each of its neighbors
		
		


		moveBoard[x][y] = -1;
		return false;

	}

	/**
	 * prints out the chessboard to the console
	 * 
	 * @param theMoves
	 */
	public static void print(int theMoves[]) {
		for (int i = 0; i < dimension; ++i) {
			for (int j = 0; j < dimension; ++j)
				System.out.print(theMoves[j * dimension + i]);
			System.out.println();
		}
	}

	/**
	 * 
	 * checks if the knight ends on a square that is one knight move from the
	 * starting square. If so the tour is closed.
	 */
	public static boolean neighbour(int x, int y, int xx, int yy) {
		for (int i = 0; i < dimension; ++i)
			if (((x + changeXMoves[i]) == xx) && ((y + changeYMoves[i]) == yy))
				return true;

		return false;
	}

	public static boolean findClosedTour() {

		int[] theMoves;
		theMoves = new int[dimension * dimension];

		// filling the chessboard array with -1's.
		for (int i = 0; i < dimension * dimension; ++i) {
			theMoves[i] = -1;
		}

		Random r = new Random();
		// Randomize initial position
		
		//--start position 
		int startX = r.nextInt(dimension + 1) % dimension;
		int startY = r.nextInt(dimension + 1) % dimension;
		
		//int startX = 0;
		//int startY = 0;
		

		// set the current point to the coordinates of the starting points
		int x = startX;
		int y = startY;

		theMoves[(y * dimension) + x] = 1; // Mark the first move

		// pick new candidate moves using Warnsdorff heuristic 
		for (int i = 0; i < (dimension * dimension) - 1; ++i) {
			if (nextMove(theMoves, x, y) == false) //mark
				return false; 

		}
		// check if the tour is closed
		if (!neighbour(x, y, startX, startY)) {
			return false;

		}

		print(theMoves);
		return true;

	}

}
