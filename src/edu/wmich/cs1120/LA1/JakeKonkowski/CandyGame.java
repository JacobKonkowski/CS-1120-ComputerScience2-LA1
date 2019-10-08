package edu.wmich.cs1120.LA1.JakeKonkowski;

import java.util.Random;
import java.util.Scanner;

public class CandyGame {
	
	private Scanner scanner;

	public CandyGame() {

	}

	public int getNumberOfStudents(int lowerLimit, int upperLimit) throws Exception {
		
		
		//Fail fast
		if (lowerLimit > upperLimit || lowerLimit <= 0) { 
			throw new Exception("Invalid lower or upper limit");
		}
		
		int numberOfStudents = 0;
		
		scanner = new Scanner(System.in);
		
		int desiredNumberOfStudents = 0;
		
		if (scanner.hasNextInt()) {
			desiredNumberOfStudents = scanner.nextInt();
		}
		
		if (desiredNumberOfStudents >= lowerLimit && desiredNumberOfStudents <= upperLimit) {
			numberOfStudents = desiredNumberOfStudents;
		} else {
			System.out.println(String.format("Must be in [%s, %s] inclusive!"
					+ " Re-enter: ", lowerLimit, upperLimit));
			numberOfStudents = getNumberOfStudents(lowerLimit, upperLimit);
		}
		
		return numberOfStudents;
		
	}
	
	public int getLimitOfStartingPiecesOfCandy(int lowerLimit, int upperLimit) throws Exception {
		
		if (lowerLimit > upperLimit || lowerLimit <= 0 || lowerLimit % 2 != 0 || upperLimit % 2 != 0) {
			throw new Exception("Invalid lower or upper limit");
		}
		
		int limitOfStartingPiecesOfCandy = 0;
		
		scanner = new Scanner(System.in);
		
		int desiredLimitOfStartingPiecesOfCandy = 0;
		
		if (scanner.hasNextInt()) {
			desiredLimitOfStartingPiecesOfCandy = scanner.nextInt();
		}
		
		if (desiredLimitOfStartingPiecesOfCandy >= lowerLimit &&
				desiredLimitOfStartingPiecesOfCandy <= upperLimit &&
				desiredLimitOfStartingPiecesOfCandy % 2 == 0) {
			limitOfStartingPiecesOfCandy = desiredLimitOfStartingPiecesOfCandy;
		} else {
			System.out.println(String.format("Must be EVEN and in [%s, %s] inclusive!"
					+ " Re-enter: ", lowerLimit, upperLimit));
			limitOfStartingPiecesOfCandy = getLimitOfStartingPiecesOfCandy(lowerLimit, upperLimit);
		}
		
		return limitOfStartingPiecesOfCandy;
		
	}
	
	public void distributeCandy(int[] students, int lowerLimit, int upperLimit) {
		
		for (int i = 0; i < students.length; i++) {
			Random rand = new Random();
			
			/*
			 * The idea behind this is that we generate a random integer from 0 to the
			 * (max limit - the lower) limit, divide by 2, and then multiply by two. This
			 * ensures that the number will always be even because when the integer is 
			 * divided by 2, it won't have a decimal place. As a result, multiplying by
			 * 2 will yield an even number. After that, we add the lower limit. This results
			 * in our lower limit and max limit still being in effect.
			 */
			students[i] = lowerLimit + (rand.nextInt((upperLimit-lowerLimit)/2) * 2);
		}
		
	}
	
	public void displayStudents(int[] students) {
		
		for (int i = 0; i < students.length; i++) {
			//Field width is 4
			System.out.printf("%4s", students[i]);
		}
		//Enters a new line after the array is printed
		System.out.println("");
		
	}
	
	public void passCandy(int[] students) {
		
		scanner = new Scanner(System.in);
		
		//I don't like null, so I initialized this as -1.
		int eachStepDesired = -1;
		
		if (scanner.hasNextInt()) {
			eachStepDesired = scanner.nextInt();
		}
		
		if (eachStepDesired != 0 && eachStepDesired != 1) {
			System.out.println("MUST be an integer in [0, 1] inclusive! Re-enter:");
			passCandy(students);
			return;
		}
		
		//Printing new lines to look nice
		System.out.println("");
		
		boolean gameComplete = false;
		
		while (!gameComplete) {
			
			//Temporary holding value of candy
			int passingCandy = 0;
			
			for (int i = 0; i < students.length; i++) {
				//Temporary holding value independent of passingCandy
				//Used to receive the candy that was passed
				int candyToPass = passingCandy;
				
				//Cut students candy in half
				students[i] = students[i]/2;
				
				//Pass half the students candy
				passingCandy = students[i];
				
				//Receive the candy that was passed
				students[i] += candyToPass;
			}
			
			//Adds the last students passed candy to the first student
			//Thus completing the circular queue
			students[0] += passingCandy;
			
			for (int i = 0; i < students.length; i++) {
				//Add 0 if the students candy is even
				//Add 1 to make the students candy even if it is odd
				students[i] += (students[i] % 2 == 0 ? 0 : 1);
			}
			
			if (checkForGameCompletion(students)) {
				displayStudents(students);
				gameComplete = true;
				return;
			}
			
			if (eachStepDesired == 1) {
				displayStudents(students);
			}
		
		}
			
	}
	
	public boolean checkForGameCompletion(int[] students) {
		
		int firstStudent = students[0];
		
		//Game is defaultly over
		boolean isGameOver = true;
		
		for (int i = 0; i < students.length; i++) {
			//If any of the students candy doesn't match the first student's
			//the game should continue
			if (students[i] != firstStudent) {
				isGameOver = false;
			}
		}
		
		return isGameOver;
		
	}
	
}
