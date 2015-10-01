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
		testHillClimbingSteepestAscent(gameList);
		//testHillClimbingFirstChoice(gameList);
		

	}
	
	public static void testHillClimbingSteepestAscent(List<Enviroment> gameList){
		Agent hillClimber = new Agent();
		//hillClimbing steepest Ascent
		
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = hillClimber.SolvePuzzleHC(gameList.get(i));
			if(cost == 0){
				solved++;
			}
		}
		System.out.printf("solved problems: %.2f\n",(solved/gameList.size())*100);
	}
	
	public static void testHillClimbingFirstChoice(List<Enviroment> gameList){
		Agent hillClimberFirstChoice = new Agent();
		//hillClimbing first choice/Random restart
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = hillClimberFirstChoice.SolvePuzzleRR(gameList.get(i));
			if(cost == 0){
				
				solved++;
			}
		}
		System.out.printf("(hill climbing first choice): solved problems: %.2f\n",(solved/gameList.size())*100);
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
