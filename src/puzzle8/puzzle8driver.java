package puzzle8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class puzzle8driver {
	
	
	public static void main(String[] args) {
		List<Enviroment> gameList = new ArrayList<Enviroment>();
		Agent Watson = new Agent();
		readGameFile(gameList);
		System.out.println("========\nCurrent State");
		gameList.get(0).printCurrentEnviromentState();
		Watson.SolvePuzzleHC(gameList.get(0));
		
		
		
		
//		System.out.println("========\nGoal State");
//		gameList.get(0).printGoalEnviromentState();
//		System.out.println("The cost of getting 4 to its goalstate is " + Watson.calculateCost(gameList.get(0), 4));
//		System.out.println("The total cost of solving the game is: "+ Watson.calculateCost(gameList.get(0)));
//		System.out.println("\nmoving blank space down!\n");
//		gameList.get(0).moveSpaceDown();
//		System.out.println("========\nCurrent State");
//		gameList.get(0).printCurrentEnviromentState();
//		System.out.println("========\nGoal State");
//		gameList.get(0).printGoalEnviromentState();
//		System.out.println("The cost of getting 4 to its goalstate is " + Watson.calculateCost(gameList.get(0), 4));
//		System.out.println("The total cost of solving the game is: "+ Watson.calculateCost(gameList.get(0)));
		
	}
	
	public static boolean readGameFile(List<Enviroment> gameList) {
		BufferedReader reader = null;
		String input = null;
		Enviroment gameState = new Enviroment();

		try {
			reader = new BufferedReader(new FileReader("10eightpuzzle.txt"));
		} catch (FileNotFoundException e) {
			return false;
		}

		try {
			while ((input = reader.readLine()) != null) {
				gameState = new Enviroment();
				if (gameState.setInitialState(input) == true){
					input = reader.readLine();
					if (input.length() == 0){
						input = reader.readLine();
					}
					if (gameState.setGoalState(input) == true){
						gameList.add(gameState);
					}else{
						System.out.println("Failed to set goal state");
					}
				}else{
					System.out.println("Failed to set initial state");
				}
			}
		} catch (IOException e) {
			return false;
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
