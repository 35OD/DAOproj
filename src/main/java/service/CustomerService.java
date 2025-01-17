package service;

import daoInterface.CustomerDao;
import daoInterface.impl.CustomerDaoImpl;
import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
private final CustomerDao customerDao;

    public CustomerService() {
        this.customerDao = new CustomerDaoImpl();
    }

    public Customer getCustomerById(int id) {
        System.out.println("Getting customer by id");
        return customerDao.getCustomerById(id);
    }

public void addCustomer(){
        Scanner sc = new Scanner(System.in);
ArrayList<Customer> customerList = new ArrayList<>();
        System.out.println("Enter the number of customers you want to add: ");
        int numOfCustomers =  Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numOfCustomers; i++) {
            Customer customer = new Customer();
            System.out.println("Enter the id for customer " + (i + 1) + ": ");
            customer.setId(Integer.parseInt(sc.nextLine()));
            System.out.println("Enter the email for customer " + (i + 1) + ": ");
            customer.setEmail(sc.nextLine());
            System.out.println("Enter the first name for customer " + (i + 1) + ": ");
            customer.setfName(sc.nextLine());
            System.out.println("Enter the last name for customer " + (i + 1) + ": ");
            customer.setlName(sc.nextLine());

            customerList.add(customer);
        }
        sc.close();
    customerDao.addCustomer(customerList);
    System.out.println("Customers added successfully");
}

    public void removeCustomerById(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the customer id of the customer you want to delete: ");
        int custId = Integer.parseInt(sc.nextLine());
        customerDao.removeCustomerById(custId);
        System.out.println("Customer deleted successfully");

    }
}
