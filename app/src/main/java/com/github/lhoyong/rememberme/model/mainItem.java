package com.github.lhoyong.rememberme.model;

public class mainItem {

    private int id;
    private String image;
    private String message;
    private Long date;

    public mainItem(int id, String image, String message, Long date) {
        this.id = id;
        this.image = image;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
