package queens8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Queens8Driver {
	final static boolean DEBUG = false;
	public static void main(String[] args) {
		List<Enviroment> gameList;
		gameList=readGameFile();
		testSimulatedAnnealing(gameList);
		gameList=readGameFile();
		testHillClimbingRandomRestart(gameList);
		gameList=readGameFile();
		testHillClimbingSteepestAscent(gameList);
		gameList=readGameFile();
		testHillClimbingFirstChoice(gameList);
	}
	public static List<Enviroment> readGameFile() {
		List<Enviroment> gameList = new ArrayList<Enviroment>();
		BufferedReader reader = null;
		String input = null;
		Enviroment gameState = new Enviroment();

		try {
			reader = new BufferedReader(new FileReader("10eightqueens.txt"));
		} catch (FileNotFoundException e) {
			return null;
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
		
		} catch (IOException e) {
			return null;
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gameList;
	}
	public static void testHillClimbingSteepestAscent(List<Enviroment> gameList){
		//hillClimbing steepest Ascent
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = hillClimbingSteepestAscent(gameList.get(i));
			if(cost == 0){
				
				solved++;
			}
		}
		System.out.printf("(hill climbing steepest ascent): solved problems: %.2f\n",(solved/gameList.size())*100);
	}
	public static void testHillClimbingFirstChoice(List<Enviroment> gameList){
		//hillClimbing steepest Ascent
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = hillClimbingFirstChoice(gameList.get(i));
			if(cost == 0){
				
				solved++;
			}
		}
		System.out.printf("(hill climbing first choice): solved problems: %.2f\n",(solved/gameList.size())*100);
	}
	public static void testHillClimbingRandomRestart(List<Enviroment> gameList){
		//hillClimbing steepest Ascent
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = hillClimbingRandomRestart(gameList.get(i));
			if(cost == 0){
				
				solved++;
			}
		}
		System.out.printf("(hill climbing random restart): solved problems: %.2f\n",(solved/gameList.size())*100);
	}
	public static void testSimulatedAnnealing(List<Enviroment> gameList){
		//hillClimbing steepest Ascent
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = simulatedAnnealing(gameList.get(i));
			if(cost == 0){
				
				solved++;
			}
		}
		System.out.printf("(simulated annealing): solved problems: %.2f\n",(solved/gameList.size())*100);
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
		boolean foundSuccessor = true; //bool on whether the algoirthim can find a successor state with a lower cost
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		//we or it with the bool of whether we have 8 queens on the board because the minHCost will be very low with less than 8 queens on the boards
		while((minHCost!=0 && foundSuccessor)||successorEnv.getNumQueens()!=8){
			foundSuccessor = false;
			
			for(int i=0; i<successorStates.size();i++){
				int curHCost = hueristicCost(successorStates.get(i));
				if(curHCost<minHCost){
					minHCost = curHCost;
					successorEnv = successorStates.get(i);
					foundSuccessor = true;
				}
			}
			//reset minHCost if we don't have 8 queens on the board
			if(successorEnv.getNumQueens()!=8){
				minHCost = Integer.MAX_VALUE;
			}
			
			successorStates = successorEnv.getSuccessorStates();
		
		}
		if(DEBUG&&minHCost==0){
			System.out.printf("solved:\n");
			System.out.printf("ENV init state:\n");
			successorEnv.printInitialState();
			System.out.println("solution:\n");
			successorEnv.printEnviromentState();
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
						if(enviromentState[i][(j+x)%8] == 1){
							hcost++;
						}
						//attack on column
						if(enviromentState[(i+x)%8][j] == 1){
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
		if(DEBUG){
			//System.out.println("Inside heuristic function with ENV:");
			//env.printEnviromentState();
			//System.out.printf("hcost is:%d\n",hcost);
		}
		return hcost;
	}
	//complete but inefficient
	public static int hillClimbingFirstChoice(Enviroment env){
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env; //start with current env
		Enviroment successorEnvContender= null;
		
		
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		while(minHCost!=0 || successorEnv.getNumQueens()!=8){

			successorEnvContender = successorEnv.getSuccessorStateRandomly();
			int curHCost = hueristicCost(successorEnvContender);
			if(curHCost<minHCost){
				minHCost = curHCost;
				successorEnv = successorEnvContender;
			}
			//reset minHCost if we don't have 8 queens on the board
			//because the minHCost doesn't count when less than 8 queens are on board
			if(successorEnv.getNumQueens()!=8){
				minHCost = Integer.MAX_VALUE;
			}
	}
		
		if(DEBUG&&minHCost==0){
			System.out.printf("solved:\n");
			System.out.printf("ENV init state:\n");
			successorEnv.printInitialState();
			System.out.println("solution:\n");
			successorEnv.printEnviromentState();
		}
		return minHCost;
	}
	public static int hillClimbingRandomRestart(Enviroment env){
		List<Enviroment> successorStates = env.getSuccessorStates();
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env; //start with current env
		boolean foundSuccessor; //bool on whether the algorithim can find a successor state with a lower cost
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		while(minHCost!=0){//until complete
			minHCost = Integer.MAX_VALUE;
			foundSuccessor =true;
			//essentially steepest ascent inside a random initial enviroment restarter if we can't find a successor
			while((minHCost!=0 && foundSuccessor)||successorEnv.getNumQueens()!=8){
				foundSuccessor = false;

				for(int i=0; i<successorStates.size();i++){
					int curHCost = hueristicCost(successorStates.get(i));
					if(curHCost<minHCost){
						minHCost = curHCost;
						successorEnv = successorStates.get(i);
						foundSuccessor = true;
					}
				}
				//reset minHCost if we don't have 8 queens on the board
				if(successorEnv.getNumQueens()!=8){
					minHCost = Integer.MAX_VALUE;
				}
				successorStates = successorEnv.getSuccessorStates();
			}
			//random restarter
			if(!foundSuccessor){
				//coudn't find a successor generate a random valid initial state and try again:
				successorEnv = Enviroment.generateRandomInitialEnv();
				successorStates = successorEnv.getSuccessorStates();
			}

		}
		
		if(DEBUG&&minHCost==0){
			System.out.printf("solved:\n");
			System.out.printf("ENV init state:\n");
			successorEnv.printInitialState();
			System.out.println("solution:\n");
			successorEnv.printEnviromentState();
		}
		return minHCost;
		
	}
	public static int simulatedAnnealing(Enviroment env){
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env; //start with current env
		Enviroment successorEnvContender= null;
		//perfect solution minHCost = 0
		int badnessOfMove;
		double probabilityOfAcceptingBadMoveBase=0.999;//less than 1
		double probabilityOfAcceptingBadMove;
		while(minHCost!=0 || successorEnv.getNumQueens()!=8){

			successorEnvContender = successorEnv.getSuccessorStateRandomly();
			int curHCost = hueristicCost(successorEnvContender);
			
			if(curHCost<minHCost){
				minHCost = curHCost;
				successorEnv = successorEnvContender;
			}else{
				badnessOfMove = curHCost - minHCost;
				probabilityOfAcceptingBadMove = probabilityOfAcceptingBadMoveBase+Math.exp(-badnessOfMove);//exponentially decreases
				
				if(didTheProbabilityOccur(probabilityOfAcceptingBadMove)){
					successorEnv = successorEnvContender;
				}
			}
			probabilityOfAcceptingBadMoveBase-=0.004;//randomly chosen
			//reset minHCost if we don't have 8 queens on the board
			if(successorEnv.getNumQueens()!=8){
				minHCost = Integer.MAX_VALUE;
			}
	}

	if(DEBUG&&minHCost==0){
		System.out.printf("solved:\n");
		System.out.printf("ENV init state:\n");
		successorEnv.printInitialState();
		System.out.println("solution:\n");
		successorEnv.printEnviromentState();
	}
	return minHCost;

}
	public static boolean didTheProbabilityOccur(double chance){
		Random rand= new Random();
		int randnum = rand.nextInt((100 - 0) + 1);
		chance = Math.round(chance * 1000.0) / 1000.0;
		double iterator = 0;
		
		while((int)(iterator*1000)!=(int)(chance*1000)){
			if(randnum == (int)(iterator*1000)){
				return true;
			}
			iterator+=0.001;
		}
		return false;
	}

}
