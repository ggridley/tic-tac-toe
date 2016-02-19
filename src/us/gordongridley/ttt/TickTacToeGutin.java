package us.gordongridley.ttt;

import java.util.Scanner;

public class TickTacToeGutin {
    public static String X_WINS = "X";
    public static String O_WINS = "O";
    public static String TIE = "It's a tie!";
     
    static String[] board = {"1", "2", "3",
                             "4", "5", "6",
                             "7", "8", "9"};
     
    public static String gamePlay(String[] args){
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        for(int i=0;i<args.length;i++){
            try{
                int turn = Integer.parseInt(args[i]);
                if(turn > 0 && turn < 10 && board[turn-1] != "O" && board[turn-1] != "X"){
                    board[turn-1] = counter%2 == 0 ? "X" : "O";
                    counter++;
                }
                String checkWin = checkWin();
                if(checkWin != null){
                    break;
                }
            }catch(NumberFormatException e){
                System.err.println(e);
            }
        }
        try {
            System.out.println("This is a traditional Tic - Tac - Toe game written in Java. Enter a number for placement in that section.");
             
  
            // Now you're on your own........This is where you start
            boolean oTurn = false;
            while(true){
                displayBoard(board);
                String checkWin = checkWin();
                if(checkWin != null){
                    System.out.println(checkWin() + " Wins!");
                    return checkWin();
                }
                System.out.print((oTurn ? "O" : "X") +"'s turn: ");
                int turn = 0;
                try{
                    turn = sc.nextInt();
                }catch(Exception e){
                    System.err.println(e);
                }
                if(turn < 1 || turn > 9){
                    System.out.println("That is an invalid option.  Enter 1-9 only");
                    continue;
                }else if(board[turn-1] == "X" || board[turn-1] == "O"){
                    System.out.println("That spot is already taken, pick again.");
                    continue;
                }
                board[turn-1] = oTurn? "O" : "X";
                oTurn = !oTurn;
            }
        } finally {
            board = new String[]{"1", "2", "3",
                 "4", "5", "6",
                 "7", "8", "9"};
            sc.close();
        }
    }
     
    private static String checkWin(){
        for(int i=0;i<3;i++){
            if(checkPart(i) != null){
                return checkPart(i);
            }
        }
        if(boardFull()){
            return TIE;
        }
        return null;
    }
     
    private static boolean boardFull() {
        for(int i=0;i<board.length;i++){
            if(board[i] != "X" && board[i] != "O"){
                return false;
            }
        }
        return true;
    }
    private static String checkPart(int index){
        if((board[index] == board[index+3] && board[index] == board[index+6])){
            return board[index];
        }
        if(board[3*index] == board[3*index +1] && board[3*index] == board[3*index +2]){
            return board[3*index];
        }
        if(index != 2 && board[2*index] == board[4] && board[4] == board[8-2*index]){
            return board[4];
        }
        return null;
    }
  
    public static void main(String[] args) {
        gamePlay(args);
    }
  
    private static void displayBoard(String[] board){
        System.out.println(board[0]+"|"+board[1]+"|"+ board[2]);
        System.out.println("-----");
        System.out.println(board[3]+"|"+board[4]+"|"+ board[5]);
        System.out.println("-----");
        System.out.println(board[6]+"|"+board[7]+"|"+ board[8]);
    }
}
