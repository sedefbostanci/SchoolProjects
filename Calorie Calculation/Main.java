
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
        
        //Defines array to hold lines
		String[] personarr= new String[50];
        String[] foodarr= new String[100];
        String[] sportarr= new String[100];
        String dosyaName;
	      
        //Opens out file
        FileWriter fileWriter = new FileWriter("monitoring.txt");
        PrintWriter out = new PrintWriter(fileWriter);
        
        //Creates objects to each class to run methods
        insan person= new insan();
        personarr=person.arrayYap();
        food yemek= new food();
        foodarr=yemek.arrayYapTwo();
        sport spor= new sport();
        sportarr=spor.arrayYapThree();
        
        //Takes command file name
	    dosyaName=args[0];
        
	    //Sends arrays and input and output file names as parameter to commandTest class
        commandTest sedef= new commandTest(personarr,foodarr,sportarr,dosyaName,out);
	    
	    out.close();
    } 
	
	    
    
}   

