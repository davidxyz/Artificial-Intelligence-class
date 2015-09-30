package puzzle8;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import puzzle8.Enviroment;

public class Agent {

	public int SolvePuzzleHC(Enviroment cur) {
		int cost = 0;
		List<Enviroment> successorStates = cur.getSuccessorStates();
		Enviroment lowest = cur.clone();
		int minSolutionCost = cur.totalCost();

		while (cur.manhattanCost() > 0||cur.hammingCost() > 0) {
			for (Enviroment successorEnvState : successorStates) {
				if (successorEnvState.totalCost() <= lowest.totalCost()) {
					minSolutionCost = successorEnvState.totalCost();
					if (lowest != successorEnvState) {
						lowest = successorEnvState;
					}
				}
			}
			if (lowest.hammingCost() < cur.hammingCost() || lowest.manhattanCost() < cur.manhattanCost()) {
				cur = lowest;
				cur.printCurrentEnviromentState();
				successorStates = cur.getSuccessorStates();
				cost++;
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