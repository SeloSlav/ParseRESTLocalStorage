package com.martinerlic.parserestlocalstorage.model;

/**
 * Created by mnxe on 8/14/2017.
 */

import java.io.Serializable;

public class Photo implements Serializable {

    private static final String CLASS_NAME = "photos";

    private String id;
    private String url;

    public Photo() {
    }

    public String getClassName() {
        return CLASS_NAME;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
