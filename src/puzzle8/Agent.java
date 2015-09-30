package puzzle8;

import java.util.List;

import java.util.Random;

import puzzle8.Enviroment;

public class Agent {
	
	public int SolvePuzzleHC(Enviroment cur){
		int cost = 0;
		List<Enviroment> successorStates = cur.getSuccessorStates();
		Enviroment lowest = new Enviroment();
		int minSolutionCost = cur.calculateCost();
		
		while (calculateCost(cur) > 0){
			for (Enviroment successorEnvState:successorStates){
				if (successorEnvState.calculateCost() >= minSolutionCost){
					minSolutionCost = successorEnvState.calculateCost();
					if (lowest != successorEnvState){
						lowest = successorEnvState;
					}
				}
			}
			if (lowest.calculateCost() <= cur.calculateCost()){
				cur = lowest;
				cost++;
			}
		}

		return cost;
	}
	
	public boolean SolvePuzzleRR(Enviroment cur){
		boolean bResult = false;
		Random r = new Random();
		
		while (calculateCost(cur) > 0){
			cur.getSuccessorStates();
		}
		return bResult;
	}
	
	public boolean SolvePuzzleSA(Enviroment cur){
		boolean bResult = false;
		
		
		return bResult;
	}
	
	
	
	///
	public int calculateCost(Enviroment currentEnviroment){
		int cost = -1;
		
		cost = 0;
		for (int i = 0 ; i < 8 ; i++){
			for (int j = 0 ; j < 3 ; j++){
				for (int k = 0 ; k < 3 ; k++){
					if (currentEnviroment.enviromentState[j][k] == i){
						cost += calculateCost(currentEnviroment, i);
					}
				}
			}
		}
		
		return cost;
		
	}
	
	public int calculateCost(Enviroment currentEnviroment, int target){
		int cost = -1;
		Tile targetCurrentLocation = new Tile();
		Tile targetGoalLocation = new Tile();
		
		for (int j = 0 ; j < 3 ; j++){
			for (int k = 0 ; k < 3 ; k++){
				if (currentEnviroment.goalState[j][k] == target){
					targetGoalLocation.setCoords(j, k);
				}
			}
		}
		
		for (int j = 0 ; j < 3 ; j++){
			for (int k = 0 ; k < 3 ; k++){
				if (currentEnviroment.enviromentState[j][k] == target){
					targetCurrentLocation.setCoords(j, k);
				}
			}
		}
		
		cost = 0;
		
		cost = Math.abs((targetCurrentLocation.horizontal - targetGoalLocation.horizontal)) + Math.abs((targetCurrentLocation.vertical - targetGoalLocation.vertical));
		
		return cost;
		
	}

}
