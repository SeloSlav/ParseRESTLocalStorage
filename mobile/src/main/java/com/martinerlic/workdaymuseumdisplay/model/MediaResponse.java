package com.martinerlic.workdaymuseumdisplay.model;

/**
 * Created by mnxe on 8/14/2017.
 */

import java.util.ArrayList;
import java.util.List;

public class MediaResponse {
    private ArrayList<String> media_items;

    public MediaResponse(ArrayList<String> media_items) {
        this.media_items = media_items;
    }

    public ArrayList<String> getResults() {
        return media_items;
    }

}