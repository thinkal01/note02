package com.note.util;

public class DebugOut {
    private static boolean DEBUG = true;

    public static void main(String[] args) {

    }

    public static void print(String arg0) {
        if (DEBUG)
            System.out.print(arg0);
    }

    public static void println(String arg0) {
        if (DEBUG)
            System.out.println(arg0);
    }

}
