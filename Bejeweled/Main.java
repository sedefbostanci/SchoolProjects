import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		
		GameGrid sedef=new GameGrid();
		String[][] game = sedef.GameGridExe();
		
		testTwo test2=new testTwo();
		test2.commandTest(game);
				
		
	}
}
