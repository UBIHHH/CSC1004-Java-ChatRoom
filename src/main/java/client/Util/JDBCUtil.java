package client.Util;

import javax.xml.catalog.Catalog;
import java.sql.*;

import java.util.Collection;

public class JDBCUtil {
    private static Statement statement;
    private  static ResultSet resultSet;
    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String url = "jdbc:mysql://localhost:3306";
            String user = "root";
            String password = "Lcj18085576690";
            connection = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    public static void closed(Connection connection, Statement statement){
        try{
            if(statement != null){
                statement.close();
                statement = null;
            }
            if(connection != null){
                connection.close();
                connection = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void closed(Connection connection, ResultSet resultSet, Statement statement){
        try{
            resultSet.close();
            resultSet = null;
        }catch (SQLException e){
            e.printStackTrace();
        }
        closed(connection, statement);
    }
}
