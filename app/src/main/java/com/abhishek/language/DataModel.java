package com.abhishek.language;

public class DataModel {
    String name;
    String votes;
    String logo;
    String english_text;
    String native_text;

    public DataModel() {}
    public DataModel(String name, String votes, String logo) {

        this.name = name;
        this.votes = votes;
        this.logo = logo;
    }


    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
