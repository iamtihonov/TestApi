package com.example.testapi;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ApiScreen {

    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("image")
    private String image;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImage() {
        return image;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}