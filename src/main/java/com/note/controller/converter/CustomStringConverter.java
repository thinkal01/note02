package com.note.controller.converter;

import org.springframework.core.convert.converter.Converter;

public class CustomStringConverter implements Converter<String, String> {

    @Override
    public String convert(String source) {
        try {
            if (source != null) {
                source = source.trim();
                if (source.equals("")) {
                    source = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return source;
    }

}
