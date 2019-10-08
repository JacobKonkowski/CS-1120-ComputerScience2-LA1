package edu.wmich.cs1120.LA1.JakeKonkowski;

public class Controller {
	
	public static void main(String[] args) throws Exception {
		
		CandyGame candyGame = new CandyGame();
		
		System.out.println("Getting the number of students");
		System.out.println("Enter an integer in [15,30] inclusive: ");
		int numberOfStudents = candyGame.getNumberOfStudents(15, 30);
		
		int[] students = new int[numberOfStudents];
		
		System.out.println("\nGetting the lower number of starting candy pieces from 4 to 10.");
		System.out.println("Enter an even integer in [4, 10] inclusive:");
		int lowerLimitOfStartingPiecesOfCandy = candyGame.getLimitOfStartingPiecesOfCandy(4, 10);
		
		int lowerLimitPlus50 = lowerLimitOfStartingPiecesOfCandy + 50;
		int nextEvenNumber = lowerLimitOfStartingPiecesOfCandy + 2;
		
		System.out.println("\nGetting the upper number of starting candy pieces");
		System.out.println(String.format("Must be even and greater than %s (the lower number)"
				+ " but less than or equal to %s (the lower number plus 50).",
				lowerLimitOfStartingPiecesOfCandy, lowerLimitPlus50));
		System.out.println(String.format("Enter an even integer in [%s, %s] inclusive:",
				nextEvenNumber, lowerLimitPlus50));
		int upperLimitOfStartingPiecesOfCandy = candyGame.getLimitOfStartingPiecesOfCandy(nextEvenNumber,
				lowerLimitPlus50);
		
		candyGame.distributeCandy(students, lowerLimitOfStartingPiecesOfCandy,
				upperLimitOfStartingPiecesOfCandy);
		
		System.out.println("\nThe original deal is:\n");
		candyGame.displayStudents(students);
		
		System.out.println("\nWe are ready to play the game.");
		System.out.println("Do you want to print the status after each move? (1 for yes, 0 for no)");
		System.out.println("Enter an integer in [0, 1] inclusive:");
		candyGame.passCandy(students);
		
	}

}
