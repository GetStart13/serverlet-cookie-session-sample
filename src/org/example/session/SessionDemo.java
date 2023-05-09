package org.example.session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session 依赖于 cookies
 */
@WebServlet("/session")
public class SessionDemo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 使用 session 获取数据
            // 1、获取session
            HttpSession session = req.getSession();
            System.out.println(session);
            // 2、获取数据
            Object msg = session.getAttribute("msg");
            System.out.println(msg);
            // 移除数据
            session.removeAttribute("msg");

            // 3、将 session 存入 cookie 中，服务器关闭后 session 也能相同
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(60 * 60);// session 存活时间 (s)
            resp.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.doPost(req, resp);
    }
}
