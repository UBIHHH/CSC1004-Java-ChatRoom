package client.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import client.Util.JDBCUtil;
public class UserDAO {
    public String searchUsername(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = JDBCUtil.getConnection();
            String sql = "select * from info where info.Username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("Username");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closed(connection, resultSet, preparedStatement);
        }
        return null;
    }
    public String searchPassword(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = JDBCUtil.getConnection();
            String sql = "select * from info where info.Username=?";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("Password");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closed(connection, resultSet, preparedStatement);
        }
        return null;
    }
    public void addUser(String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO info (Username, Password) VALUES(?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int num = preparedStatement.executeUpdate();
            if(num > 0){
                System.out.println("register successfully");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closed(connection, resultSet, preparedStatement );
        }
    }
}
