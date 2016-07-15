package com.example.sarthak.testproject.model;

import java.io.Serializable;

/**
 * Created by Sarthak on 3/12/2016.
 */
public class StoreItem implements Serializable{
    private float rating;
    private String title;
    private String discription;
    private int imageResource;
    private int prize;

    public StoreItem(float rating, String title, String discription, int imageResource, int prize) {
        this.rating = rating;
        this.title = title;
        this.discription = discription;
        this.imageResource = imageResource;
        this.prize = prize;
    }

    public StoreItem() {
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
