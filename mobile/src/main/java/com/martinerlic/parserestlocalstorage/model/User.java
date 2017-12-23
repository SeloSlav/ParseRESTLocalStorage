package com.martinerlic.parserestlocalstorage.model;

import java.io.Serializable;

/**
 * @author Bluebery
 * @since 3/12/2016
 */
public class User implements Serializable {

    private static final String CLASS_NAME = "User";

    private String objectId;
    private String username;

    public User(String objectId) {
        this.setObjectId(objectId);
    }

    public static String getClassName() {
        return CLASS_NAME;
    }

    public String getObjectId() {
        return objectId;
    }

    private void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String text) {
        this.username = username;
    }
}
