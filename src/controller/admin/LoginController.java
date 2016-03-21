package controller.admin;
import common.*;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
// ...

/**
 * Created by ningge on 16/1/3.
 */

//用于用户登录注销逻辑
public class LoginController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String action = req.getParameter("action");
        switch (action){
            case "login":
                this.login(req,resp);
                break;
            case "logout":
                this.logout(req,resp);
                break;
        }
    }


    //登录函数
    public void login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        try{

            User user = User.findByName(username);
            if(user.getUsername()  == null ){
                CommonFunctions.error(request,response,"失败啦","用户名或密码错误", "");
            }
            else if(!user.getPassword().equals(CommonFunctions.md5(password))){
                CommonFunctions.error(request,response,"失败啦","用户名或密码错误", "");
            }
            else{
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(60 * 60);
                session.setAttribute("isLogin", true);
                session.setAttribute("username", username);
                response.sendRedirect("/view/index.jsp");
            }



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("./view/index.jsp");
    }
}
