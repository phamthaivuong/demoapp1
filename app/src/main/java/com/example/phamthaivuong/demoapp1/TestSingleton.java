package com.example.phamthaivuong.demoapp1;

public class TestSingleton {
    private static TestSingleton INSTANCE = null;

    // other instance variables can be here

    private TestSingleton() {};

    public static TestSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestSingleton();
        }
        return(INSTANCE);
    }

    // other instance methods can follow
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
