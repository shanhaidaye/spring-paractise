//package org.register.servlet;
//
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import sun.net.www.content.text.Generic;
//
//import javax.servlet.*;
//import java.io.IOException;
//
///**
// * Created by sunshiwang on 16/6/5.
// */
//public class ServletProxy extends GenericServlet {
//
//    private Servlet proxy;
//
//    public void init() throws ServletException{
//        super.init();
//        //初始化spring容器
//
//        WebApplicationContext wac= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
//        this.proxy=(Servlet)wac.getBean(getServletName());
//        proxy.init(getServletConfig());
//    }
//
//    @Override
//    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        proxy.service(servletRequest,servletResponse);
//    }
//}
