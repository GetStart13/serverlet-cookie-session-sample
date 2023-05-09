package org.example.cookie;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/cookie")
public class CookieDemo extends HttpServlet {
    private static final String UTF8 = "utf-8";
    private static final String ACCESS_TIME = "access-time";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {

            // 设置响应消息的格式及编码
            resp.setContentType("text/html;charset=utf-8");
            // 获取所有 cookie 数组
            Cookie[] cookies = req.getCookies();
            // 访问标记
            boolean accessFlag = false;

            // 遍历 cookie 数组
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // 获取 cookie 名称
                    String name = cookie.getName();

                    // 判断名称是否是 lastTime
                    if (ACCESS_TIME.equals(name)) {
                        // 第一次访问
                        accessFlag = true;
                        // 响应数据
                        // 获取 cookie 的 value 值，值为之前设置的时间
                        String value = cookie.getValue();
                        value = URLDecoder.decode(value, UTF8);// URL 解码
                        resp.getWriter().write("<h1>欢迎回来，你上次的访问时间是：" + value + "<h1>");

                        // 设置 cookie 的 value
                        // 获取当前时间字符串，重新设置 cookie 的值，重新发送 cookie
                        LocalDateTime now = LocalDateTime.now();//jdk新时间
                        // 新时间格式化
                        String newDate = now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒"));
                        String cookieValue = URLEncoder.encode(newDate, UTF8);// 给字符串编码
                        cookie.setValue(cookieValue);// 重新赋值

                        // 设置 cookie 的存活时间(s)
                        cookie.setMaxAge(60 * 60 * 2);// 存活2小时
                        resp.addCookie(cookie);
                    }
                }

            }
            if (cookies == null || !accessFlag) {
                // 没有 cookie，第一次访问
                // 设置 cookie 的 value
                // 获取当前时间字符串，重新设置 cookie 的值，重新发送 cookie
                LocalDateTime now = LocalDateTime.now();
                String nowDate = now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒"));
                String cookieValue = URLEncoder.encode(nowDate, UTF8);// 给字符串编码

                // 新建一个 cookie，名称为 lastTime，值为 cookieValue
                Cookie cookie = new Cookie(ACCESS_TIME, cookieValue);
                // 设置 cookie 的存活时间(s)
                cookie.setMaxAge(60 * 60 * 2);// 存活两小时
                resp.addCookie(cookie);

                // 回写页面数据
                resp.getWriter().write("<h1> 欢迎第一次访问 <h1>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.doPost(req, resp);
    }
}
