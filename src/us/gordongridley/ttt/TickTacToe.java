package us.gordongridley.ttt;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class TickTacToe {

	public static String X_WINS = "X wins!";
	public static String O_WINS = "O wins!";
	public static String TIE = "It's a tie!";
	private static ArrayList<Integer[]> winningRows = new ArrayList<Integer[]>();
	
	public static String gamePlay(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("This is a traditional Tic - Tac - Toe game written in Java. ");
        
		String[] board = {"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7",
			"8",
			"9"};
		displayBoard(board);
		
		try {
			populateWinningRows();
			boolean gameover = false;
			boolean xturn = true;
			boolean commandLine = false;
			if(args!=null && args.length>0){
				commandLine = true;
			}
			int turnCounter = 0;
			while(!gameover){
				if (xturn){
					System.out.println("X's turn:");
				} else {
					System.out.println("O's turn:");
				}
				String placement = null;
				if (commandLine){
					placement = args[turnCounter];
				} else {
					placement = sc.next();
				}
				try{
					validatePlacement(placement, board);
					int position = Integer.valueOf(placement);
					if (xturn){
						board[position-1]= "X";
						xturn = false;
					} else {
						board[position-1]= "O";
						xturn = true;
					}
					displayBoard(board);
					
					String winner = findWinner(board);
					if (StringUtils.isNotBlank(winner)){
						if("X".equals(winner)){
							System.out.println(X_WINS);
							gameover = true;
							return X_WINS;
						} else if ("O".equals(winner)){
							System.out.println(O_WINS);
							gameover = true;
							return O_WINS;
						} else {
							gameover = true;
							return TIE;
						}
					}
					turnCounter++;
				} catch (RuntimeException e){
					turnCounter++;
					System.out.println(e.getMessage());
					// keep going, don't stop the game!
				}
			}
			return null;
		 } finally {
			 sc.close();
		 }
	}
	private static void populateWinningRows() {
		winningRows.add(new Integer[]{1,2,3});
		winningRows.add(new Integer[]{4,5,6});
		winningRows.add(new Integer[]{7,8,9});
		winningRows.add(new Integer[]{1,5,9});
		winningRows.add(new Integer[]{3,5,7});
		winningRows.add(new Integer[]{3,6,9});
		winningRows.add(new Integer[]{1,4,7});
		winningRows.add(new Integer[]{2,5,8});		
	}
	
	public static void main(String[] args) {
		gamePlay(args);
	}

	private static String findWinner(String[] board) {
		for (Integer[] row : winningRows){
			String winningRow = checkRow(board, row[0], row[1], row[2]);
			if (StringUtils.isNotBlank(winningRow)){
				return winningRow;
			}
		}
		for (int i = 0; i < 9; i++){
			try {
				Integer.parseInt(board[i]);
				return null;
			} catch (NumberFormatException e) {
				if (i == 8){
					return "tie";
				}
			}
		}				
		return null;
	}

	private static String checkRow(String[] board, int i, int j, int k) {
		if (StringUtils.isNotBlank(board[i-1])
			&& StringUtils.isNotBlank(board[j-1])
			&& StringUtils.isNotBlank(board[k-1])
			&& (board[i-1].equals(board[j-1]) && board[i-1].equals(board[k-1]))){
				return board[i-1];
			}
		return null;
	}

	private static void validatePlacement(String placement, String[] board) {
		if (!placement.equals("1")
				&& !placement.equals("2")
				&& !placement.equals("3")
				&& !placement.equals("4")
				&& !placement.equals("5")
				&& !placement.equals("6")
				&& !placement.equals("7")
				&& !placement.equals("8")
				&& !placement.equals("9")){
			throw new RuntimeException("That is an invalid option.  Enter 1-9 only");
		}
		if (board[Integer.valueOf(placement)-1].equals("X") || board[Integer.valueOf(placement)-1].equals("O")){
			throw new RuntimeException("That spot is already taken, pick again.");
		}
	}

	private static void displayBoard(String[] board){
		System.out.println(board[0]+"|"+board[1]+"|"+ board[2]);
		System.out.println("-----");
		System.out.println(board[3]+"|"+board[4]+"|"+ board[5]);
		System.out.println("-----");
		System.out.println(board[6]+"|"+board[7]+"|"+ board[8]);
	}
}

