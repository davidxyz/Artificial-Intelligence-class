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
		readGameFile(gameList);
		System.out.println("========");
		gameList.get(5).printEnviromentState();
		gameList.get(5).printBlankSpaceLocation();
		System.out.println("========");
		System.out.println("moving right");
		gameList.get(5).moveSpaceRight();
		gameList.get(5).printEnviromentState();
		gameList.get(5).printBlankSpaceLocation();
		System.out.println("========");
		System.out.println("moving up");
		gameList.get(5).moveSpaceUp();
		gameList.get(5).printEnviromentState();
		gameList.get(5).printBlankSpaceLocation();
		System.out.println("========");
		System.out.println("moving left");
		gameList.get(5).moveSpaceLeft();
		gameList.get(5).printEnviromentState();
		gameList.get(5).printBlankSpaceLocation();
		System.out.println("========");
		System.out.println("moving down");
		gameList.get(5).moveSpaceDown();
		gameList.get(5).printEnviromentState();
		gameList.get(5).printBlankSpaceLocation();
		System.out.println("========");
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
