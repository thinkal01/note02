package com.note.designpattern.filter.web;

public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}
