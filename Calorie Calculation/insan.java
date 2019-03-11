
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class insan {
	
	//Reads people.txt file and puts each line to an array
	public String[] arrayYap() throws IOException {
		StringBuilder sb = new StringBuilder();
        String 	line;
        BufferedReader peopleFile = new BufferedReader(new FileReader("people.txt"));
        String[] arrayname= new String[50];
        int i=0;
        
        while ((line = peopleFile.readLine()) != null) {
	        arrayname[i]=line;
	        i++;
	    }
		
        return arrayname;
	}
	
	
}

