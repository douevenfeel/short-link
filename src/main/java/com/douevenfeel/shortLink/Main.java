package com.douevenfeel.shortLink;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.douevenfeel.shortLink");
        context.getBean(OperationConsoleListener.class).start();
    }
}