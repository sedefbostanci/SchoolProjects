import static java.util.Comparator.comparing;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CustomerDAOImplement implements CustomerDAO {
	
	int i,f,a;
	int k=0;
	
	String[] customerArray= new String[80];
	
	String line;
	
	String Adress= "";
	
	
	List<Customer> customers;
	
	StringBuilder sb = new StringBuilder();
    
    BufferedReader customerFile = new BufferedReader(new FileReader("customer.txt"));

	
	public CustomerDAOImplement() throws IOException {
		
		customers = new ArrayList<Customer>();
		
		while ((line = customerFile.readLine()) != null) { //Read customer file line by line and split it from space
	        customerArray[i]=line;
	  
	        String[] words= customerArray[i].split(" ");
	        
	        for(f=5;f<words.length;f++) { // Create address
	        	Adress=Adress+" "+words[f]+"";
	        	
	        }
	        Customer customer= new Customer(Integer.parseInt(words[0]),words[1], words[2], words[3], Adress); //Create customer object according to its information
	        customers.add(customer); //Add that customer to customers arraylist
	        Adress=" ";
		}
		
		
		customerFile.close();
	}
	
	
	@Override
	public List<Customer> getAll() {
		return customers;
	}

	@Override
	public void addCustomer(Customer customer) {
		
		customers.add(customer); //Add created customer to customers arraylist
		
		Collections.sort(customers, comparing(Customer::getCustomerId)); // Sort customer arraylist by customers Id number
		
		
	}

	@Override
	public void deleteCustomer(int Id) {
		
		for(i=0;i<customers.size();i++) {
			if(customers.get(i).getCustomerId()==Id) { // Remove customer from arraylist by its Id number
				customers.remove(i);
			}
		}
	}


	
	
	
	

}

	