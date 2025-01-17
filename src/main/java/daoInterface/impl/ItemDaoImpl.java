package daoInterface.impl;

import daoInterface.ItemDao;
import model.Customer;
import model.Item;
import utility.ConnectionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl extends ConnectionDAO implements ItemDao {


    @Override
    public List<Item> getAllItems() {
        try {
            Connection connection = ConnectionDAO.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM items");

            List<Item> itemList = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                itemList.add(item);
            }
            return itemList;
        }catch(SQLException e){
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    @Override
    public void addItem(List<Item> itemList) {
        try{
            Connection connection = ConnectionDAO.getConnection();
            for(Item item : itemList){
                String sqlQuery = "INSERT INTO item (id, name, price) VALUES (?, ?, ?, ?)";
                PreparedStatement prepStmt = connection.prepareStatement(sqlQuery);
                prepStmt.setInt(1, item.getId());
                prepStmt.setString(2, item.getName());
                prepStmt.setDouble(3, item.getPrice());
                int affectedRows = prepStmt.executeUpdate();
                System.out.println(affectedRows + "rows(s) affected.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean removeItemById(int id) {
        try {
            Connection connection = ConnectionDAO.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM item WHERE id = ?");

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
