import static java.util.Comparator.comparing;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class testTwo {
	
	public void leaderBoard(ArrayList<Person> person,Person kisi) throws IOException {
		
		int high;
		int low;
		int mine;
		
		Collections.sort(person);
		
		int number=Collections.binarySearch(person, kisi);
		if(person.size()==1) {
			System.out.printf("Your rank is %d/0, ",person.size()-number);
		}
		else {
			System.out.printf("Your rank is %d/%d, ",person.size()-number,person.size());
		}
		
		mine=person.get(number).getScore();
		
		
		if(number-1<0 && person.size()==2 || number-1<0 && person.size()>2){
			System.out.printf("your score is %d points lower than %s\n",person.get(number+1).getScore()-mine,person.get(number+1).getName());
		}
		    
		
		if(number==1 && person.size()==2 || person.size()-1==number) {
			System.out.printf("your score is %d points higher than %s\n",mine-person.get(number-1).getScore(),person.get(number-1).getName());
		}
		else if(number-1>=0 && number+1 < person.size()){
			System.out.printf("your score is %d points higher than %s and %d points lower than %s\n",mine-person.get(number-1).getScore(),person.get(number-1).getName(),person.get(number+1).getScore()-mine,person.get(number+1).getName());
		}
	    
		
		
	}
	
	public void printMap(String[][] map) {
		
		int i,j;
		
		for(i=0;i<10;i++) {
			for(j=0;j<10;j++) {
				System.out.printf("%s ",map[i][j]);
			}
			System.out.println("");
		}
	}
	
	public void commandTest(String[][] map) throws IOException {
		
		String line,line2;
		String[] commandArray=new String[15];
		int f,a,c=0;
		int i=0;
		int sayac=0;
		String[] maths=new String[] {"+","-","/","\\","|"};
		int row,column;
		int total=0;
		int point,score;
		String isim,name;
		

		BufferedReader command = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter("leaderboard.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
	    BufferedReader leaders = new BufferedReader(new FileReader("leaderboard.txt"));
		
		
		UpdateMap update=new UpdateMap();
		System.out.println("Game grid:\n");
		printMap(map);
		System.out.println();
		
		
		ArrayList<Person> personList = new ArrayList<Person>();
		
		while ((line2 = leaders.readLine()) != null) {
			
			
			String[] words2= line2.split(" ");
			
			if(words2.length>1) {
				name=words2[0];
				score=Integer.parseInt(words2[1]);
				personList.add(new Person(name,score));
			}
			
	    }
	    
		
		
		while ((line = command.readLine()) != null) {
	        
			System.out.printf("Select coordinate or enter E to end the game: %s\n",line);
		    System.out.println();
			
		    commandArray[i]=line;
			
			String[] words= commandArray[i].split(" ");
	        
	        if(words[0].equals("E")) {
	        	
	        	
	        	System.out.printf("Total score: %d points.\n",total);
	        	System.out.println();
	        	isim=command.readLine();
	        	System.out.printf("Enter name: %s\n",isim);
	        	out.print("\r\n");
	        	out.printf("%s %s\r\n", isim,total);
	        	
	    		personList.add(new Person(isim,total));
	        	leaderBoard(personList,new Person(isim,total));
	        	
	        	break;
	        		
	        }
	        
	        else {
	        	row=Integer.parseInt(words[0]);
	        	column=Integer.parseInt(words[1]);
	        	
	        	
	        	if(row<map.length && column < map[0].length) {
	        		if(map[row][column].equals("D")) {
		        		Diamond diamond=new Diamond();
		        		int check=diamond.match(row, column, map);
		        		update.make(check, row, column, map);
		        		printMap(map);
		        		System.out.println("\n");
		        		if(check==0) {
		        			int point1=0;
		        			System.out.printf("Score: %d points.\n",point1);
		        		}
		        		
		        		else {
		        			total+=90;
			        		int point1 = 90;
			        		System.out.printf("Score: %d points.\n",point1);
		        		}
		        		
		        	}
		        	else if(map[row][column].equals("S")) {
		        		Square square=new Square();
		        		int check2=square.match(row, column, map);
		        		update.make(check2, row, column, map);
		        		printMap(map);
		        		System.out.println("\n");
		        		if(check2==0) {
		        			int point1=0;
		        			System.out.printf("Score: %d points.\n",point1);
		        		}
		        		
		        		else {
		        			total+=45;
			        		int point1 = 45;
			        		System.out.printf("Score: %d points.\n",point1);
		        		}
		        		
		        	}
		        	else if(map[row][column].equals("T")) {
		        		Triangle triangle=new Triangle();
		        		int check3=triangle.match(row, column, map);
		        		update.make(check3, row, column, map);
		        		printMap(map);
		        		System.out.println("\n");
		        		if(check3==0) {
		        			int point1=0;
		        			System.out.printf("Score: %d points.\n",point1);
		        		}
		        		else{
		        			total+=45;
		        			int point1 = 45;
			        		System.out.printf("Score: %d points.\n",point1);
		        		}
		        		
		        	}
		        	else if(map[row][column].equals("W")) {
		        		WildCard wildcard=new WildCard();
		        		int check4=wildcard.match(row, column, map);
		        		update.make(check4, row, column, map);
		        		printMap(map);
		        		System.out.println("\n");
		        		
		        		if(check4==1 || check4==9 || check4==3 || check4==7) {
		        			point=70;
		        		}
		        		else if(check4==2 || check4==8 || check4==4 || check4==6) {
		        			point=40;
		        		}
		        		else if(check4%1000==0){
		        			point=60;
		        		}
		        		else if(100<check4 && check4<110) {
		        			point=30;
		        		}
		        		else if(150<check4 && check4<160) {
		        			point=35;
		        		}
		        		else if(200<check4 && check4<210) {
		        			point=40;
		        		}
		        		else {
		        			point=50;
		        		}
		        		
		        		if(check4==0) {
		        			point=0;
		        			System.out.printf("Score: %d points.\n",point);
		        		}
		        		else {
		        			total+=point;
			        		int point1 = point;
			        		System.out.printf("Score: %d points.\n",point1);
			        		point=0;
		        		}
		        		
		        		
		        	}
		        	else if(Arrays.asList(maths).contains(map[row][column])) {
		        		Math math=new Math();
		        		int check5=math.match(row, column, map, 1);
		        		update.make(check5, row, column, map);
		        		printMap(map);
		        		System.out.println("\n");
		        		if(check5==0) {
		        			int point1=0;
		        			System.out.printf("Score: %d points.\n",point1);
		        		}
		        		else {
		        			total+=60;
			        		int point1 = 60;
			        		System.out.printf("Score: %d points.\n",point1);
		        		}
		        		
		        		
		        	}
		        	else {
		        		System.out.println("Invalid coordinate. Try again.");
		        	}
		        	
	        	}
	        	else {
	        		System.out.println("Out of map.Try again.");
	        	}
	        	
	        	
	        }
	        
	       
	    
		}
			
	System.out.println();
	System.out.println("Good bye!"); 
	out.close();       
	        
	}
}
