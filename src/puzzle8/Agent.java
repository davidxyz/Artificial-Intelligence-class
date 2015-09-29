package puzzle8;

public class Agent {
	
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
