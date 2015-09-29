package puzzle8;

public class Enviroment {
	
	public int[][] enviromentState = new int[3][3];
	public int[][] goalState = new int[3][3];
	private int[][] initialState = new int[3][3];
	private Tile blankSpaceLocation = new Tile();
	
	
	public boolean setInitialState(String initialState){
		boolean bResult = false;
		int iIndex = 0;
		
		if(initialState.matches("[0-9]{9}$") == true){
			//System.out.println("The intitial state is eight digits! " + initialState);
			for (int i = 0 ; i < 3 ; i++){
				for (int j = 0 ; j < 3 ; j++){
					if (Integer.parseInt(initialState.substring(iIndex, iIndex + 1)) == 0){
						blankSpaceLocation.setCoords(i, j);
					}
					this.initialState[i][j] = Integer.parseInt(initialState.substring(iIndex, iIndex + 1));
					iIndex++;
				}
			}
			this.enviromentState = this.initialState;
			bResult = true;
		}else{
			System.out.println("The intitial state is NOT eight digits! " + initialState);
			bResult = false;
		}
		
		
		return bResult;
		
	}
	
	public boolean setGoalState(String goalState){
		boolean bResult = false;
		int iIndex = 0;
		
		if(goalState.matches("[0-9]{9}$") == true){
			//System.out.println("The goal state is eight digits! " + goalState);
			for (int i = 0 ; i < 3 ; i++){
				for (int j = 0 ; j < 3 ; j++){
					this.goalState[i][j] = Integer.parseInt(goalState.substring(iIndex, iIndex + 1));
					iIndex++;
				}
			}
			bResult = true;
		}else{
			System.out.println("The goal state is NOT eight digits! " + goalState);
			bResult = false;
		}
		
		
		return bResult;
		
	}
	
	public void printCurrentEnviromentState(){

		   for(int i = 0; i < 3; i++)
		   {
		      for(int j = 0; j < 3; j++)
		      {
		         System.out.printf("%5d ", this.enviromentState[i][j]);
		      }
		      System.out.println();
		   }
	}
	
	public void printGoalEnviromentState(){

		   for(int i = 0; i < 3; i++)
		   {
		      for(int j = 0; j < 3; j++)
		      {
		         System.out.printf("%5d ", this.goalState[i][j]);
		      }
		      System.out.println();
		   }
	}
	
	public void printBlankSpaceLocation(){
		System.out.println("The blank space is " + this.blankSpaceLocation.vertical + " from the top " + ", " + this.blankSpaceLocation.horizontal + " from the left");
	}
	
	public boolean moveSpaceUp(){
		boolean bResult = false;
		int iCurrentVal = -1;
		int iFutureVal = -1;
		
		if (this.blankSpaceLocation.vertical > 0){
			iCurrentVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal];
			iFutureVal = this.enviromentState[this.blankSpaceLocation.vertical - 1][this.blankSpaceLocation.horizontal];
			this.enviromentState[this.blankSpaceLocation.vertical - 1][this.blankSpaceLocation.horizontal] = iCurrentVal;
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal] = iFutureVal;
			this.blankSpaceLocation.vertical = this.blankSpaceLocation.vertical - 1;
			bResult = true;
			
		}else{
			System.out.println("Cannot move above top row!");
		}
		
		return bResult;
		
	}
	public boolean moveSpaceDown(){
		boolean bResult = false;
		int iCurrentVal = -1;
		int iFutureVal = -1;
		
		if (this.blankSpaceLocation.vertical < 2){
			iCurrentVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal];
			iFutureVal = this.enviromentState[this.blankSpaceLocation.vertical + 1][this.blankSpaceLocation.horizontal];
			this.enviromentState[this.blankSpaceLocation.vertical + 1][this.blankSpaceLocation.horizontal] = iCurrentVal;
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal] = iFutureVal;
			this.blankSpaceLocation.vertical = this.blankSpaceLocation.vertical + 1;
			bResult = true;
			
		}else{
			System.out.println("Cannot move below bottom row!");
		}
		
		return bResult;
		
	}
	public boolean moveSpaceLeft(){
		boolean bResult = false;
		int iCurrentVal = -1;
		int iFutureVal = -1;
		
		if (this.blankSpaceLocation.horizontal > 0){
			iCurrentVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal];
			iFutureVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal - 1];
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal - 1] = iCurrentVal;
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal] = iFutureVal;
			this.blankSpaceLocation.horizontal = this.blankSpaceLocation.horizontal - 1;
			bResult = true;
			
		}else{
			System.out.println("Cannot move below bottom row!");
		}
		
		return bResult;
		
	}
	public boolean moveSpaceRight(){
		boolean bResult = false;
		int iCurrentVal = -1;
		int iFutureVal = -1;
		
		if (this.blankSpaceLocation.horizontal < 2){
			iCurrentVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal];
			iFutureVal = this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal + 1];
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal + 1] = iCurrentVal;
			this.enviromentState[this.blankSpaceLocation.vertical][this.blankSpaceLocation.horizontal] = iFutureVal;
			this.blankSpaceLocation.horizontal = this.blankSpaceLocation.horizontal + 1;
			bResult = true;
			
		}else{
			System.out.println("Cannot move beyond right row!");
		}
		
		return bResult;
		
	}
}
