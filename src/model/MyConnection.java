package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ningge on 15/12/29.
 */
public class MyConnection {
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/red_cross?useSSL=false&useUnicode=true&characterEncoding=UTF-8";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    public Connection connection = null;

    //构造函数
    public MyConnection(){
        this.connection = this.getConnection();
    }

    //获取数据库连接
    public static Connection getConnection(){
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        return null;
    }

    public boolean insert(Model model){
        return  model.insert();
    }

    //析构函数
    protected void destroy(){
        try{
            this.connection.close();
        }
        catch (SQLException sqlexception){
            sqlexception.printStackTrace();
        }

    }

    //获得所有省份信息
    public static Map<Integer, String> getProvinces(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM map where class_type = ?");
            statement.setString(1,"1");
            ResultSet resultSet = statement.executeQuery();
            Map<Integer, String> result = new HashMap<Integer, String >();
            if(resultSet.equals(null)){

            }
            else{
                while(resultSet.next()){
                    //Retrieve by column name
                    int id  = resultSet.getInt("class_id");
                    String name = resultSet.getString("class_name");
                    result.put(id, name);
                }
                connection.close();
                resultSet.close();
                return result;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new HashMap<>();
    }

    //获得省的所有城市
    public static ArrayList<City> getCity(String id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM map where class_parent_id = ?");
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<City> result = new ArrayList<>();
            if(resultSet.equals(null)){

            }
            else{
                while(resultSet.next()){
                    int c_id  = resultSet.getInt("class_id");
                    String name = resultSet.getString("class_name");
                    result.add(new City(c_id,name));
                }
                resultSet.close();
                connection.close();
                return result;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    //根据ID获得省份或城市名
    public static String getById(String id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM map where class_id = ?");
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            String name;
            if(resultSet.equals(null)){

            }
            else{
                while(resultSet.next()){
                    name = resultSet.getString("class_name");
                    return name;
                }
                resultSet.close();
                connection.close();

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new String();
    }
    //获得余额
    public static String getTotal(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM total where id = ?");
            statement.setString(1,"1");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<City> result = new ArrayList<>();
            if(resultSet.equals(null)){

            }
            else{
                String total = "0";
                while(resultSet.next()){
                    total = resultSet.getString("total");

                }
                resultSet.close();
                connection.close();
                return total;

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "0";

    }
}
