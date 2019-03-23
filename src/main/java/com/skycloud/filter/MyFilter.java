package com.skycloud.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author angel
 * @date 2019/3/22
 */
public class MyFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTI ONS, DELETE");

        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
