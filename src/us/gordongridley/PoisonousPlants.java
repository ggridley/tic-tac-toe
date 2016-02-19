package us.gordongridley;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PoisonousPlants {
	
	static List<Integer> poisonList = new ArrayList<Integer>();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		try {
			getGardenInput(sc);
			Integer dayNumber = 0;
			displayPoisonList(poisonList);
			int survivalCount = poisonList.size();
			boolean survivalCountIsStable = false;
			long start = System.currentTimeMillis();
			while (!survivalCountIsStable){
				poisonList = simulateEndOfDay(poisonList);
				if (poisonList.size() == survivalCount){
					survivalCountIsStable = true;
				} else {
					survivalCount = poisonList.size();
					dayNumber++;
				}
			}
			displayPoisonList(poisonList);
			System.out.println("Days: " + dayNumber);
			long elapsedTimeMillis = System.currentTimeMillis() - start;
			System.out.println("It took " + elapsedTimeMillis / 1000 + " seconds to identify how many days plants survived.");

		} finally {
			sc.close();
		}
	}
	
	private static void displayPoisonList(List<Integer> poisonList){
		System.out.print("{");
		for (int i = 0; i < poisonList.size(); i++){
			System.out.print(poisonList.get(i));
			if (i < poisonList.size()-1){
				System.out.print(",");
			}
		}
		System.out.println("}");
	}
	
	private static void getGardenInput(Scanner sc) {
		System.out.println("Enter the number of plants (1 or more):");
		int numberOfPlants = sc.nextInt();
		
		System.out.println("Randomly generate pesticide use on each plant?");
		String randomGenerate = sc.next();
		if ("Y".equalsIgnoreCase(randomGenerate)){
			assignPesticide(true, numberOfPlants);
		} else {
			assignPesticide(false, numberOfPlants);
		}
		
	}
	
	private static void assignPesticide(boolean randomlyPesticide, Integer numberOfPlants){
		Random rand = new Random();
		int max = 1000000000;
		int min = 0;
		for (int i = 0; i < numberOfPlants; i++){
			Integer pesticideAmount = null;
			if (!randomlyPesticide) {
				System.out.println("Enter how many ounces (integer) of pesticide used on plant #" + (i+1));
				pesticideAmount = sc.nextInt();
			} else {
				pesticideAmount =  rand.nextInt((max - min) + 1) + min;
			}
			poisonList.add(pesticideAmount);
		}
	}
	public static List<Integer> simulateEndOfDay(List<Integer> poisonList){
		List<Integer> newPoisonList = new ArrayList<Integer>();
		// The first one in the row automatically survives since nothing is on it's left.
		newPoisonList.add(poisonList.get(0));
		for (int i = 1; i < poisonList.size(); i++){
			if (poisonList.get(i) <= poisonList.get(i-1)){
				newPoisonList.add(poisonList.get(i));
			}
		}
		return newPoisonList;
	}
}
