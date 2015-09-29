package queens8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Queens8Driver {
	public static void main(String[] args) {
		List<Enviroment> gameList = new ArrayList<Enviroment>();
		readGameFile(gameList);
		gameList.get(0).printEnviromentState();
	}
	public static boolean readGameFile(List<Enviroment> gameList) {
		BufferedReader reader = null;
		String input = null;
		Enviroment gameState = new Enviroment();

		try {
			reader = new BufferedReader(new FileReader("10eightqueens.txt"));
		} catch (FileNotFoundException e) {
			return false;
		}

		try {
			while ((input = reader.readLine()) != null) {
				gameState = new Enviroment();
				if (gameState.setInitialState(input) == true){
					System.out.println("Set initial state");
					gameList.add(gameState);
				}else{
					System.out.println("Failed to set initial state");
				}
			}
			//hillClimbing steepest Ascent
			int cost = 0;
			float solved=0;
			for(int i=0;i<gameList.size();i++){
				cost = hillClimbingSteepestAscent(gameList.get(i));
				if(cost == 0){
					solved++;
				}
			}
			System.out.printf("solved problems: %.2f\n",(solved/gameList.size())*100);
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
	/**
	 * Non limiting steepest ascent which aborts if it found the perfect solution or a local minimum
	 * @param env
	 * @return minimum heuristic cost: local minimum or global minimum
	 */
	public static int hillClimbingSteepestAscent(Enviroment env){
		List<Enviroment> successorStates = env.getSuccessorStates();
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env; //start with current env
		boolean foundSuccessor = true; //bool on whether the algoirthm can find a successor state with a lower cost
		
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		while(minHCost!=0 && foundSuccessor){
			foundSuccessor = false;
			
			for(int i=0; i<successorStates.size();i++){
				int curHCost = hueristicCost(successorStates.get(i));
				if(curHCost<minHCost){
					minHCost = curHCost;
					successorEnv = successorStates.get(i);
					foundSuccessor = true;
				}
			}
			successorStates = successorEnv.getSuccessorStates();
		}
		return minHCost;
	}
	public static int hueristicCost(Enviroment env){
		int[][] enviromentState = env.getEnviromentState();
		int hcost = 0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				
				//found queen
				if(enviromentState[i][j] == 1){
					
					for(int x = 1; x<8 ;x++){
						//attack on row
						if(enviromentState[i][(j+x)%7] == 1){
							hcost++;
						}
						//attack on column
						if(enviromentState[(i+x)%7][j] == 1){
							hcost++;
						}
						//attack on diagonal
						//take into account chess boarders
						if(i+x<8&&j+x<8&&enviromentState[i+x][j+x] == 1){
							hcost++;
						}
						if(i-x>=0&&j-x>=0&&enviromentState[i-x][j-x] == 1){
							hcost++;
						}
						if(i-x>=0&&j+x<8&&enviromentState[i-x][j+x] == 1){
							hcost++;
						}
						if(i+x<8&&j-x>=0&&enviromentState[i+x][j-x] == 1){
							hcost++;
						}
					}
				}
			}
		}
		return hcost;
	}
	
}
