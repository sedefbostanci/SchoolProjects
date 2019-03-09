
public class Customer {
	private int CustomerId;
	private String CustomerName;
	private String CustomerSurname;
	private String CustomerPhoneNumber;
	private String CustomerAdress;
	private int OrderId;
	private String Order;
	private int Customer;
	
	public Customer() {
		
	}
	
	public Customer(int CustomerId, String CustomerName, String CustomerSurname, String CustomerPhoneNumber, String CustomerAdress){
		this.CustomerName=CustomerName;
		this.CustomerSurname=CustomerSurname;
		this.CustomerId=CustomerId;
		this.CustomerPhoneNumber=CustomerPhoneNumber;
		this.CustomerAdress=CustomerAdress;
	}
	
	public Customer(int Customer,int OrderId) {
		
		this.Customer=Customer;
		this.OrderId=OrderId;
	
	}
	
	public int getCustomerId() {
		return CustomerId;
	}


	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}


	public String getCustomerName() {
		return CustomerName;
	}


	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}


	public String getCustomerSurname() {
		return CustomerSurname;
	}


	public void setCustomerSurname(String customerSurname) {
		CustomerSurname = customerSurname;
	}


	public String getCustomerPhoneNumber() {
		return CustomerPhoneNumber;
	}


	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		CustomerPhoneNumber = customerPhoneNumber;
	}


	public String getCustomerAdress() {
		return CustomerAdress;
	}


	public void setCustomerAdress(String customerAdress) {
		CustomerAdress = customerAdress;
	}

	
	public int getCustomer() {
		return Customer;
	}

	public void setCustomer(int customer) {
		Customer = customer;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public String getOrder() {
		return Order;
	}

	public void setOrder(String order) {
		Order = order;
	}

	
	
}