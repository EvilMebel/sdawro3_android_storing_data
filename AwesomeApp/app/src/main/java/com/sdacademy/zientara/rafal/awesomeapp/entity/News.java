package com.sdacademy.zientara.rafal.awesomeapp.entity;

import android.support.annotation.NonNull;

import java.util.Date;

public class News implements Comparable<News>{
    private int id;
    private String title;
    private String description;
    private Date dateStart;
    private Date dateFinish;
    private int isAsset;

    public int getId() {
        return id;
    }

    public void setId(int mId) {
        this.id = mId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mName) {
        this.title = mName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        this.description = mDescription;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date mDateStart) {
        this.dateStart = mDateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date mDateFinish) {
        this.dateFinish = mDateFinish;
    }

    public boolean isAsset() {
        return isAsset != 0;
    }

    public void setIsAsset(int mIsAsset) {
        this.isAsset = mIsAsset;
    }

    @Override
    public int compareTo(@NonNull News news) {
        return news.getDateStart().getTime() < getDateStart().getTime() ? 1 : -1;
    }
}
