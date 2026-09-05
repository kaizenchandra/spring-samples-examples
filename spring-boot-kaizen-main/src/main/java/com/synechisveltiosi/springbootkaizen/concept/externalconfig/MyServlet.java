package com.synechisveltiosi.springbootkaizen.concept.externalconfig;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyServlet extends HttpServlet {
    private String maxUsers;
    private String mode;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init();
        maxUsers = servletConfig.getInitParameter("maxUsers");
        mode = servletConfig.getInitParameter("mode");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.getWriter().write(
                "maxUsers=" + maxUsers + ", mode=" + mode
        );
    }


}
