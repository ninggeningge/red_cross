package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.CommonFunctions;

/**
 * Created by ningge on 15/12/29.
 */
public class User extends Model {
    private int id = 0;//默认的id代表还没有分配id
    private String username ;
    private String password;
    private boolean is_forbid;
    private String addon;

    //默认构造函数
    public User(){
       this.connection = new MyConnection().getConnection();
    }

    public User(String name, String password, boolean is_forbid,String add_on){
        this.username = name;
        this.password = password;
        this.is_forbid = is_forbid;
        this.connection = new MyConnection().getConnection();
        this.addon = add_on;
    }

    //getters and setters
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
    public boolean isIs_forbid(){
        return this.is_forbid;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setIs_forbid(boolean is_forbid){
        this.is_forbid = is_forbid;
    }

    public String getAddon(){
        return this.addon;
    }

    public boolean insert() {

        if(!isValid()){
            return false;
        }
        try{
            int forbid = this.isIs_forbid() ? 1 : 0;
            Statement stmt = this.connection.createStatement();
            String sql = "insert into user(`username`, `password`, `is_forbid`, `addon`) values('" +
                    this.getUsername() + "','" + CommonFunctions.md5(this.getPassword()) +
                    "'," + forbid +",'" + this.addon + "')";

            System.out.print(sql);

            int result = stmt.executeUpdate(sql);


            if(result == 0){

                return false;

            }
            else{
                this.id = result;
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return true;
    }

    public boolean delete(){
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = MyConnection.getConnection();

                Statement statement = connection.createStatement();
                String sql = "DELETE  FROM user where id = " + this.getId() + "";
                int count = statement.executeUpdate(sql);
                if(count == 0){
                   return false;
                }
                else{
                  return true;
                }


            }
            catch (Exception e){
                e.printStackTrace();
            }
            return false;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  true;
    }

    public static User findByName(String username ){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM user where username = ?");
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.equals(null)){
                System.out.print("233");
            }
            else{
                while(resultSet.next()){
                    //Retrieve by column name
                    int id  = resultSet.getInt("id");
                    String name = resultSet.getString("username");
                    String pass = resultSet.getString("password");
                    Boolean forbid = Integer.parseInt(resultSet.getString("is_forbid")) == 0 ? false : true;
                    String add_on = resultSet.getString("addon");
                    User user =  new User(name,pass,forbid, add_on);
                    user.setId(id);
                    return user;
                }
                resultSet.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new User();
    }

    public static User findById(String idString){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM user where id = ?");
            statement.setString(1,idString);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.equals(null)){

            }
            else{
                while(resultSet.next()){
                    //Retrieve by column name
                    int id  = resultSet.getInt("id");
                    String name = resultSet.getString("username");
                    String pass = resultSet.getString("password");
                    Boolean forbid = Integer.parseInt(resultSet.getString("is_forbid")) == 0 ? false : true;
                    String add_on = resultSet.getString("addon");
                    User user =  new User(name,pass,forbid, add_on);
                    user.setId(id);
                    return user;
                }
                resultSet.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new User();
    }

    public static ArrayList<User> getAll(){
        ArrayList<User> users = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM user where username != 'admin'";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.equals(null)){
            }
            else{
                while(resultSet.next()){
                    int id  = resultSet.getInt("id");
                    String name = resultSet.getString("username");
                    String pass = resultSet.getString("password");
                    Boolean forbid = Integer.parseInt(resultSet.getString("is_forbid")) == 0 ? false : true;
                    String add_on = resultSet.getString("addon");
                    User user =  new User(name,pass,forbid,add_on);
                    user.setId(id);
                    users.add(user);
                }
                resultSet.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }

    public boolean update(){
        if(!isValid()){
            return false;
        }
        try{
            int forbid = this.isIs_forbid() ? 1 : 0;
            Statement stmt = this.connection.createStatement();
            String sql = "update user set username='" + this.getUsername() + "', password='" +
                    CommonFunctions.md5(this.getPassword()) +"', is_forbid='" +
                    this.isIs_forbid() + "' where id = " + this.getId() ;

            int result = stmt.executeUpdate(sql);


            if(result == 0){

                return false;

            }
            else{
                this.id = result;
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return false;
    }

    //判断用户名是否占用
    private boolean isValid(){
        try{

            Statement stmt = this.connection.createStatement();
            String sql = "SELECT * FROM  user where username='" + this.getUsername() + "'";

            ResultSet result = stmt.executeQuery(sql);
            int count = 0;
            if (result.last()) {
                count = result.getRow();
            }
            if(count > 0){
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return true;

    }

    @Override
    public String toString() {
        return "username : " + this.username ;
    }
}
