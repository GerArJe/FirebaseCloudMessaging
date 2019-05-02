package com.gerarje.firebaseprojectandroid.model;

public class MyNotification {

    private String id;
    private String title;
    private String description;
    private String descount;

    public MyNotification() {
    }

    public MyNotification(String id, String title, String description, String descount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.descount = descount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescount() {
        return descount;
    }

    public void setDescount(String descount) {
        this.descount = descount;
    }
}
