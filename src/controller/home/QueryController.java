package controller.home;

import model.Donation;
import model.Support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ningge on 16/1/15.
 */
public class QueryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String action = req.getParameter("action");
        String type = req.getParameter("type");
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        String name = req.getParameter("name");
        if(action != null){
            if(action.equals("query")){
                if(type.equals("1")){//捐款查询
                    ArrayList<Donation> donations = Donation.query(start,end,name);
                    req.setAttribute("items",donations);
                }
                else{
                    ArrayList<Support> supports = Support.query(start,end,name);
                    req.setAttribute("items",supports);
                }

                req.getRequestDispatcher("/front/query/redirect.jsp").forward(req,resp);
                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        this.doGet(req,resp);
    }
}
