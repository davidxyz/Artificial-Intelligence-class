package puzzle8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import puzzle8.Enviroment;

public class Agent {
	final static boolean DEBUG = true;
	static int simPathCost=0;
	static int randRestartPathCost=0;
	static int steepestAscentPathCost=0;
	static int firstChoicePathCost=0;
	
	
	public static void main(String[] args) {
		List<Enviroment> gameList = new ArrayList<Enviroment>();
		readGameFile(gameList);
		//testHillClimbingSteepestAscent(gameList);
		//testHillClimbingFirstChoice(gameList);
		//testHillClimbingRandomRestart(gameList);
		testSimulatedAnnealing(gameList);
		

	}
	
	public static void testSimulatedAnnealing(List<Enviroment> gameList){
		Agent simulatedAnnealing = new Agent();
		//Simulated Annealing
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = simulatedAnnealing.solvePuzzleSimAnnealing(gameList.get(i));
			if(cost == 0){
				
				solved++;
			}
		}
		System.out.printf("(simulated annealing): solved problems: %.2f\tavg search cost: %d\n",(solved/gameList.size())*100,(int)(simPathCost/gameList.size()));
	}
	
	public static void testHillClimbingRandomRestart(List<Enviroment> gameList){
		//hillClimbing steepest Ascent
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = solvehillClimbingRandomRestart(gameList.get(i));
			if(cost == 0){
				
				solved++;
			}
		}
		System.out.printf("(hill climbing random restart): solved problems: %.2f\tavg search cost: %d\n",(solved/gameList.size())*100,(int)(randRestartPathCost/gameList.size()));
	}
	
	public static void testHillClimbingSteepestAscent(List<Enviroment> gameList){
		Agent hillClimber = new Agent();
		//hillClimbing steepest Ascent
		
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = hillClimber.solvePuzzleSteepestAscent(gameList.get(i));
			if(cost == 0){
				solved++;
			}
		}
		System.out.printf("(hill climbing steepest ascent): solved problems: %.2f\tavg search cost: %d\n",(solved/gameList.size())*100,(int)(steepestAscentPathCost/gameList.size()));
	}
	
	public static void testHillClimbingFirstChoice(List<Enviroment> gameList){
		Agent hillClimberFirstChoice = new Agent();
		//hillClimbing first choice
		int cost = 0;
		float solved=0;
		for(int i=0;i<gameList.size();i++){
			cost = hillClimberFirstChoice.solveHillClimbFirstChoice(gameList.get(i));
			if(cost == 0){
				
				solved++;
			}
		}
		System.out.printf("(hill climbing first choice): solved problems: %.2f\tavg search cost: %d\n",(solved/gameList.size())*100,(int)(firstChoicePathCost/gameList.size()));
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
	
	public static int solvehillClimbingRandomRestart(Enviroment env){
		List<Enviroment> successorStates = env.getSuccessorStates();
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env.clone(); //start with current env
		boolean foundSuccessor; //bool on whether the algorithim can find a successor state with a lower cost
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		while(minHCost!=0){//until complete
			minHCost = Integer.MAX_VALUE;
			foundSuccessor =true;
			//essentially steepest ascent inside a random initial enviroment restarter if we can't find a successor
			while((minHCost!=0 && foundSuccessor)||successorEnv.totalCost()!=0){
				foundSuccessor = false;

				for(int i=0; i<successorStates.size();i++){
					int curHCost = successorStates.get(i).totalCost();
					if(curHCost<minHCost){
						minHCost = curHCost;
						successorEnv = successorStates.get(i);
						foundSuccessor = true;
						
					}
				}
				randRestartPathCost++;
				if(successorEnv.totalCost()!=0){
					minHCost = Integer.MAX_VALUE;
				}
				successorEnv.printCurrentEnviromentState();
				successorStates = successorEnv.getSuccessorStates();
			}
			//random restarter
			if(!foundSuccessor){
				//coudn't find a successor generate a random valid initial state and try again:
				successorEnv = env.getSuccessorStateRandomly();
			}

		}
		
		if(DEBUG&&minHCost==0){
			System.out.printf("solved:\n");
			System.out.printf("ENV init state:\n");
			successorEnv.printInitialEnviromentState();
			System.out.println("solution:\n");
			successorEnv.printCurrentEnviromentState();
		}
		return minHCost;
		
	}
	
	
	public int solvePuzzleSteepestAscent(Enviroment env) {
		List<Enviroment> successorStates = env.getSuccessorStates();
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env.clone(); //start with current env
		boolean foundSuccessor = true; //bool on whether the algoirthim can find a successor state with a lower cost
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		while((minHCost!=0 && foundSuccessor)||successorEnv.totalCost()!=0){
			foundSuccessor = false;
			
			for(int i=0; i<successorStates.size();i++){
				int curHCost = successorStates.get(i).totalCost();
				if(curHCost<minHCost){
					minHCost = curHCost;
					successorEnv = successorStates.get(i);
					foundSuccessor = true;
					successorEnv.printCurrentEnviromentState();
				}
			}
			steepestAscentPathCost++;
			//reset minHCost if we don't have 8 queens on the board
			if(successorEnv.totalCost()!=0){
				minHCost = Integer.MAX_VALUE;
			}
			
			successorStates = successorEnv.getSuccessorStates();
		
		}
		if(DEBUG&&minHCost==0){
			System.out.printf("solved:\n");
			System.out.printf("ENV init state:\n");
			successorEnv.printInitialEnviromentState();
			System.out.println("solution:\n");
			successorEnv.printGoalEnviromentState();
		}
		return minHCost;
	}

	public int solveHillClimbFirstChoice(Enviroment env) {
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env.clone(); //start with current env
		Enviroment successorEnvContender= null;
		
		
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		while(minHCost!=0 || successorEnv.totalCost()!=0){

			successorEnvContender = successorEnv.getSuccessorStateRandomly();
			int curHCost = successorEnvContender.totalCost();
			if(curHCost<minHCost){
				minHCost = curHCost;
				successorEnv = successorEnvContender;
			}
			firstChoicePathCost++;
			if(successorEnv.totalCost()!=0){
				minHCost = Integer.MAX_VALUE;
			}
	}
		
		if(DEBUG&&minHCost==0){
			System.out.printf("solved:\n");
			System.out.printf("ENV init state:\n");
			successorEnv.printInitialEnviromentState();
			System.out.println("solution:\n");
			successorEnv.printCurrentEnviromentState();
		}
		return minHCost;
	}

	public int solvePuzzleSimAnnealing(Enviroment env) {
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env.clone(); //start with current env
		Enviroment successorEnvContender= null;
		//perfect solution minHCost = 0
		int badnessOfMove;
		double probabilityOfAcceptingBadMoveBase=0.999;//less than 1
		double probabilityOfAcceptingBadMove;
		while(minHCost!=0 || successorEnv.totalCost()!=0){

			successorEnvContender = successorEnv.getSuccessorStateRandomly();
			int curHCost = successorEnvContender.totalCost();
			
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
			simPathCost++;
			probabilityOfAcceptingBadMoveBase-=0.005;//randomly chosen
			//reset minHCost if we don't have 8 queens on the board
			if(successorEnv.totalCost()!=0){
				minHCost = Integer.MAX_VALUE;
			}
	}

	if(DEBUG&&minHCost==0){
		System.out.printf("solved:\n");
		System.out.printf("ENV init state:\n");
		successorEnv.printInitialEnviromentState();
		System.out.println("solution:\n");
		successorEnv.printCurrentEnviromentState();
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