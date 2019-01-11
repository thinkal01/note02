package com.note.util.itcast.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String charset = "UTF-8";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getMethod().equalsIgnoreCase("GET")) {
            if (!(req instanceof GetRequest)) {
                //处理get请求编码
                req = new GetRequest(req, charset);
            }
        } else {
            //处理post请求编码
            req.setCharacterEncoding(charset);
        }
        chain.doFilter(req, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        String charset = fConfig.getInitParameter("charset");
        if (charset != null && !charset.isEmpty()) {
            this.charset = charset;
        }
    }
}
