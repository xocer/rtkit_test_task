package test.servlet;

import com.alibaba.fastjson.JSON;
import test.Store.JdbcStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (action.equals("all")) {
            resp.getWriter().write(
                    JSON.toJSONString(JdbcStore.instOf().getAll()));
        } else if (action.equals("one")) {
            int id = Integer.parseInt(req.getParameter("id"));
            resp.getWriter().write(
                    JSON.toJSONString(JdbcStore.instOf().getColorById(id)));
        }
    }
}
