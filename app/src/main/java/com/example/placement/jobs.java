package com.example.placement;

public class jobs {
    private  String title;
    private String description;
    private String date;

    public jobs() {
    }

    public jobs(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
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
        String trimdes=description.substring(0,35);
        this.description = trimdes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
