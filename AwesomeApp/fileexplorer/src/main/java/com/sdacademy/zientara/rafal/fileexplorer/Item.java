package com.sdacademy.zientara.rafal.fileexplorer;

public class Item implements Comparable<Item> {
    private String name;
    private String description;
    private String date;
    private String path;
    private boolean isDirectory;

    public Item(String name, String description, String date, String path, boolean isDirectory) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.path = path;
        this.isDirectory = isDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public int compareTo(Item o) {
        if (this.name != null)
            return this.name.toLowerCase().compareTo(o.name.toLowerCase());
        else
            throw new IllegalArgumentException();
    }
}
