package queens8;
import java.util.ArrayList;
import java.util.List;

public class Enviroment {
	private int[][] enviromentState = new int[8][8];
	private int[][] initialState = new int[8][8];
	int numQueens;
	
	public Enviroment(){
		
	}
	public Enviroment(int[][] init,int[][] env){
		initialState = init;
		enviromentState = env;
		for(int i = 0; i < 8; i++)
		   {
		      for(int j = 0; j < 8; j++)
		      {
		        if(enviromentState[i][j]==1){
		        	numQueens++;
		        }
		      }
		   }
	}
	public Enviroment(int[][] init,int[][] env,int num){
		initialState = init;
		enviromentState = env;
		numQueens = num;
	}
	public boolean setInitialState(String initialState){
		boolean bResult = false;
		int iIndex = 0;
		int num=0;
		for (int i = 0 ; i < initialState.length() ; i++){
			if (Integer.parseInt(initialState.substring(iIndex, iIndex + 1)) > 0){
				this.initialState[Integer.parseInt(initialState.substring(iIndex, iIndex + 1)) - 1][i] = 1;
				num++;
			}
			iIndex++;
		}
		numQueens = num;
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
	public void printInitialState(){

		   for(int i = 0; i < 8; i++)
		   {
		      for(int j = 0; j < 8; j++)
		      {
		         System.out.printf("%5d ", this.initialState[i][j]);
		      }
		      System.out.println();
		   }
	}
	public List<Enviroment> getSuccessorStates(){
		List<Enviroment> successorStates = new ArrayList<Enviroment>();
		int[][] successorEnvState = enviromentState.clone();
		//don't have 8 queens on board
		if(numQueens<8){
			  for(int j = 0;j<8;j++){
    			  successorEnvState[numQueens][j] = 1;
    			  Enviroment env = new Enviroment(initialState,successorEnvState,numQueens+1);
    			  successorStates.add(env);
    			  successorEnvState[numQueens][j] = 0;
    		  }

		}else{
		  for(int i = 0; i < 8; i++)
		   {
			  
		      for(int j = 0; j < 8; j++)
		      {
		    	  //if we find a queen generate 7 successor states of the environment by moving the queen on that column
		    	  if(enviromentState[i][j] == 1){
		    		  successorEnvState[i][j] = 0;
		    		  for(int n=1;n<8;n++){
		    			  //set to 1 then 0 so as to restart the state for the successor
		    			  successorEnvState[(i+n)%7][j] = 1;
		    			  Enviroment env = new Enviroment(initialState,successorEnvState);
		    			  successorStates.add(env);
		    			  successorEnvState[(i+n)%7][j] = 0;
		    		  }
		    		  successorEnvState[i][j] = 1;
		    	  }
		      }
		      //successorEnvState = enviromentState.clone();
		   }
		}
		  return successorStates;
	}
	public int[][] getEnviromentState() {
		return enviromentState;
	}

	public int[][] getInitialState() {
		return initialState;
	}

	public void setEnviromentState(int[][] enviromentState) {
		this.enviromentState = enviromentState;
	}

	public void setInitialState(int[][] initialState) {
		this.initialState = initialState;
	}

}
