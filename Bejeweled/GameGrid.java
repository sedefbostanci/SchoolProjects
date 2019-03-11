import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameGrid {
	
	int i,f,a,d;
	String line;
	int row=0;
	String[] gameGridArray= new String[50];
	int column;
	
	public String[][] GameGridExe() throws IOException {
		BufferedReader gameGrid = new BufferedReader(new FileReader("gameGrid.txt"));
		
		while ((line = gameGrid.readLine()) != null) {
	        gameGridArray[i]=line;
	        i++;
	    }
		
		for(f=0;f<gameGridArray.length;f++) {
			
			if(gameGridArray[f]!= null) {
				row++;
			}
		}
		
		for(a=0;a<1;a++) {//Take each line and split from space
	        String[] words= gameGridArray[a].split(" ");
	        column=words.length;
	    }
		
		String[][] map=new String[row][column];
		
		for(a=0;a<row;a++) {//Take each line and split from space
	        String[] words= gameGridArray[a].split(" ");
	        
	        for(d=0;d<words.length;d++) {
	        	map[a][d]=words[d];
	        }
	    }
		
		
		return map;
		
		
	}
	
	
    
	
}
