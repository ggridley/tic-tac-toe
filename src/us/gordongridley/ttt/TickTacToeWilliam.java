package us.gordongridley.ttt;

import java.util.Random;
import java.util.Scanner;

public class TickTacToeWilliam {
    public static String X_WINS = "X wins!";
    public static String O_WINS = "O wins!";
    public static String TIE = "It's a tie!";
 
    private static boolean player1Computer = false;
    private static boolean player2Computer = true;
 
    private static Random randomGenerator = new Random();
 
    private static String result = null;
    public static String gamePlay(String[] args){
        boolean player1 = true;
        result = null;
        System.out.println("This is a traditional Tic - Tac - Toe game written in Java. Enter a number for placement in that section.");
        String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        populateArgs(board, player1, args);
 
        try (Scanner sc = new Scanner(System.in)) {
            while (!gameDone(board)) {
                displayBoard(board);
                if (player1Computer && player1 || player2Computer && !player1) {
                    computerPlay(board, player1);
                    player1 = !player1;
                } else {
                    printPlayer(player1);
                    String input = sc.next();
                    if (populateInput(board, player1, input)) {
                        player1 = !player1;
                    }
                }
            }
        }
 
        displayBoard(board);
        return result;
    }
 
    private static boolean computerPlay(String[] board, boolean player1) {
        return defendOrWin(board, player1, player1) || defendOrWin(board, player1, !player1) || bestMove(board, player1);
    }
 
    private static boolean defendOrWin(String[] board, boolean currentPlayer, boolean checkPlayer) {
        return (defendOrWin(board, currentPlayer, checkPlayer, 0, 1, 2) || defendOrWin(board, currentPlayer, checkPlayer, 3, 4, 5) || defendOrWin(board, currentPlayer, checkPlayer, 6, 7, 8)
                || defendOrWin(board, currentPlayer, checkPlayer, 0, 3, 6) || defendOrWin(board, currentPlayer, checkPlayer, 1, 4, 7) || defendOrWin(board, currentPlayer, checkPlayer, 2, 5, 8)
                || defendOrWin(board, currentPlayer, checkPlayer, 0, 4, 8) || defendOrWin(board, currentPlayer, checkPlayer, 2, 4, 6));
    }
 
    private static boolean defendOrWin(String[] board, boolean currentPlayer, boolean checkPlayer, int index1, int index2, int index3) {
 
        if (board[index1].equalsIgnoreCase(board[index2]) && board[index1].equalsIgnoreCase(getPlayerSymbol(checkPlayer)) && !isFilled(board, index3)) {
            populateInput(board, currentPlayer, "" + (index3 + 1));
        } else if (board[index2].equalsIgnoreCase(board[index3]) && board[index2].equalsIgnoreCase(getPlayerSymbol(checkPlayer)) && !isFilled(board, index1)) {
            populateInput(board, currentPlayer, "" + (index1 + 1));
        } else if (board[index1].equalsIgnoreCase(board[index3]) && board[index1].equalsIgnoreCase(getPlayerSymbol(checkPlayer)) && !isFilled(board, index2)) {
            populateInput(board, currentPlayer, "" + (index2 + 1));
        } else {
            return false;
        }
 
        return true;
    }
 
    private static boolean isFilled(String[] board, int index) {
        return board[index].equalsIgnoreCase("x") || board[index].equalsIgnoreCase("o");
    }
 
    private static boolean bestMove(String[] board, boolean player1) {
        return pickCenter(board, player1) || fork(board, player1) || stopFork(board, player1) || pickACorner(board, player1) || pickASide(board, player1);
    }
 
    private static boolean fork(String[] board, boolean player1) {
        for (int i = 0; i < board.length; i++) {
            String[] boardCopy = {board[0], board[1], board[2], board[3], board[4], board[5], board[6], board[7], board[8]};
            if (!isFilled(boardCopy, i)) {
                boardCopy[i] = getPlayerSymbol(player1);
                String[] boardSecondCopy = {boardCopy[0], boardCopy[1], boardCopy[2], boardCopy[3], boardCopy[4], boardCopy[5], boardCopy[6], boardCopy[7], boardCopy[8]};
                if (defendOrWin(boardCopy, player1, player1)) {
                    defendOrWin(boardSecondCopy, !player1, player1);
                    if (defendOrWin(boardSecondCopy, player1, player1)) {
                        return populateInput(board, player1, "" + (i + 1));
                    }
                }
            }
        }
        return false;
    }
 
    private static boolean opponentFork(String[] board, boolean player1) {
        for (int i = 0; i < board.length; i++) {
            String[] boardCopy = {board[0], board[1], board[2], board[3], board[4], board[5], board[6], board[7], board[8]};
            if (!isFilled(boardCopy, i)) {
                boardCopy[i] = getPlayerSymbol(!player1);
                if (defendOrWin(boardCopy, !player1, !player1)) {
                    return defendOrWin(boardCopy, !player1, !player1);
                }
            }
        }
        return false;
    }
 
    private static boolean stopFork(String[] board, boolean player1) {
        for (int i = 0; i < board.length; i++) {
            String[] boardCopy = {board[0], board[1], board[2], board[3], board[4], board[5], board[6], board[7], board[8]};
            if (!isFilled(boardCopy, i)) {
                boardCopy[i] = getPlayerSymbol(player1);
                if (!opponentFork(boardCopy, player1)) {
                    return populateInput(board, player1, "" + (i + 1));
                }
            }
        }
        return false;
    }
 
    private static boolean pickCenter(String[] board, boolean player1) {
        return (validateInput(board, 5, true) && populateInput(board, player1, "5"));
    }
 
    private static boolean pickACorner(String[] board, boolean player1) {
        int[] corners = {1, 3, 7, 9};
        return populateInput(board, player1, corners);
    }
 
    private static boolean pickASide(String[] board, boolean player1) {
        int[] sides = {2, 4, 6, 8};
        return populateInput(board, player1, sides);
    }
 
    private static boolean populateInput(String[] board, boolean player1, int[] options) {
        int curMove;
        boolean[] picked = {false, false, false, false};
 
        do {
            if (picked[0] && picked[1] && picked[2] && picked[3]) return false;
            curMove = randomGenerator.nextInt(options.length);
            picked[curMove] = true;
        } while (!validateInput(board, options[curMove], true));
 
        return populateInput(board, player1, "" + options[curMove]);
    }
 
    private static String getPlayerSymbol(boolean player1) {
        return (player1 ? "X" : "O");
    }
 
    private static boolean populateInput(String[] board, boolean player1, String strInput) {
        try {
            int input = Integer.parseInt(strInput);
 
            if (validateInput(board, input, false)) {
                board[input - 1] = getPlayerSymbol(player1);
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input: " + strInput + ", Input should be a number between 1 and 9.");
        }
 
        return false;
    }
 
    private static void populateArgs(String[] board, boolean player1, String[] args) {
        boolean localPlayer1 = player1;
        for (String arg : args){
            if (populateInput(board, localPlayer1, arg)) {
                displayBoard(board);
                if (gameDone(board)) break;
                localPlayer1 = !localPlayer1;
            }
        }
    }
 
    private static String checkResult(String[] board) {
        return (checkResult(board, 0, 1, 2) || checkResult(board, 3, 4, 5) || checkResult(board, 6, 7, 8)
                || checkResult(board, 0, 3, 6) || checkResult(board, 1, 4, 7) || checkResult(board, 2, 5, 8)
                || checkResult(board, 0, 4, 8) || checkResult(board, 2, 4, 6) ? result : null);
    }
 
    private static boolean checkResult(String[] board, int index1, int index2, int index3) {
        if (board[index1].equalsIgnoreCase(board[index2]) && board[index2].equalsIgnoreCase(board[index3])) {
            result = (board[index1].equalsIgnoreCase("x") ? X_WINS : O_WINS);
            return true;
        }
 
        return false;
    }
 
    private static void printPlayer(boolean player1) {
        System.out.print((player1 ? "Player1: " : "Player2: "));
    }
 
    private static boolean validateInput(String[] board, int input, boolean noPrint) {
        if (input < 1 || input > 9) {
            if (!noPrint) System.out.println(input + " is an invalid option.  Enter 1-9 only");
            return false;
        } else if (isFilled(board, input - 1)) {
            if (!noPrint) System.out.println(input + " is an invalid option.  Position is already filled");
            return false;
        }
 
        return true;
    }
 
    private static boolean gameDone(String[] board) {
        result = checkResult(board);
        if (result == null) {
            for (int i = 0; i < board.length; i++) {
                if (!isFilled(board, i)) return false;
            }
 
            result = TIE;
        }
 
        return true;
    }
 
    public static void main(String[] args) {
        System.out.println(gamePlay(args));
    }
 
    private static void displayBoard(String[] board) {
        System.out.println("=========");
        System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---------");
        System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---------");
        System.out.println(board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println("=========");
    }
}
