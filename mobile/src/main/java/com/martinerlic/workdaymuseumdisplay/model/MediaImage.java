package com.martinerlic.workdaymuseumdisplay.model;

/**
 * Created by mnxe on 8/14/2017.
 */

public class MediaImage {
    private int id;
    private String title;

    public MediaImage() {
    }

    public MediaImage(String title) {
        super();
        this.title = title;
    }

    public MediaImage(int id) {
        this.setId(id);
    }

    @Override
    public String toString() {
        return "MediaImage [id=" + getId() + ", title=" + getTitle() + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}