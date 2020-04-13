package com.zay.depensesbe.filters;


import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@Component
public class MainFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(MainFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        Random random = new Random();
        Long reqId = random.nextLong();
        try {

            LOGGER.info("START [" + req.getMethod() + "] " + req.getRequestURI() + " ("+reqId+")");
            chain.doFilter(servletRequest, servletResponse);
            LOGGER.info("END [" + req.getMethod() + "] " + req.getRequestURI() + " ("+reqId+")") ;

        } catch (Exception e){
            LOGGER.error("ERROR in request "+ reqId);
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
