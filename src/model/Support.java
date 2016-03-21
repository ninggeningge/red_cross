package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ningge on 15/12/31.
 */
public class Support extends Model {
    private String name ;//捐款人
    private String idnum;//受捐款人身份证号
    private String amount;//捐款额
    private String time;//捐款时间
    private int id;


    //构造函数
    public Support() {
    }

    public Support(String name, String idnum, String amount, String time){
        this.name = name;
        this.idnum = idnum;
        this.amount = amount;
        this.time = time;
    }

    //getters and setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return  this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getIdnum(){
        return this.idnum;
    }

    public void setIdnum(String idnum){
        this.idnum = idnum;
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
            String sql = "insert into support(`idnum`, `name`, `amount`, `time`) values('" +
                    this.getIdnum() + "','" + this.getName() +
                    "','" + this.getAmount() + "','" + this.getTime() +"')";

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


    //获取全部信息
    public ArrayList<Support> getAll(){
        ArrayList<Support> supports = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM support where 1 order by time";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.equals(null)){
            }
            else{
                while(resultSet.next()){
                    int id  = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String idnum = resultSet.getString("idnum");
                    String amount = resultSet.getString("amount");
                    String time = resultSet.getString("time");
                    Support support =  new Support(idnum,name,amount,time);
                    support.setId(id);
                    supports.add(support);
                }
                resultSet.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return supports;
    }

    public static Support findById(String idString){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM support where id = ?");
            statement.setString(1,idString);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.equals(null)){

            }
            else{
                while(resultSet.next()){
                    //Retrieve by column name
                    int id  = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String amount = resultSet.getString("amount");
                    String idnum = resultSet.getString("idnum");
                    String time = resultSet.getString("time");
                    Support support = new Support(idnum,name, amount,time);
                    support.setId(id);
                    return support;
                }
                connection.close();
                resultSet.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new Support();
    }

    public static boolean deleteByID(String id){
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = MyConnection.getConnection();

                Statement statement = connection.createStatement();
                String sql = "DELETE  FROM support where id = " + id + "";
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

    public boolean update(){
        try{
            Statement stmt = MyConnection.getConnection().createStatement();
            String sql = "update support set name='" + this.getName() + "', amount='" +
                    this.getAmount() +"', idnum='" +
                    this.getIdnum() + "' where id = " + this.getId() ;
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
    //查询信息
    public static ArrayList<Support> query(String start, String end, String name){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = MyConnection.getConnection();
            PreparedStatement statement;
            if(name == null ){
                statement = connection.prepareStatement("" +
                        "SELECT * FROM support where time >= ? AND time <= ? ORDER BY time");
                statement.setString(1,start);
                statement.setString(2,end);
            }
            else if(name.equals("")){
                statement = connection.prepareStatement("" +
                        "SELECT * FROM support where time >= ? AND time <= ? ORDER BY time");
                statement.setString(1,start);
                statement.setString(2,end);
            }
            else{
                statement = connection.prepareStatement("" +
                        "SELECT * FROM support where name = ? AND time >= ? AND time <= ? ORDER BY time");
                statement.setString(1,name);
                statement.setString(2,start);
                statement.setString(3,end);
            }
            ArrayList<Support> supports = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.equals(null)){
                return new ArrayList<>();
            }
            else{
                while(resultSet.next()){
                    int id  = resultSet.getInt("id");
                    String time = resultSet.getString("time");
                    String amount = resultSet.getString("amount");
                    String fname = resultSet.getString("name");
                    String idNum = resultSet.getString("idNum");
                    supports.add(new Support(fname,idNum,amount,time));
                }
                resultSet.close();
                connection.close();
                return supports;
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
            String sql = "SELECT * FROM  support where 1";

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
