package puzzle8;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import puzzle8.Enviroment;

public class Agent {

	public int SolvePuzzleHC(Enviroment cur) {
		int cost = 0;
		List<Enviroment> successorStates = cur.getSuccessorStates();
		List<Enviroment> successorStatesChildren = null;
		Enviroment lowest = cur.clone();

		while (cur.totalCost() > 0) {
			for (Enviroment successorEnvState : successorStates) {
				if (successorEnvState.totalCost() < lowest.totalCost()){
					lowest = successorEnvState;
				}
			}
			if (lowest.totalCost() < cur.totalCost()){
				System.out.println("moving blank space");
				cur = lowest;
				cur.printCurrentEnviromentState();
				System.out.println("cur Manhattan Distance: " + cur.manhattanCost());
				System.out.println("cur Hamming Distance: " + cur.hammingCost());
				successorStates = cur.getSuccessorStates();
			}
		}
		return cost;
	}

	public boolean SolvePuzzleRR(Enviroment cur) {
		boolean bResult = false;
		Random r = new Random();

		while (cur.manhattanCost() > 0) {
			cur.getSuccessorStates();
		}
		return bResult;
	}

	public boolean SolvePuzzleSA(Enviroment cur) {
		boolean bResult = false;

		return bResult;
	}
}