import java.util.List;

public interface OrderDAO {
	
	public List<Customer> getAllOrder();
	public void createOrder(int order, int Id);
	public void removeOrder(int orderId);
	public void removeCustomer(int customerId);
}
