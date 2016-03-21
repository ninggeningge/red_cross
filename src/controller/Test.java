package controller;
import com.google.gson.Gson;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import common.*;

/**
 * Created by ningge on 15/12/31.
 */
public class Test extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String json = new Gson().toJson(new City(1, "2"));
        System.out.print(json);
//        MyConnection myConnection = new MyConnection();
//        User user = new User("233","233",false);
//        myConnection.insert(user);
//        System.out.print(user.getId());
    }
}
