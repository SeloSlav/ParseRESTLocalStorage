package com.martinerlic.parserestlocalstorage.model;

/**
 * Created by mnxe on 8/14/2017.
 */

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {

    private static final String CLASS_NAME = "Post";

    private String objectId;
    private String text;
    private int _id;
    private List<Photo> photos;

    public Post() {
    }

    public String getClassName() {
        return CLASS_NAME;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
