package com.masterminds;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Filter started!");
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;

	    response.setHeader("Access-Control-Allow-Origin", "https://ipl-masterminds-angular.herokuapp.com");
//	    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
//	    response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Access-Control-Request-Method, Access-Control-Request-Headers, Origin, X-Requested-With, Content-Type, Accept, content-type, application/json");
	    response.setHeader("Access-Control-Allow-Headers", "Access-Control-Request-Headers");
	    chain.doFilter(req, res);
	}

}
