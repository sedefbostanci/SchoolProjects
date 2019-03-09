import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.Comparator.comparing;

public class OrderDAOImplement implements OrderDAO{
	
	int k=0,y=0,x=0,m=0;
	int sayac=0;
	int f,a;
	int musteri;
	int orderId;
	String order="";
	String line;
	String[] orderArray=new String[100];
	
	List<Customer> orders;
	
	BufferedReader orderFile = new BufferedReader(new FileReader("order.txt"));
	
	public OrderDAOImplement() throws IOException {
		
		orders = new ArrayList<Customer>();
		
		while ((line = orderFile.readLine()) != null) { //Read order file and store each line to an array
			orderArray[k]=line;
			k++;
		}
		
		for(f=0;f<orderArray.length;f++) { //Find out how many lines are stored
			if(orderArray[f]!= null) {
				sayac++;
			}
		}
		
		int[] IdList=new int[sayac]; //To hold order Id numbers
		String[] orderList=new String[sayac]; // Hold order information
		
		for(f=0;f<sayac;f++) {
			
			String[] words2=orderArray[f].split(" "); //Read each line of order file and split it from space
			if(words2[0].equals("Order:")) {
				
				orderId=Integer.parseInt(words2[1]);
				musteri=Integer.parseInt(words2[2]);
				IdList[m]=orderId; 
				m++;
				
				Customer customer= new Customer(musteri,orderId); //Create new customer object to hold its order information
				orders.add(customer); 
			}
			
			
		}
		
		for(f=1;f<sayac;f++) {
			
			String[] words3=orderArray[f].split(" ");
			
			boolean exact= words3[0].equals("Order:");
			
			
			if(!exact) { //Take type of pizza which is under the line starting with "Order:"
				order+=orderArray[f]+"\r\n"; // Create order information
		
			}
			if(exact || f==sayac-1) {
				orderList[x]=order;		//When other line executing which is start with "Order:" store order information to orderlist array
				
				x++;
				order="";
			}
			
			
			
		}
		
		for(a=0;a<orders.size();a++) {
			
			if(orders.get(a).getOrderId() == IdList[y]) { // Set customers order according to order Id numbers
				
				orders.get(a).setOrder(orderList[y]);

				y++;
			}
		}
	}
	
	
	
	@Override
	public List<Customer> getAllOrder() { 
		return orders;
	}
	
	@Override
	public void createOrder(int order, int Id) {
		// TODO Auto-generated method stub
		
		Customer customer=new Customer(Id,order); //Create new customer to hold its order information
		orders.add(customer); //Add that customer object to orders array list
		
		Collections.sort(orders, comparing(Customer::getOrderId)); // Sort arraylist by customer's order Id
	}


	@Override
	public void removeOrder(int orderId) {
		// TODO Auto-generated method stub
		for(a=0;a<orders.size();a++) {
			if(orders.get(a).getOrderId() == orderId) { //find index of mentioned order in orders arraylist
				orders.get(a).setOrder(null); // Remove its order and order Id
				orders.get(a).setOrderId(0);
				
			}
		}
	}
	
	public void removeCustomer(int customerId) {//find index of mentioned customer in orders arraylist
		// TODO Auto-generated method stub
		for(a=0;a<orders.size();a++) {
			if(orders.get(a).getCustomer() == customerId) {
				orders.remove(a); // Remove customer and its order from orders arraylist
				
			}
		}
	}
}
