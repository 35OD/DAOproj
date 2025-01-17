package daoInterface;

import model.Customer;

import java.util.List;

public interface CustomerDao {
  Customer getCustomerById(int id);
  void addCustomer(List<Customer> customerList);
boolean removeCustomerById(int id);


}
