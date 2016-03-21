package controller.home;

import com.google.gson.Gson;
import model.Donation;
import model.MyConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ningge on 16/1/16.
 */
public class StatisticsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        req.getRequestDispatcher("/front/query/statistics.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        ArrayList<Donation> donations = Donation.selectByTime(start,end);
        Map<Integer,String> province = MyConnection.getProvinces();
        Iterator iterator = province.entrySet().iterator();
        Map<String,Integer> result= new HashMap<String,Integer>();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            String value = (String)entry.getValue();
            result.put(value,0);
        }
        for(int i = 0; i < donations.size(); i++ ){
            Donation donation = donations.get(i);
            int origin = (int)result.remove(donation.getProvince());
            result.put(donation.getProvince(), origin + Integer.parseInt(donation.getAmount()));
        }
        String resultString = new Gson().toJson(result);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(resultString);
    }

    public void getData(String start, String end, HttpServletResponse response)throws ServletException, IOException{
        ArrayList<Donation> donations = Donation.selectByTime(start,end);
        Map<Integer,String> province = MyConnection.getProvinces();
        Iterator iterator = province.entrySet().iterator();
        Map<String,Integer> result= new HashMap<String,Integer>();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            String value = (String)entry.getValue();
            result.put(value,0);
        }
        for(int i = 0; i < donations.size(); i++ ){
            Donation donation = donations.get(i);
            int origin = (int)result.remove(donation.getProvince());
            result.put(donation.getProvince(), origin + Integer.parseInt(donation.getAmount()));
        }
        String resultString = new Gson().toJson(result);

        response.getWriter().write(resultString);

    }
}
