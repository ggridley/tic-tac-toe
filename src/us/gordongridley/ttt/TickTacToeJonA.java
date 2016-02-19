package us.gordongridley.ttt;

import java.util.Scanner;

public class TickTacToeJonA {
	public static String X_WINS = "X wins!";
	public static String O_WINS = "O wins!";
	public static String TIE = "It's a tie!";

	public static final String O_TURN = "O's turn:";
	public static final String X_TURN = "X's turn:";
	public static final String X = "X", O = "O";
	public static final String SPOT_TAKEN = "That spot is already taken, pick again.";
	public static final String INVALID_OPTION = "That is an invalid option. Enter 1-9 only";

	public static final int[][] winConditions = new int[][] { { 0, 1, 2 },
			{ 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
			{ 0, 4, 8 }, { 2, 4, 6 } };

	public static String gamePlay(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			System.out
					.println("This is a traditional Tic - Tac - Toe game written in Java. Enter a number for placement in that section.");
			String[] board = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
			displayBoard(board);

			// Now you're on your own........This is where you start
			int i = 1, j = 0;
			String whoWins = null;
			while ((whoWins = win(board)) == null) {
				System.out.println(i % 2 == 0 ? O_TURN : X_TURN);
				// String indexStr = sc.nextLine();
				String indexStr = args.length == 0 ? sc.nextLine() : args[j];
				String allowed = null;
				if ((allowed = isMoveAllowed(indexStr, board)) != null)
					System.out.println(allowed);
				else
					board[Integer.parseInt(indexStr) - 1] = i++ % 2 == 0 ? O
							: X;
				displayBoard(board);
				j++;
			}
			System.out.println(whoWins.equals(O) ? O_WINS
					: whoWins.equals(X) ? X_WINS : TIE);
			return whoWins.equals(O) ? O_WINS : whoWins.equals(X) ? X_WINS
					: TIE;
		} finally {
			sc.close();
		}
	}

	private static String isMoveAllowed(String indexStr, String[] board) {
		if (!indexStr.matches("[1-9]"))
			return INVALID_OPTION;
		int index = Integer.parseInt(indexStr) - 1;
		if (board[index].equals(X) || board[index].equals(O))
			return SPOT_TAKEN;
		return null;
	}

	private static String win(String[] board) {
		// 012 345 678 is a win
		// 036 147 258 is a win
		// 048 246 is a win
		boolean isTie = true;
		for (int[] i : winConditions) {
			int count = 1;
			String option = board[i[0]];
			if (!option.equals(O) && !option.equals(X)) {
				isTie = false;
				continue;
			}
			for (int j = 1; j < i.length; j++) {
				if (!board[i[j]].equals(O) && !board[i[j]].equals(X))
					isTie = false;
				if (option.equals(board[i[j]]))
					count++;
			}
			if (count == 3)
				return option;
		}

		return isTie ? TIE : null;
	}

	public static void main(String[] args) {
		gamePlay(args);
	}

	private static void displayBoard(String[] board) {
		System.out.println(board[0] + "|" + board[1] + "|" + board[2]);
		System.out.println("-----");
		System.out.println(board[3] + "|" + board[4] + "|" + board[5]);
		System.out.println("-----");
		System.out.println(board[6] + "|" + board[7] + "|" + board[8]);
	}
}