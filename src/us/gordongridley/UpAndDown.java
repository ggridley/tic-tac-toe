package us.gordongridley;

import java.util.Random;
import java.util.Scanner;

public class UpAndDown {

	public static void main(String[] args) {
		Random rand = new Random();
		int maxNumber = 1000;
		Integer magicNumber = rand.nextInt(maxNumber);
		//System.out.println("your magic number is: "+magicNumber);
		
			takeGuess(magicNumber, maxNumber);
		
	}

	private static void takeGuess(Integer correctNumber, Integer maxNumber) {
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number from 1 to " + maxNumber + ":");
        Integer guess = -1;
        Integer counter =0;
       
        while(!guess.equals(correctNumber)){
        	counter++;
        	guess=sc.nextInt();
        	if (guess < correctNumber){
        		System.out.println("higher...");
        	}
        	if (guess > correctNumber){
        		System.out.println("lower...");
        	}
        	
        }
    	System.out.println("correct!  It took you "+ counter +" guesses.");
		sc.close();
		
	}

}
