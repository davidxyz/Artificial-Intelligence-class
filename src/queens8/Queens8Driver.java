package queens8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Queens8Driver {

	public static void main(String[] args) {
		List<Enviroment> gameList = new ArrayList<Enviroment>();
		readGameFile(gameList);
		gameList.get(0).printEnviromentState();
	}
	public static boolean readGameFile(List<Enviroment> gameList) {
		BufferedReader reader = null;
		String input = null;
		Enviroment gameState = new Enviroment();

		try {
			reader = new BufferedReader(new FileReader("10eightqueens.txt"));
		} catch (FileNotFoundException e) {
			return false;
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

}
