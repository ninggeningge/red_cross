package controller.home;

import model.Donation;
import model.MyConnection;
import model.Support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ningge on 16/1/15.
 */
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String d_times = Donation.getCount();
        String s_times = Support.getCount();
        String total = MyConnection.getTotal();
        req.setAttribute("d_times", d_times);
        req.setAttribute("s_times",s_times);
        req.setAttribute("total", total);

        req.getRequestDispatcher("/front/index.jsp").forward(req,resp);

    }
}
