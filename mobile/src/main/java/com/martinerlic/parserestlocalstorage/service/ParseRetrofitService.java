package com.martinerlic.parserestlocalstorage.service;

/**
 * Created by mnxe on 8/14/2017.
 */

import com.martinerlic.parserestlocalstorage.model.PostResponse;
import com.martinerlic.parserestlocalstorage.model.User;

import retrofit.http.GET;
import retrofit.http.Headers;
import rx.Observable;

public interface ParseRetrofitService {

    String SERVICE_ENDPOINT = "https://parseapi.back4app.com";

    /*curl -X GET \
            -H "X-Parse-Application-Id: BdNNb0zgaGuhR5wcFTdP0NU3K4LVB2m9anCjQZGG" \
            -H "X-Parse-REST-API-Key: CVx8kjx38fqREH3VnNPO9WWqTO4X91hVU9LPC6JD" \
    https://parseapi.back4app.com/classes/_User/*/

    @Headers({
            "X-Parse-Application-Id: BdNNb0zgaGuhR5wcFTdP0NU3K4LVB2m9anCjQZGG",
            "X-Parse-REST-API-Key: CVx8kjx38fqREH3VnNPO9WWqTO4X91hVU9LPC6JD"
    })
    @GET("/classes/_User")
    Observable<User> getUsers();

    /*curl -X GET \
            -H "X-Parse-Application-Id: BdNNb0zgaGuhR5wcFTdP0NU3K4LVB2m9anCjQZGG" \
            -H "X-Parse-REST-API-Key: CVx8kjx38fqREH3VnNPO9WWqTO4X91hVU9LPC6JD" \
    https://parseapi.back4app.com/classes/Post/*/

    @Headers({
            "X-Parse-Application-Id: BdNNb0zgaGuhR5wcFTdP0NU3K4LVB2m9anCjQZGG",
            "X-Parse-REST-API-Key: CVx8kjx38fqREH3VnNPO9WWqTO4X91hVU9LPC6JD"
    })
    @GET("/classes/Post")
    Observable<PostResponse> getPosts();

    /*curl -X GET \
            -H "X-Parse-Application-Id: BdNNb0zgaGuhR5wcFTdP0NU3K4LVB2m9anCjQZGG" \
            -H "X-Parse-REST-API-Key: CVx8kjx38fqREH3VnNPO9WWqTO4X91hVU9LPC6JD" \
    https://parseapi.back4app.com/classes/Post/Mmh8l9gjCk*/

}