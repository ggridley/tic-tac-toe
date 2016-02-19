package us.gordongridley;

import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;

public class NameMaker {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("This program will try to regenerate your password.  Enter your password:");
		
		String nameToGet = sc.nextLine();
		String starWarsName = "";
		int counter = 0;
		while (!starWarsName.equals(nameToGet)){
			starWarsName = RandomStringUtils.random(nameToGet.length(),nameToGet);
			System.out.println(starWarsName + " (" + counter + ")");
			counter++;
		}
		System.out.println("it took: " + counter + " tries for me to come up with the name: " );
		sc.close();
	}
	
	

}
