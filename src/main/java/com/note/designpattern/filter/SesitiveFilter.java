package com.note.designpattern.filter;

public class SesitiveFilter implements Filter {

    @Override
    public String doFilter(String str) {
        //process the sensitive words
        String r = str.replace("敏感", "").replace("被就业", "就业");
        return r;
    }
}
