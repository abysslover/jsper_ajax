package com.javawide.book.blackbook1.ch07.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloFilter implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		if("로그인 성공".equals(((HttpServletRequest)request).getSession().getAttribute("loginMessage"))) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED);			
		}
	}
	public void init(FilterConfig config) throws ServletException {
	}
}
