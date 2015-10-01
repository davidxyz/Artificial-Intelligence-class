package puzzle8;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import puzzle8.Enviroment;

public class Agent {
	final static boolean DEBUG = true;
	

	public int SolvePuzzleHC(Enviroment env) {
		List<Enviroment> successorStates = env.getSuccessorStates();
		int minHCost = Integer.MAX_VALUE;
		int resetCount = 0;
		Enviroment successorEnv = env.clone(); //start with current env
		boolean foundSuccessor = true; //bool on whether the algoirthim can find�a successor�state with a lower cost
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		while((minHCost!=0 && foundSuccessor)||successorEnv.manhattanCost()!=0){
			foundSuccessor = false;
			
			for(int i=0; i<successorStates.size();i++){
				int curHCost = successorStates.get(i).totalCost();
				if(curHCost<minHCost){
					minHCost = curHCost;
					successorEnv = successorStates.get(i);
					foundSuccessor = true;
				}
			}
			if(successorEnv.hammingCost() >= 7){
				minHCost = Integer.MAX_VALUE;
			}else if(foundSuccessor == false){
				return minHCost;
			}
			if(DEBUG){
				System.out.println("========");
				successorEnv.printCurrentEnviromentState();
			}
			successorStates = successorEnv.getSuccessorStates();
		
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

	public int SolvePuzzleRR(Enviroment env) {
		int minHCost = Integer.MAX_VALUE;
		Enviroment successorEnv = env.clone(); //start with current env
		Enviroment successorEnvContender= null;
		
		
		//perfect solution minHCost = 0
		//if we don't find a successor with a lower cost abort
		while(minHCost!=0 || successorEnv.manhattanCost()!=0){

			successorEnvContender = successorEnv.getSuccessorStateRandomly();
			int curHCost = successorEnvContender.totalCost();
			if(curHCost<minHCost){
				minHCost = curHCost;
				successorEnv = successorEnvContender;
			}
			if(successorEnv.manhattanCost()!=0){
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

	public boolean SolvePuzzleSA(Enviroment cur) {
		boolean bResult = false;

		return bResult;
	}
}