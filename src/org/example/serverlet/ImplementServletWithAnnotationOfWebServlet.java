package org.example.serverlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

/**
 * <p> 2023/5/9 </p>
 * 注解方式实现 servlet
 *
 * @author Fqq
 */
@WebServlet("/servlet/impl")
public class ImplementServletWithAnnotationOfWebServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) {
        System.out.println("》》》》》 servlet 初始化 》》》》》");
        System.out.println("获取 servlet 配置和上下文 context: " + servletConfig);
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("》》》》》 获取 servlet 配置的方法（可选） 》》》》》");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        System.out.println("》》》》》 进入 servlet 服务 》》》》》");
        System.out.println("request: " + servletRequest);
        System.out.println("response: " + servletResponse);
    }

    @Override
    public String getServletInfo() {
        System.out.println("》》》》》 获取 servlet 信息的方法（可选） 》》》》》");
        return "some info";
    }

    @Override
    public void destroy() {
        System.out.println("》》》》》 servlet 被销毁 》》》》》");
    }
}
