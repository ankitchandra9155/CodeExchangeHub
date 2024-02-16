//package com.backend.configuration;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class SimpleCorsFilter implements Filter {
//	
//	private final String clientAppUrl="http://localhost:4200/*";
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		HttpServletResponse response=(HttpServletResponse)servletResponse;
//		HttpServletRequest request=(HttpServletRequest)servletRequest;
//		Map<String,String> map=new HashMap<>();
//		
//		String originHeader=request.getHeader("origin");
//		response.setHeader("Access-Control-Allow-Origin", originHeader);
//		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Headers", "*");
//		
//		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//			response.setStatus(HttpServletResponse.SC_OK);
//		}
//		else
//		{
//			filterChain.doFilter(servletRequest, servletResponse);
//		}
//		
//	}
//
//	
//
//
//}
