package com.note.common.lucene.itcast.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharSetFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}
