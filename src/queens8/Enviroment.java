package queens8;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

//getting successor states methods doesn't alter the Environment's state
public class Enviroment {
	private int[][] enviromentState = new int[8][8];
	private int[][] initialState = new int[8][8];
	private Random rand;
	int numQueens=0;
	
	public Enviroment(){
		rand = new Random();
	}
	public Enviroment(int[][] init,int[][] env){
		initialState = init;
		enviromentState = Enviroment.deepCopyMatrix(env);
		rand = new Random();
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
		enviromentState = Enviroment.deepCopyMatrix(env);
		numQueens = num;
		rand = new Random();
	}
	public boolean setInitialState(String initialState){
		boolean bResult = false;
		int iIndex = 0;
		for (int i = 0 ; i < initialState.length() ; i++){
			if (Integer.parseInt(initialState.substring(iIndex, iIndex + 1)) > 0){
				this.initialState[Integer.parseInt(initialState.substring(iIndex, iIndex + 1)) - 1][i] = 1;
				numQueens++;
			}
			iIndex++;
		}
		this.enviromentState = Enviroment.deepCopyMatrix(this.initialState);
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
	public static int[][] deepCopyMatrix(int[][] input) {
	    if (input == null)
	        return null;
	    int[][] result = new int[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}
	public void printSuccessorStates(){
		int[][] successorEnvState = enviromentState;
		//don't have 8 queens on board
		if(numQueens<8){
			  for(int i = 0;i<8;i++){
    			  successorEnvState[i][numQueens] = 1;
    			  Enviroment env = new Enviroment(initialState,successorEnvState);
    			  successorEnvState[i][numQueens] = 0;
    			  env.printEnviromentState();
    			  System.out.println("");
    			  //successorEnvState[i][numQueens] = 0;
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
		    			  successorEnvState[(i+n)%8][j] = 1;
		    			  Enviroment env = new Enviroment(initialState,successorEnvState);
		    			  env.printEnviromentState();
		    			  System.out.println("");
		    			  successorEnvState[(i+n)%8][j] = 0;
		    		  }
		    		  successorEnvState[i][j] = 1;
		    	  }
		      }
		      //successorEnvState = enviromentState.clone();
		   }
		}
	}
	public List<Enviroment> getSuccessorStates(){
		List<Enviroment> successorStates = new ArrayList<Enviroment>();
		int[][] successorEnvState = enviromentState;
		//don't have 8 queens on board
		if(numQueens<8){
			  for(int i = 0;i<8;i++){
    			  successorEnvState[i][numQueens] = 1;
    			  Enviroment env = new Enviroment(initialState,successorEnvState);
    			  successorStates.add(env);
    			  successorEnvState[i][numQueens] = 0;
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
		    			  successorEnvState[(i+n)%8][j] = 1;
		    			  Enviroment env = new Enviroment(initialState,successorEnvState);
		    			  successorStates.add(env);
		    			  successorEnvState[(i+n)%8][j] = 0;
		    		  }
		    		  successorEnvState[i][j] = 1;
		    	  }
		      }
		      //successorEnvState = enviromentState.clone();
		   }
		}
		  return successorStates;
	}
	private int randInt(int min, int max){


		    int randomNum = rand.nextInt((max - min) + 1) + min;

		    return randomNum;
	}
	public Enviroment getSuccessorStateRandomly(){
		int[][] successorEnvState = enviromentState;
		//don't have 8 queens on board
		if(numQueens<8){
    			  successorEnvState[randInt(0,7)][numQueens] = 1;
    			  return new Enviroment(initialState,successorEnvState);

		}else{
			//is this a valid successor state. If queen is there it is not
			//if no queen move queen here
			int newColumnIndex=randInt(0,7),j=randInt(0,7);
			int oldColumnIndex;
			while(successorEnvState[newColumnIndex][j]==1){
				newColumnIndex=randInt(0,7);
				j=randInt(0,7);
		    		  
			}
			for(oldColumnIndex=0;oldColumnIndex<8; oldColumnIndex++){
				//found queen. Let's move it to generate successor state
				if(successorEnvState[oldColumnIndex][j]==1){
					successorEnvState[oldColumnIndex][j] = 0;
					successorEnvState[newColumnIndex][j] =  1;
					return new Enviroment(initialState,successorEnvState);
					
				}			    		  
			}
				
		   
		}
		return null;//error. Shouldn't get here ever!
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
	public int getNumQueens() {
		return numQueens;
	}

}
