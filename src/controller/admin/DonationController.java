package controller.admin;

import com.google.gson.Gson;
import common.CommonFunctions;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ningge on 16/1/11.
 */
public class DonationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String action = req.getParameter("action");

        switch (action){
            case "add":
                add(req,resp);
                break;
            case "getCity":
                getCity(req,resp);
                break;
            case "list":
                list(req,resp);
                break;
            case "delete":
                delete(req,resp);
                break;
            case "view":
                view(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String complete = request.getParameter("complete");
        request.setCharacterEncoding("utf-8");
        if(complete == null){
            Map<Integer, String> provinces = MyConnection.getProvinces();
            ArrayList city = MyConnection.getCity("1");
            request.setAttribute("provinces", provinces);
            request.setAttribute("city", city);
            Iterator iterator = provinces.entrySet().iterator();
            Map cities = new HashMap<Integer,ArrayList<City>>();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                String key = Integer.toString((Integer)entry.getKey());
                cities.put(Integer.parseInt(key), MyConnection.getCity(key));
            }
            request.setAttribute("cities", cities);
            request.getRequestDispatcher("/view/donation/add.jsp").forward(request,response);
            return;
        }

        String person = new String(request.getParameter("person").getBytes("ISO8859_1"),"utf-8");

        String province = new String(request.getParameter("province").getBytes("ISO8859_1"),"utf-8");
        String city = new String(request.getParameter("city").getBytes("ISO8859_1"),"utf-8");
        String amount = new String(request.getParameter("amount").getBytes("ISO8859_1"),"utf-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Donation donation = new Donation(person,province,city,amount,df.format(new Date()));
        if(!donation.insert()){
            CommonFunctions.error(request,response,"","添加失败,请重试","");
        }
        else{
            response.sendRedirect("/donation?action=list");
            return;
        }
    }
    //捐助信息详情
    public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String id = request.getParameter("id");
        Donation donation = Donation.findById(id);
        request.setAttribute("donation",donation);
        String complete = request.getParameter("complete");
        if(complete != null){
            request.getRequestDispatcher("/donation?action=list").forward(request,response);
            return;
        }
        request.getRequestDispatcher("/view/donation/view.jsp").forward(request,response);
    }

    //编辑信息详情
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String id = request.getParameter("id");
        String complete = request.getParameter("complete");
        if(complete == null){
            Donation donation = Donation.findById(id);
            request.setAttribute("donation",donation);
            Map<Integer, String> provinces = MyConnection.getProvinces();
            ArrayList city = MyConnection.getCity("1");
            request.setAttribute("provinces", provinces);
            request.setAttribute("city", city);
            Iterator iterator = provinces.entrySet().iterator();
            Map cities = new HashMap<Integer,ArrayList<City>>();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                String key = Integer.toString((Integer)entry.getKey());
                cities.put(Integer.parseInt(key), MyConnection.getCity(key));
            }
            request.setAttribute("cities", cities);
            request.getRequestDispatcher("/view/donation/edit.jsp").forward(request,response);
            return;
        }
        String person = new String(request.getParameter("person").getBytes("ISO8859_1"),"utf-8");
        String province = new String(request.getParameter("province").getBytes("ISO8859_1"),"utf-8");
        String city = new String(request.getParameter("city").getBytes("ISO8859_1"),"utf-8");
        String amount = new String(request.getParameter("amount").getBytes("ISO8859_1"),"utf-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Donation donation = new Donation(person,province,city,amount,df.format(new Date()));
        donation.setId(Integer.parseInt(id));

        if(!donation.update()){
            CommonFunctions.error(request, response, "", "修改失败,请重试", "");
        }
        else{
            request.getRequestDispatcher("/donation?action=list").forward(request,response);
            return;
        }

    }

    public void getCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ArrayList city = MyConnection.getCity(id);
        String result = new Gson().toJson(city);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);

    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Donation> donations = new Donation().getAll();
        request.setAttribute("donations",donations);
        request.getRequestDispatcher("/view/donation/list.jsp").forward(request,response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        if(!Donation.deleteByID(id)) {
            CommonFunctions.error(request, response, "", "删除失败,请重试", "");
        }
        request.getRequestDispatcher("/donation?action=list").forward(request,response);

    }
}
