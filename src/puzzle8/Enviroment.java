package puzzle8;

import java.util.ArrayList;
import java.util.List;

public class Enviroment {

	public int[][] enviromentState = new int[3][3];
	public int[][] goalState = new int[3][3];
	private int[][] initialState = new int[3][3];
	private Tile blankSpaceLocation = new Tile();

	public Enviroment(int[][] enviromentState, int[][] goalState, int[][] initialState, Tile blankSpaceLocation) {
		super();
		this.enviromentState = enviromentState;
		this.goalState = goalState;
		this.initialState = initialState;
		this.blankSpaceLocation = blankSpaceLocation;
	}

	public Enviroment() {
		// TODO Auto-generated constructor stub
	}

	public Enviroment clone() {
		return new Enviroment(deepCopyIntMatrix(enviromentState), goalState,
			initialState, blankSpaceLocation.clone());
	}

	public static int[][] deepCopyIntMatrix(int[][] input) {
		if (input == null)
			return null;
		int[][] result = new int[input.length][];
		for (int r = 0; r < input.length; r++) {
			result[r] = input[r].clone();
		}
		return result;
	}

	public boolean setInitialState(String initialState) {
		boolean bResult = false;
		int iIndex = 0;

		if (initialState.matches("[0-9]{9}$") == true) {
			// System.out.println("The intitial state is eight digits! " +
			// initialState);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (Integer.parseInt(initialState.substring(iIndex, iIndex + 1)) == 0) {
						blankSpaceLocation.setCoords(i, j);
					}
					this.initialState[i][j] = Integer.parseInt(initialState.substring(iIndex, iIndex + 1));
					iIndex++;
				}
			}
			this.enviromentState = this.initialState;
			bResult = true;
		} else {
			System.out.println("The intitial state is NOT eight digits! " + initialState);
			bResult = false;
		}

		return bResult;

	}

	public boolean setGoalState(String goalState) {
		boolean bResult = false;
		int iIndex = 0;

		if (goalState.matches("[0-9]{9}$") == true) {
			// System.out.println("The goal state is eight digits! " +
			// goalState);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					this.goalState[i][j] = Integer.parseInt(goalState.substring(iIndex, iIndex + 1));
					iIndex++;
				}
			}
			bResult = true;
		} else {
			System.out.println("The goal state is NOT eight digits! " + goalState);
			bResult = false;
		}

		return bResult;

	}

	public int[][] getEnviromentState() {
		return enviromentState;
	}

	public void setEnviromentState(int[][] enviromentState) {
		this.enviromentState = enviromentState;
	}

	public int[][] getGoalState() {
		return goalState;
	}

	public void setGoalState(int[][] goalState) {
		this.goalState = goalState;
	}

	public int[][] getInitialState() {
		return initialState;
	}

	public void setInitialState(int[][] initialState) {
		this.initialState = initialState;
	}

	public Tile getBlankSpaceLocation() {
		return blankSpaceLocation;
	}

	public void setBlankSpaceLocation(Tile blankSpaceLocation) {
		this.blankSpaceLocation = blankSpaceLocation;
	}

	public void printCurrentEnviromentState() {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("%5d ", this.enviromentState[i][j]);
			}
			System.out.println();
		}
	}
	
	public void printInitialEnviromentState() {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("%5d ", this.initialState[i][j]);
			}
			System.out.println();
		}
	}

	public void printGoalEnviromentState() {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("%5d ", this.goalState[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * @param direction
	 *            1 up,2 down,3 left, 4 right
	 */
	public Enviroment move(int direction) {
		boolean b = false;
		Enviroment that = this.clone();
		switch (direction) {
		case 1:
			b = that.moveSpaceUp();
			break;
		case 2:
			b = that.moveSpaceDown();
			break;
		case 3:
			b = that.moveSpaceLeft();
			break;
		case 4:
			b = that.moveSpaceRight();
			break;
		}
		if (b = false) {
			return this;
		}
		return that;
	}

	public void printBlankSpaceLocation() {
		System.out.println("The blank space is " + this.blankSpaceLocation.vertical + " from the top " + ", "
				+ this.blankSpaceLocation.horizontal + " from the left");
	}

	public boolean moveSpaceUp() {
		boolean bResult = false;
		int iCurrentVal = -1;
		int iFutureVal = -1;

		if (this.blankSpaceLocation.vertical > 0) {
			iCurrentVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal];
			iFutureVal = this.enviromentState[this.blankSpaceLocation.vertical - 1][this.blankSpaceLocation.horizontal];
			this.enviromentState[this.blankSpaceLocation.vertical
					- 1][this.blankSpaceLocation.horizontal] = iCurrentVal;
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal] = iFutureVal;
			this.blankSpaceLocation.vertical = this.blankSpaceLocation.vertical - 1;
			bResult = true;

		}

		return bResult;

	}

	public boolean moveSpaceDown() {
		boolean bResult = false;
		int iCurrentVal = -1;
		int iFutureVal = -1;

		if (this.blankSpaceLocation.vertical < 2) {
			iCurrentVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal];
			iFutureVal = this.enviromentState[this.blankSpaceLocation.vertical + 1][this.blankSpaceLocation.horizontal];
			this.enviromentState[this.blankSpaceLocation.vertical
					+ 1][this.blankSpaceLocation.horizontal] = iCurrentVal;
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal] = iFutureVal;
			this.blankSpaceLocation.vertical = this.blankSpaceLocation.vertical + 1;
			bResult = true;

		}

		return bResult;

	}

	public boolean moveSpaceLeft() {
		boolean bResult = false;
		int iCurrentVal = -1;
		int iFutureVal = -1;

		if (this.blankSpaceLocation.horizontal > 0) {
			iCurrentVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal];
			iFutureVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal - 1];
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal
					- 1] = iCurrentVal;
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal] = iFutureVal;
			this.blankSpaceLocation.horizontal = this.blankSpaceLocation.horizontal - 1;
			bResult = true;

		}

		return bResult;

	}

	public boolean moveSpaceRight() {
		boolean bResult = false;
		int iCurrentVal = -1;
		int iFutureVal = -1;

		if (this.blankSpaceLocation.horizontal < 2) {
			iCurrentVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal];
			iFutureVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal + 1];
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal
					+ 1] = iCurrentVal;
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal] = iFutureVal;
			this.blankSpaceLocation.horizontal = this.blankSpaceLocation.horizontal + 1;
			bResult = true;

		}

		return bResult;

	}

	public int cost(int target) {
		int cost = -1;
		Tile targetCurrentLocation = new Tile();
		Tile targetGoalLocation = new Tile();

		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				if (goalState[j][k] == target) {
					targetGoalLocation.setCoords(j, k);
				}
			}
		}

		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				if (enviromentState[j][k] == target) {
					targetCurrentLocation.setCoords(j, k);
				}
			}
		}
		cost = 0;
		cost = Math.abs((targetCurrentLocation.horizontal - targetGoalLocation.horizontal))
				+ Math.abs((targetCurrentLocation.vertical - targetGoalLocation.vertical));
		return cost;
	}

	public int totalCost(){
		int cost = -1;

		cost = this.manhattanCost() + this.hammingCost();
		return cost;
	}
		
	public int manhattanCost() {
		int cost = -1;

		cost = 0;
		for (int i = 1; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (enviromentState[j][k] == i) {
						cost += cost(i);
					}
				}
			}
		}
		return cost;
	}
	
	public int hammingCost() {
		int misplaceTiles = -1;

		misplaceTiles = 0;
		for (int i = 1; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (enviromentState[j][k] == i) {
						if (cost(i) != 0){
							misplaceTiles++;
						}
					}
				}
			}
		}
		return misplaceTiles;
	}
	
	

	public List<Enviroment> getSuccessorStates() {
		List<Enviroment> successorStates = new ArrayList<Enviroment>();
		Enviroment successorEnvState = this.clone();

		for (int j = 1; j < 5; j++) {
			Enviroment env = new Enviroment();
			env = successorEnvState.clone().move(j);
			if (env.blankSpaceLocation.horizontal != successorEnvState.blankSpaceLocation.horizontal || env.blankSpaceLocation.vertical != successorEnvState.blankSpaceLocation.vertical){
				successorStates.add(env);
				// DEBUG
//				System.out.println("===== printing succssor " + successorStates.size());
//				env.printCurrentEnviromentState();
			}
		}
		return successorStates;
	}
}
