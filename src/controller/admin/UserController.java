package controller.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;

import common.*;
import model.User;

/**
 * Created by ningge on 16/1/3.
 */

//用户管理
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String action = req.getParameter("action");
        //判断是否登录
        if(!common.CommonFunctions.checkLogin(req)){
            common.CommonFunctions.error(req,resp,"","您还未登录","/view/login.html");
        }
        String username = (String)req.getSession().getAttribute("username");
        if(!username.equals("admin")){
            CommonFunctions.error(req,resp,"","您的权限不足,无法访问该页面","");
            return;
        }
        switch (action){
            case "add":
                this.add(req,resp);
                break;
            case "list":
                this.list(req,resp);
                break;
            case "view":
                this.view(req,resp);
                break;
            case "delete":
                this.delete(req,resp);
                break;
            case "edit":
                this.edit(req,resp);
                break;
        }
    }

    //添加用户
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String complete = request.getParameter("complete");
        request.setCharacterEncoding("utf-8");
        if(complete == null){
            request.getRequestDispatcher("/view/user/add.jsp").forward(request,response);
            return;
        }

        String username = new String(request.getParameter("username").getBytes("ISO8859_1"),"utf-8");
        String password = new String(request.getParameter("password").getBytes("ISO8859_1"),"utf-8");;
        String canLogin = request.getParameter("canLogin").trim();
        boolean is_forbid = canLogin.equals("false") ? true : false;

        if(username.equals("") || password.equals("") || username.length() < 3 || password.length() < 6){
            CommonFunctions.error(request,response,"","用户名或密码格式错误","");
        }
        else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

            User user = new User(username,password,is_forbid,df.format(new Date()));
            if(!user.insert()){
                CommonFunctions.error(request,response,"","用户名被占用,请重试","");
            }
            else{
                response.sendRedirect("/user?action=list");
                return;
            }

        }


    }
    //用户列表
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        ArrayList<User> users = User.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/view/user/list.jsp").forward(request,response);
    }
    //查看用户信息
    public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        User user = User.findById(id);

        request.setAttribute("user", user);
        request.getRequestDispatcher("/view/user/view.jsp").forward(request,response);
    }
    //编辑用户
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        String complete = request.getParameter("complete");
        User user = User.findById(id);
        if(complete == null){
            request.setAttribute("user", user);
            request.getRequestDispatcher("/view/user/edit.jsp").forward(request,response);
            return;
        }

        String username = new String(request.getParameter("username").getBytes("ISO8859_1"),"utf-8");
        String password = new String(request.getParameter("password").getBytes("ISO8859_1"),"utf-8");;
        String canLogin = request.getParameter("canLogin").trim();
        boolean is_forbid = canLogin.equals("false") ? true : false;

        User userGet = new User(username,password,is_forbid,null);
        if(userGet.update()){
            response.sendRedirect("/user?action=list");
            return;
        }
        else{
            CommonFunctions.error(request,response,"","修改失败,请重试","");
        }
    }
    //删除用户
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        User user = User.findById(id);
        if(!user.delete()) {
            CommonFunctions.error(request, response, "", "删除失败,请重试", "");
        }

        request.getRequestDispatcher("/user?action=list").forward(request,response);

    }
}
