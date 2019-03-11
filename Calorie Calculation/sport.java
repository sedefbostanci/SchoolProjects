
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class sport {
	
	//Reads sport.txt file and puts each line to an array
	public String[] arrayYapThree() throws IOException {
		StringBuilder sb = new StringBuilder();
        String 	line;
        BufferedReader sportFile = new BufferedReader(new FileReader("sport.txt"));
        String[] arrayname= new String[100];
        int i=0;
        
        while ((line = sportFile.readLine()) != null) {
	        arrayname[i]=line;
	        i++;
	    }
		
        return arrayname;
	}
	
	
}
