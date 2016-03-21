package model;

import common.CommonFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringJoiner;

/**
 * Created by ningge on 15/12/31.
 */
public class Donation extends Model {
    private String name ;//捐款人
    private String province;//所在省份
    private String city;//所在城市
    private String amount;//捐款额
    private String time;//捐款时间
    private int id;


    //构造函数

    public Donation() {
    }

    public Donation(String name, String province, String city, String amount, String time){
        this.name = name;
        this.province = province;
        this.city = city;
        this.amount = amount;
        this.time = time;
    }

    //getters and setters
    public String getName(){
        return  this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getProvince(){
        return  this.province;
    }

    public void  setProvince(String province){
        this.province = province;
    }

    public String getCity(){
        return  this.city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getAmount(){
        return this.amount;
    }

    public void setAmount(String amount){
        this.amount = amount;
    }

    public String getTime(){
        return this.time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public boolean insert(){
        try {

            Statement stmt = MyConnection.getConnection().createStatement();
            String sql = "insert into donation(`name`, `province`, `city`,`amount`, `time`) values('" +
                    this.getName() + "','" + this.getProvince() +
                    "','" + this.getCity() + "','" + this.getAmount() + "','" + this.getTime() +"')";

            int result = stmt.executeUpdate(sql);


            if (result == 0) {
                return false;
            } else {
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(){
        return true;
    }

    public static boolean deleteByID(String id){
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = MyConnection.getConnection();

                Statement statement = connection.createStatement();
                String sql = "DELETE  FROM donation where id = " + id + "";
                int count = statement.executeUpdate(sql);
                connection.close();
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

    //查询信息
    public static ArrayList<Donation> query(String start, String end, String name){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();
            PreparedStatement statement;
            if(name == null ){
                statement = connection.prepareStatement("" +
                        "SELECT * FROM donation where time >= ? AND time <= ? ORDER BY time");
                statement.setString(1,start);
                statement.setString(2,end);
            }
            else if(name.equals("")){
                statement = connection.prepareStatement("" +
                        "SELECT * FROM donation where time >= ? AND time <= ? ORDER BY time");
                statement.setString(1,start);
                statement.setString(2,end);
            }
            else{
                statement = connection.prepareStatement("" +
                        "SELECT * FROM donation where name = ? AND time >= ? AND time <= ? ORDER BY time");
                statement.setString(1,name);
                statement.setString(2,start);
                statement.setString(3,end);
            }
            ArrayList<Donation> donations = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.equals(null)){
                return new ArrayList<>();
            }
            else{
                while(resultSet.next()){
                    int id  = resultSet.getInt("id");
                    String time = resultSet.getString("time");
                    String amount = resultSet.getString("amount");
                    String province = resultSet.getString("province");
                    province = MyConnection.getById(province);
                    String city = resultSet.getString("city");
                    city = MyConnection.getById(city);
                    String fname = resultSet.getString("name");
                    donations.add(new Donation(fname,province,city,amount,time));
                }
                resultSet.close();
                connection.close();
                return donations;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public boolean update(){
        try{
            Statement stmt = MyConnection.getConnection().createStatement();
            String sql = "update donation set name='" + this.getName() + "', amount='" +
                   this.getAmount() +"', province='" +
                    this.getProvince() +  "', city='" + this.getCity()  + "' where id = " + this.getId() ;
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

    public ArrayList<Donation> getAll(){
        ArrayList<Donation> donations = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM donation where 1 ORDER by time";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.equals(null)){
            }
            else{
                while(resultSet.next()){
                    int id  = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String province = resultSet.getString("province");
                    province = MyConnection.getById(province);
                    String city = resultSet.getString("city");
                    city = MyConnection.getById(city);
                    String amount = resultSet.getString("amount");
                    String time = resultSet.getString("time");
                    Donation donation =  new Donation(name,province,city,amount,time);
                    donation.setId(id);
                    donations.add(donation);
                }
                resultSet.close();
                connection.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return donations;
    }

    public static Donation findById(String idString){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM donation where id = ?");
            statement.setString(1,idString);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.equals(null)){

            }
            else{
                while(resultSet.next()){
                    //Retrieve by column name
                    int id  = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String province = resultSet.getString("province");
                    province = MyConnection.getById(province);
                    String city = resultSet.getString("city");
                    city = MyConnection.getById(city);
                    String amount = resultSet.getString("amount");
                    String time = resultSet.getString("time");
                    Donation donation =  new Donation(name,province,city,amount,time);
                    donation.setId(id);
                    return donation;
                }
                resultSet.close();
                connection.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new Donation();
    }
    public static  ArrayList<Donation> selectByTime(String start, String end){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM donation where time >= ? AND time <= ? ORDER BY time");;
            statement.setString(1,start);
            statement.setString(2,end);
            ArrayList<Donation> donations = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.equals(null)){
                return new ArrayList<>();
            }
            else{
                while(resultSet.next()){
                    int id  = resultSet.getInt("id");
                    String time = resultSet.getString("time");
                    String amount = resultSet.getString("amount");
                    String province = resultSet.getString("province");
                    province = MyConnection.getById(province);
                    String city = resultSet.getString("city");
                    city = MyConnection.getById(city);
                    String fname = resultSet.getString("name");
                    donations.add(new Donation(fname,province,city,amount,time));
                }
                resultSet.close();
                connection.close();
                return donations;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static String getCount(){
        try{

            Statement stmt = MyConnection.getConnection().createStatement();
            String sql = "SELECT * FROM  donation where 1";

            ResultSet result = stmt.executeQuery(sql);
            int count = 0;
            if (result.last()) {
                count = result.getRow();
            }
            stmt.getConnection().close();
            return Integer.toString(count);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "0";

    }



}
