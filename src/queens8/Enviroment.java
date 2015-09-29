package queens8;

public class Enviroment {
	private int[][] enviromentState = new int[8][8];
	private int[][] initialState = new int[8][8];
	
	public boolean setInitialState(String initialState){
		boolean bResult = false;
		int iIndex = 0;
		
		for (int i = 0 ; i < initialState.length() ; i++){
			if (Integer.parseInt(initialState.substring(iIndex, iIndex + 1)) > 0){
				this.initialState[Integer.parseInt(initialState.substring(iIndex, iIndex + 1)) - 1][i] = 1;
			}
			iIndex++;
		}
		this.enviromentState = this.initialState;
		bResult = true;
		
		return bResult;
		
	}
	
	public void printEnviromentState(){

		   for(int i = 0; i < 8; i++)
		   {
		      for(int j = 0; j < 8; j++)
		      {
		         System.out.printf("%5d ", this.enviromentState[i][j]);
		      }
		      System.out.println();
		   }
	}

}
