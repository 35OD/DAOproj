package daoInterface.impl;

import daoInterface.CustomerDao;
import model.Customer;
import utility.ConnectionDAO;

import java.sql.*;
import java.util.List;

public class CustomerDaoImpl extends ConnectionDAO implements CustomerDao {
  @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        try {
            Connection connection = ConnectionDAO.getConnection();
            String query = "SELECT * FROM customer WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setfName(rs.getString("fname"));
                customer.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return customer;
    }

    @Override
    public void addCustomer(List<Customer> customerList ) {
        try{
            Connection connection = ConnectionDAO.getConnection();
            for(Customer cust : customerList){
                String sqlQuery = "INSERT INTO customer (id, email, fName, lName) VALUES (?, ?, ?, ?)";
                PreparedStatement prepStmt = connection.prepareStatement(sqlQuery);
                prepStmt.setInt(1, cust.getId());
                prepStmt.setString(2, cust.getEmail());
                prepStmt.setString(3, cust.getfName());
                prepStmt.setString(4, cust.getlName());
                int affectedRows = prepStmt.executeUpdate();
                System.out.println(affectedRows + "rows(s) affected.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean removeCustomerById(int id) {
        try {
            Connection connection = ConnectionDAO.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM customer WHERE id = ?");

            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
