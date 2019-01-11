package com.note.controller.converter;

import org.springframework.core.convert.converter.Converter;

public class CustomIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        try {
            return Integer.parseInt(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
