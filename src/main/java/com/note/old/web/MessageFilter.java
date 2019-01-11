package com.note.old.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class MessageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String l = req.getParameter("request_locale");
        Locale locale;
        if (l != null && !l.isEmpty()) {
            String[] strs = l.split("_");
            locale = new Locale(strs[0], strs[1]);
            req.getSession().setAttribute("WW_TRANS_I18N_LOCALE", locale);
        } else {
            locale = (Locale) req.getSession().getAttribute("WW_TRANS_I18N_LOCALE");
        }

        if (locale == null) {
            locale = req.getLocale();
        }
        MessageUtils.setLocale(locale);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) {
    }
}
