
import acm.program.*;

public class CentimetersToFeetAndInches extends ConsoleProgram {
	
	public void run() {
		int[][] board = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};
		displayBoard(board);
	};
		

	
	private void displayBoard(int[][] boardArg) {
		for (int row = 0; row < 3; row++) {
			if (row > 0) println("-----+-----+-----");
			println("     |     |");
			print("  ");
			for (int col = 0; col < 3; col++) {
				if (col > 0) print("  |  ");
				print(boardArg[row][col]);
			};
			println();
			println("     |     |");
		};
	};
	
	
	
};