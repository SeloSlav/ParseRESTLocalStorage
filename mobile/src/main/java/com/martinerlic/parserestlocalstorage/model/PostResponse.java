package com.martinerlic.parserestlocalstorage.model;

/**
 * Created by mnxe on 8/14/2017.
 */

import java.util.List;

public class PostResponse {
    private List<Post> results;

    public List<Post> getResults() {
        return results;
    }

    public void setResults(List<Post> results) {
        this.results = results;
    }
}