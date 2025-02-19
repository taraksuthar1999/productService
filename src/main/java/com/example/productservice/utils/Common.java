package com.example.productservice.utils;

public class Common {
    public static String convertToSku(String title){
        return title.replaceAll("\\s+","-").toLowerCase();
    }
}
