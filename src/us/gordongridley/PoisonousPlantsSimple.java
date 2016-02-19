package us.gordongridley;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PoisonousPlantsSimple {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> poisonList = new ArrayList<Integer>();
		try {
			int numberOfPlants = sc.nextInt();
			for (int i = 0; i < numberOfPlants; i++){
				poisonList.add(sc.nextInt());
			}
			Integer dayNumber = 0;
			int survivalCount = poisonList.size();
			boolean survivalCountIsStable = false;
			while (!survivalCountIsStable){
				poisonList = simulateEndOfDay(poisonList);
				if (poisonList.size() == survivalCount){
					survivalCountIsStable = true;
				} else {
					survivalCount = poisonList.size();
					dayNumber++;
				}
			}
			System.out.println(dayNumber);
		} finally {
			sc.close();
		}

    }
	private static List<Integer> simulateEndOfDay(List<Integer> poisonList){
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
