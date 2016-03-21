package controller.admin;

import common.CommonFunctions;
import model.Donation;
import model.MyConnection;
import model.Support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ningge on 16/1/14.
 */
public class SupportController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String action = req.getParameter("action");
        switch (action){
            case "add":
                add(req,resp);
                break;
            case "list":
                list(req,resp);
                break;
            case "view":
                view(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            case "delete":
                delete(req,resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }


    //添加资助信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String complete  = request.getParameter("complete");
        if(complete == null){
            request.getRequestDispatcher("/view/support/add.jsp").forward(request,response);
            return;
        }
        String idnum = new String(request.getParameter("idNum").getBytes("ISO8859_1"),"utf-8");
        String name = new String(request.getParameter("name").getBytes("ISO8859_1"),"utf-8");
        String amount = new String(request.getParameter("amount").getBytes("ISO8859_1"),"utf-8");
        String total = MyConnection.getTotal();
        if(Integer.parseInt(amount) > Integer.parseInt(total)){
            CommonFunctions.error(request,response,"","超额了,没那么多钱捐啊","");
            return;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Support support = new Support(idnum, name, amount, df.format(new Date()));
        if(!support.insert()){
            CommonFunctions.error(request,response,"","添加失败,请重试","");
        }
        else{
            response.sendRedirect("/support?action=list");

        }
    }

    //资助信息列表
    public void list(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        ArrayList<Support> supports = new Support().getAll();
        request.setAttribute("supports",supports);
        request.getRequestDispatcher("/view/support/list.jsp").forward(request,response);
    }

    //资助信息详情
    public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String id = request.getParameter("id");
        String complete = request.getParameter("complete");
        if(complete == null){
            Support support = Support.findById(id);
            request.setAttribute("support",support);
            request.getRequestDispatcher("/view/support/view.jsp").forward(request,response);
            return;
        }
        else{
            request.getRequestDispatcher("/support?action=list").forward(request,response);
            return;
        }

    }

    //编辑信息详情
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String id = request.getParameter("id");
        String complete = request.getParameter("complete");
        Support support = Support.findById(id);
        if(complete == null){
            request.setAttribute("support",support);
            request.getRequestDispatcher("/view/support/edit.jsp").forward(request,response);
            return;
        }
        else{
            String idnum = new String(request.getParameter("idNum").getBytes("ISO8859_1"),"utf-8");
            String name = new String(request.getParameter("name").getBytes("ISO8859_1"),"utf-8");
            String amount = new String(request.getParameter("amount").getBytes("ISO8859_1"),"utf-8");
            String total = MyConnection.getTotal();
            if(Integer.parseInt(amount) > Integer.parseInt(total) + Integer.parseInt(support.getAmount())){
                CommonFunctions.error(request,response,"","超额了,没那么多钱捐啊","");
                return;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Support support2 = new Support(idnum, name, amount, df.format(new Date()));
            support2.setId(Integer.parseInt(id));
            if(!support2.update()){
                CommonFunctions.error(request,response,"","更新失败,请重试","");
            }
            else{
                request.getRequestDispatcher("/support?action=list").forward(request,response);
                return;
            }

        }
    }

    //删除记录
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String id = request.getParameter("id");
        if(!Support.deleteByID(id)) {
            CommonFunctions.error(request, response, "", "删除失败,请重试", "");
        }

        request.getRequestDispatcher("/support?action=list").forward(request,response);
    }
}
