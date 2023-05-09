package org.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <p> 2023/5/9 </p>
 * 注解方式实现 filter 过滤器，过滤器的 xml 配置方式和 servlet一样
 * <br> /* 表示过滤所有请求
 *
 * @author Fqq
 */
@WebFilter("/*")
public class ImplementFilterWithAnnotationOfWebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("》》》》》 filter 初始化 》》》》》");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("》》》》》 进入 filter 过滤器中 》》》》》");

        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("》》》》》 filter 过滤器被销毁 》》》》》");
    }
}
