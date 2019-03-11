
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class food {
	
	//Reads food.txt file and puts each line to an array
	public String[] arrayYapTwo() throws IOException {
		StringBuilder sb = new StringBuilder();
        String 	line;
        BufferedReader foodFile = new BufferedReader(new FileReader("food.txt"));
        String[] arrayname= new String[100];
        int i=0;
        
        while ((line = foodFile.readLine()) != null) {
	        arrayname[i]=line;
	        i++;
	    }
		
        return arrayname;
	}
	
	
}
