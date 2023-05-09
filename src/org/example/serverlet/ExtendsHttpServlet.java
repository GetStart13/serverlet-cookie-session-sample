package org.example.serverlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> 2023/5/9 </p>
 * 继承 HttpServlet 方式实现 servlet
 *
 * @author Fqq
 */
@WebServlet("/servlet/extends")
public class ExtendsHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("》》》》》 进入 servlet Post 方法 》》》》》");
        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
    }

}
