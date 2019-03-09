import static java.util.Comparator.comparing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class inputExecution {
	
	public void siparisKayit(String[] siparisler,int length,List<Customer> list,int musteriID,int orderID,PrintWriter outputFile2) throws IOException {
		
		int i,k,a;
		String order="";
		String whichPizza;
		int cost=0;
		int costSatir=0;
		String payCheck="	";
		
		
		
		for(i=0;i<length;i++) { //Read siparisler array line by line and split it
			String[] words=siparisler[i].split(" ");
			
			
			if(words[0].equals("AddPizza")) {
				
				whichPizza=words[2]; //Take type of pizza
				outputFile2.printf("%s pizza added to order %d\r\n",whichPizza,orderID);
				
				for(k=2;k<words.length;k++) {//Creating order information and payCheck to write output file suitably
					order=order+""+words[k]+" ";
					payCheck=payCheck+""+words[k]+" ";
					
				}
				order=order+"\r\n";
				
				
				if(whichPizza.equals("AmericanPan")) {
					costSatir+=5;
				}
				else {//if it is Neapolitan
					costSatir+=10;
				}
				
				for(a=3;a<words.length;a++) {
					if(words[a].equals("Soudjouk")) {
						costSatir+=3;
					}
					if(words[a].equals("HotPepper")) {
						costSatir+=1;
					}
					if(words[a].equals("Onion")) {
						costSatir+=2;
					}
					if(words[a].equals("Salami")) {
						costSatir+=3;
					}
				}
				
				payCheck=payCheck+""+Integer.toString(costSatir)+"$";
				payCheck=payCheck+"\r\n";
				payCheck=payCheck+"\t";
				cost+=costSatir;//Add cost of one line to total cost
				costSatir=0;
				
				
				
			}
			if(words[0].equals("AddDrink")) {
				
				cost+=1;//Add 1 dollar to total cost
				outputFile2.printf("Drink added to order %d\r\n",orderID);
				order=order+"SoftDrink"+"\r\n";
				payCheck=payCheck+"SoftDrink"+" "+"1$"+"\r\n"+"\t";
				
			}
			if(words[0].equals("PayCheck")) {
				
				outputFile2.printf("PayCheck for order %d\r\n",orderID);
				outputFile2.printf("%s",payCheck);
				outputFile2.printf("Total: %d$\r\n",cost);
			}
			
		}
		
		for(a=0;a<list.size();a++) {//Set customer's order to string order which is created above accordin to customer Id
			if(list.get(a).getCustomer()==musteriID) {
				
				if(list.get(a).getOrder()==null) {
						list.get(a).setOrder(order);}
				}
		}
		
		
	
	}
	
	public void updateFile(List<Customer> list) throws IOException {
		
		
		
		FileWriter fileWriter = new FileWriter("customer.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	   
	    //update customer file by writing customers array list
	    for (Customer customer : list) {
	    	 printWriter.printf("%d %s %s %s Address:%s\r\n",customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerSurname(),customer.getCustomerPhoneNumber(),customer.getCustomerAdress());
	    	
	    }
  
	  
	    fileWriter.close();
	}
	
	public void updateOrder(List<Customer> orderList) throws IOException {
		
		
		
		FileWriter fileWriter = new FileWriter("order.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	   
	    //update order file by writing orders array list
	    for(Customer customer: orderList) {
			printWriter.printf("Order: %d %d\r\n",customer.getOrderId(),customer.getCustomer());
			printWriter.printf("%s",customer.getOrder());
		}
		
  
	  
	    fileWriter.close();
	}
	
	
	public void inputTest(String DosyaAdi) throws IOException {
		
		int i = 0,y=0;
		int f,a,x,t;
		int orderId,musteri;
		int sayac=0;
		String Adress=" ";
		String line;
		String name;
		String[] inputArray=new String[100];
		String[] siparisTutma=new String[10];
		
	    
	    BufferedReader inputFile = new BufferedReader(new FileReader(DosyaAdi));
	    
	    FileWriter fileWriter = new FileWriter("output.txt");
	    PrintWriter outputFile = new PrintWriter(fileWriter);
	   
		
		CustomerDAO customerDAO= new CustomerDAOImplement();
		OrderDAO orderDAO=new OrderDAOImplement();
		
		
		//Read input file line by line and store each line to an array
		while ((line = inputFile.readLine()) != null) {
	        
			inputArray[i]=line;
			
			i++;
		}
		
		//Find out how many lines are stored
		for(f=0;f<inputArray.length;f++) {
			
			
			if(inputArray[f]!= null) {
				sayac++;
			}
		}
		
		
		
		for(a=0;a<sayac;a++) {//Take each line and split from space and check first word
	        String[] words= inputArray[a].split(" ");
	        
	        
	        if(words[0].equals("AddCustomer")) {
	        	
	        	for(f=5;f<words.length;f++) {//Create address
		        	Adress=Adress+" "+words[f]+" ";
		        	
		        }
	        	        	
	        	Customer customer1= new Customer();//Create customer object according to words 
	    	    customer1.setCustomerId(Integer.parseInt(words[1]));
	    	    customer1.setCustomerName(words[2]);
	    	    customer1.setCustomerSurname(words[3]);
	    	    customer1.setCustomerPhoneNumber(words[4]);
	    	    customer1.setCustomerAdress(Adress);
	    	    Adress=" ";
	    	    customerDAO.addCustomer(customer1);// Add this customer object to customers arraylist
	    	    outputFile.printf("Customer %d %s added\r\n",Integer.parseInt(words[1]),words[2]);
	    	   
	    
	    	    
	        }
	        
	        if(words[0].equals("RemoveCustomer")) {
	        	
	        	for(t=0;t<customerDAO.getAll().size();t++) { //Find mentioned customer index in customers arraylist
	        		if(customerDAO.getAll().get(t).getCustomerId()==Integer.parseInt(words[1])) {
	        			name=customerDAO.getAll().get(t).getCustomerName(); // Take mentioned customers name
	        			outputFile.printf("Customer %d %s removed\r\n",Integer.parseInt(words[1]),name);
	        		}
	        	}
	        	
	        	
	        	customerDAO.deleteCustomer(Integer.parseInt(words[1])); // Remove that customer from customers arraylist
	        	orderDAO.removeCustomer(Integer.parseInt(words[1])); //Remove that customer from orders arraylist
	        	
	        	
	        }
	        
	        if(words[0].equals("List")) {
				
	        	Collections.sort(customerDAO.getAll(), comparing(Customer::getCustomerName)); //Sort customers arraylist by customer's name to write output file correctly
	        	
	        	outputFile.print("Customer List:\r\n");
	        	for (Customer customer : customerDAO.getAll()) {
			        
					
					outputFile.printf("%d %s %s %s Address:%s\r\n",customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerSurname(),customer.getCustomerPhoneNumber(),customer.getCustomerAdress());
			         
			    }
	        	Collections.sort(customerDAO.getAll(), comparing(Customer::getCustomerId));//Sort customers arraylist by customer's Id numbers
		   
			}
	        
	        if(words[0].equals("CreateOrder")){
	        	
	        	
	        	orderId=Integer.parseInt(words[1]); //take order Id number
	        	musteri=Integer.parseInt(words[2]); //take customer Id number
	        	orderDAO.createOrder(orderId, musteri); //Add that order to orders arraylist by customer Id and order Id
	        	
	        	outputFile.printf("Order %d created\r\n",orderId);
	        	
	        	for(x=a+1;x<sayac;x++) { //Check other lines which is in input file
	        		String[] words2= inputArray[x].split(" ");
	        		
	        		
	        		if(words2[1].equals(Integer.toString(orderId))) { //If mentioned order Id is same
	        			if(words2[0].equals("AddPizza") || words2[0].equals("AddDrink") || words2[0].equals("PayCheck")) { // If first word of other lines are same with that words
	        				
	        			
	        				siparisTutma[y]=inputArray[x].toString(); //Store that lines to string array
	        				y++;
	        				
	        			
	        			}
	        			
	        		}
	        		
	        	}
	        	
	        	//Send that array and orders arraylist to siparisKayit method 
	        	siparisKayit(siparisTutma,y,orderDAO.getAllOrder(),musteri,orderId,outputFile);
        		
        		y=0;
	        	
	        	
	        }
	        
	        if(words[0].equals("RemoveOrder")){
	        	
	        	orderId=Integer.parseInt(words[1]); //Take mentioned orderId
	        	orderDAO.removeOrder(orderId); // Remove that order from orders array list
	        } 
	        
	       
		}
		
		updateFile(customerDAO.getAll()); //Send customers array list to update customer file
		updateOrder(orderDAO.getAllOrder()); // Send orders array list to update order file
		
	
		outputFile.close();
	}
	
	
	public inputExecution(String fileName) throws IOException {
		
		inputTest(fileName); // Execute input txt file
	}
}