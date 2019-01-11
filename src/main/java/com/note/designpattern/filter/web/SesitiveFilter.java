package com.note.designpattern.filter.web;

public class SesitiveFilter implements Filter {


    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.requestStr.replace("敏感", "")
                .replace("被就业", "就业") + "---SesitiveFilter()";
        chain.doFilter(request, response, chain);
        response.responseStr += "---SesitiveFilter()";
    }
}
