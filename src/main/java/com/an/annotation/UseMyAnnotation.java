package com.an.annotation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UseMyAnnotation {

    @MyAnnotation(color = "colortest2  method")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @MyAnnotation(color="colortest",product="producttest")
    public static void genericsTest() throws FileNotFoundException {
        List<String> l = new ArrayList<String>();
        l.add("abc");
        oldMethod();

    }
}
