package utility;

import java.sql.*;

public class ConnectionDAO {
    public static Connection connection = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;

    public static Connection getConnection(){
        final String dburl = "jdbc:mysql://localhost:3306/gamestore";
        final String username = "root";
        final String password = "password";

        try{
            connection = DriverManager.getConnection(dburl, username, password);
            System.out.println("Connection to database successfully established.");
        }catch(SQLException e){
            System.out.println(e);
            e.printStackTrace();
        }

        return connection;
    }

    public void disconnect(){
        try{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch(Exception e){

        }
    }
}
