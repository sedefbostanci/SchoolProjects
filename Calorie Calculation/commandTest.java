
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class commandTest {
	
	public void printResult(String isim2, int age2, int dailyNeed2, int caloriesTaken2, int caloriesBurned2, PrintWriter outFileTwo) {
		
		//Prints information of specific person
		
		int result;
		
		result=(caloriesTaken2-caloriesBurned2)-dailyNeed2;
		
		if(result<0) {
			outFileTwo.printf("%s	%d	%dkcal	%dkcal	%dkcal	%dkcal",isim2,age2,dailyNeed2,caloriesTaken2,caloriesBurned2,result);
			outFileTwo.print("\n");
		}
		else {
			outFileTwo.printf("%s	%d	%dkcal	%dkcal	%dkcal	+%dkcal",isim2,age2,dailyNeed2,caloriesTaken2,caloriesBurned2,result);
			outFileTwo.print("\n");
		}
		
		
	}
	
	public void printAll(String isim3, int age3, int dailyNeed3, int caloriesTaken3, int caloriesBurned3, PrintWriter outFileThree) {
		
		//Prints informations of all persons
		
		int result3;
		
		result3=(caloriesTaken3-caloriesBurned3)-dailyNeed3;
		
		if(result3<0) {
			outFileThree.printf("%s	%d	%dkcal	%dkcal	%dkcal	%dkcal",isim3,age3,dailyNeed3,caloriesTaken3,caloriesBurned3,result3);
			outFileThree.print("\n");
		}
		else {
			outFileThree.printf("%s	%d	%dkcal	%dkcal	%dkcal	+%dkcal",isim3,age3,dailyNeed3,caloriesTaken3,caloriesBurned3,result3);
			outFileThree.print("\n");
		}
		
		
	}
	
	public int dailyCalorieNeeds(String kisi,String[] arr,int counter) {
		
		//Calculates daily calorie need 
		
		int a;
		String personGender;
		int personHeight;
		int personWeight;
		int dateOfBirth;
		int dailyCalorie=0;
		
		for(a=0;a<counter;a++) {
			String[] insanArray= arr[a].split("\t");
			if(insanArray[0].equals(kisi)) {
				personGender=insanArray[2];
				personWeight=Integer.parseInt(insanArray[3]);
				personHeight=Integer.parseInt(insanArray[4]);
				dateOfBirth=Integer.parseInt(insanArray[5]);
				if(personGender.equals("male")) {
					dailyCalorie+=(int)Math.round(66 + (13.75 * personWeight) + (5 * personHeight) - (6.8 *(2018-dateOfBirth)));
				}
				else {
					dailyCalorie+=(int)Math.round(665 + (9.6 * personWeight) + (1.7 * personHeight) - (4.7 *(2018-dateOfBirth)));
				}
			}
		}
		
		return dailyCalorie;
	}
	
	
	public void commandExecution(String[] array1, String[] array2, String[] array3, String fileName2, PrintWriter outFile2) throws IOException{
		StringBuilder sb = new StringBuilder();
        String 	line;
        
        BufferedReader commandFile = new BufferedReader(new FileReader(fileName2));

        
        String[] commandArray= new String[50];
        int i=0;
        int k,a,x,y,p,j;
        String nameOfFood;
        String nameOfSport;
        String personID;
        String isim;
        int age;
        String isim2;
        int age2;
        int calorieBurned;
        int calorieCount;
        int caloriesTaken=0;
        int caloriesBurned=0;
        int caloriesTaken2=0;
        int caloriesBurned2=0;
        int dailyNeed;
        int dailyNeed2;
        int sayac=0;
        int sayac1=0;
        int sayac2=0;
        int sayac3=0;
        int sayac4=0;
        int sayac5=0;
        int sayac6=0;
        int sayac7=0;
        int f=0;
        
        int[][] tutucuFood = new int[50][2];
        int[][] tutucuSport = new int[50][2];
        int[] isimTutucu= new int[50];
        
        //Checks how many line in arrays
        for(k=0;k<array2.length;k++) {
        	if(array2[k] != null) {
        		sayac2++;
        	}
        }
        
        for(k=0;k<array1.length;k++) {
        	if(array1[k] != null) {
        		sayac1++;
        	}
        }
        
        
        for(k=0;k<array3.length;k++) {
        	if(array3[k] != null) {
        		sayac3++;
        	}
        }
        
        //Checks how many line in command file
        while ((line = commandFile.readLine()) != null) {
	        commandArray[i]=line;
	        sayac7++;}
        
        commandFile.close();
        
        //Opens command file again with another name
        BufferedReader commandFile2 = new BufferedReader(new FileReader(fileName2));
        
	        
        
        while ((line = commandFile2.readLine()) != null) {
	        commandArray[i]=line;
	  
	        String[] words= commandArray[i].split("\t"); //Takes first line and splits it from tab and put each word to an array
	        if(words.length>1) { //if the line does not consist of a single word 
	        	
	        	//Checks if the second word of line is about foods or sports
	        	if(Integer.parseInt(words[1]) < 2000) { //if it is food
	        		for(k=0 ; k<sayac2 ; k++) {
	        			String[] foodArray= array2[k].split("\t");//Takes elements of food array one by one and splits it from tab and puts each word to an another array 
	        			if(Integer.parseInt(foodArray[0])==Integer.parseInt(words[1])) {//Checks foodID
	        				
	        				nameOfFood=foodArray[1];
	        				calorieCount=Integer.parseInt(foodArray[2]);
	        				tutucuFood[sayac4][0]=Integer.parseInt(words[0]);
	        				tutucuFood[sayac4][1]+=calorieCount*Integer.parseInt(words[2]);
	        				outFile2.printf("%s	has	taken	%dkcal	from	%s",words[0],calorieCount*Integer.parseInt(words[2]),nameOfFood);
	        				outFile2.print("\n");
	        				
	        				sayac4++;
	        			}
	        		}
	        		
	        	
	        	}
	        	else { // if it is sport
	        		
	        		for(k=0 ; k<sayac3 ; k++) {
	        			String[] sportArray= array3[k].split("\t");//Takes elements of sport array one by one and splits it from tab and puts each word to an another array
	        			
	        			if(sportArray[0].equals(words[1])) {		// Checks sport ID
	        				
	        				nameOfSport=sportArray[1];
	        				calorieBurned=Integer.parseInt(sportArray[2]);
	        				
	        				tutucuSport[sayac5][0]=Integer.parseInt(words[0]);
	        				tutucuSport[sayac5][1]+=(calorieBurned*Integer.parseInt(words[2]))/60;
	        				
	        				outFile2.printf("%s	has	burned	%dkcal	thanks	to	%s",words[0],(calorieBurned*Integer.parseInt(words[2]))/60,nameOfSport);
	        				outFile2.print("\n");
	        				
	        				sayac5++;
	        			}
	        		}
	        	}
	        }
	        else { // if line has a single word
	        	if(words[0].equals("printList")) { 
	        		
	        		for(x=0;x<sayac4;x++) { // Takes ID of persons who are in command file and puts that IDs to an array
	        			isimTutucu[f]=tutucuFood[x][0];
	        			f++;
	        		}
	        		
	        		//Gets rid of from double ID s
	        		for(k=0;k<isimTutucu.length;k++) {
	                	if(isimTutucu[k] != 0) {
	                		sayac6++;
	                	}
	                }
	        		
	        		int[] newList= new int[sayac6];
	        		
	        		for(k=0;k<sayac6;k++) {
	                	newList[k]=isimTutucu[k];
	                }
	        		

	                for (i = 0; i < sayac6; i++) {
	                    for ( j = i + 1; j < sayac6; j++) {
	                        if (newList[i] == newList[j]) {                  
	                            int shiftLeft = j;

	                            for(k = j + 1; k < sayac6; k++, shiftLeft++) {
	                                newList[shiftLeft] = newList[k];
	                            }

	                            sayac6--;
	                            j--;
	                        }
	                    }
	                }

	                int[] sonList = new int[sayac6];

	                for (i = 0; i < sayac6; i++) {
	                    sonList[i] = newList[i];
	                }

	                for(k=0;k<sonList.length;k++) { //Finds names,ages,daily calorie needs of corresponding persons and send that informations to printAll method as parameters
	                	dailyNeed2 = dailyCalorieNeeds(Integer.toString(sonList[k]), array1, sayac1);
		        		for(a=0;a<sayac1;a++) {
		        			String[] insanArray3= array1[a].split("\t");
		        			if(insanArray3[0].equals(Integer.toString(sonList[k]))) {
		        				isim2=insanArray3[1];
		        				age2=2018-(Integer.parseInt(insanArray3[5]));
		        				for(x=0;x<sayac4;x++) {
		        					if(tutucuFood[x][0]==sonList[k]) {
		        						caloriesTaken2+=tutucuFood[x][1];
		        					}
		        				}
		        				
		        				for(y=0;y<sayac5;y++) {
		        					
		        					if(tutucuSport[y][0]==sonList[k]) {
		        						
		        						caloriesBurned2+=tutucuSport[y][1];
		        					}
		        				}
		        				printAll(isim2,age2,dailyNeed2,caloriesTaken2,caloriesBurned2,outFile2);
		        				caloriesTaken2=0;
		        				caloriesBurned2=0;
		        			}
		        			
		        		}
	                }
	        		
	                
	        		
	        	}
	        	
	        	
	        	else {// if first word of command line is print takes ID of person and finds name,age,daily calorie need of corresponding person and send that informations to printResult method as parameters
	        		personID=words[0].substring(6,11);
	        		dailyNeed = dailyCalorieNeeds(personID, array1, sayac1);
	        		for(a=0;a<sayac1;a++) {
	        			String[] insanArray2= array1[a].split("\t");
	        			if(insanArray2[0].equals(personID)) {
	        				isim=insanArray2[1];
	        				age=2018-(Integer.parseInt(insanArray2[5]));
	        				for(x=0;x<sayac4;x++) {
	        					
	        					if(tutucuFood[x][0]==Integer.parseInt(personID)) {
	        						caloriesTaken+=tutucuFood[x][1];
	        					}
	        				}
	        				
	        				for(y=0;y<sayac5;y++) {
	        					
		        				
	        					if(tutucuSport[y][0]==Integer.parseInt(personID)) {
	        						
	        						caloriesBurned+=tutucuSport[y][1];
	        						
	        					}
	        				}
	        				printResult(isim,age,dailyNeed,caloriesTaken,caloriesBurned,outFile2);
	        				caloriesTaken=0;
	        				caloriesBurned=0;
	        			}
	        			
	        		}
	        	}
	        	
	        }
	        sayac++;
	        if(sayac != sayac7) {
	        	outFile2.print("***************");
				outFile2.print("\n");
	        }
	        
        }
        
		
        
	}
	
	
	public commandTest(String[] arr1, String[] arr2, String[] arr3,String fileName, PrintWriter outFile) throws IOException {
		
		// Constructor of commandTest class
		// Runs commandExecution method to excure command file
		commandExecution(arr1, arr2, arr3, fileName, outFile);
	}
}
