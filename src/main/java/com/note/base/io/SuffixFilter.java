package com.note.base.io;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixFilter implements FilenameFilter {
    private String suffix;

    public SuffixFilter(String suffix) {
        super();
        this.suffix = suffix;
    }

    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }


    public static void listDemo_2() {
        File dir = new File("c:\\");
        String[] names = dir.list(new SuffixFilter(".txt"));
        for (String name : names) {
            System.out.println(name);
        }
    }
}