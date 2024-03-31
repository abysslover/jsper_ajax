package com.javawide.book.blackbook1.ch07.filters;

import java.io.IOException;

import javax.servlet.*;

public class ChainFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		response.getWriter().print("Copyright by magneto");
	}
	public void init(FilterConfig config) throws ServletException {
	}
}
