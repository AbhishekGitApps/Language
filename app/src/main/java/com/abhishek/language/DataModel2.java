package com.abhishek.language;

public class DataModel2 {

    String english_text;
    String native_text;

    public DataModel2() {}

    public DataModel2(String english_text, String native_text) {
        this.english_text = english_text;
        this.native_text = native_text;
    }

    public String getEnglish_text() {
        return english_text;
    }

    public void setEnglish_text(String english_text) {
        this.english_text = english_text;
    }

    public String getNative_text() {
        return native_text;
    }

    public void setNative_text(String native_text) {
        this.native_text = native_text;
    }
}
