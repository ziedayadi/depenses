package com.zay.depensesbe.filters;

import com.zay.depensesbe.controllers.charges.ChargesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MainFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        LOGGER.info("["+req.getMethod() + "]\t" +req.getRequestURI());
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
