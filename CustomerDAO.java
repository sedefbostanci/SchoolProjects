import java.util.List;

public interface CustomerDAO {
	public List<Customer> getAll();
	public void addCustomer(Customer customer);
	public void deleteCustomer(int Id);
	

}
