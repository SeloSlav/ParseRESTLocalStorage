package com.martinerlic.workdaymuseumdisplay.service;

/**
 * Created by mnxe on 8/14/2017.
 */

import com.martinerlic.workdaymuseumdisplay.model.MediaResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface CausticRetrofitService {

    String SERVICE_ENDPOINT = "https://caustic-rest-service.herokuapp.com";

    /* ----- CURL Example ----- */

    /*curl -i -X GET \
            https://caustic-rest-service.herokuapp.com/media*/

    /* ----- API Requests ----- */

    /* Get Health Check */
    @GET("/healthcheckList")
    Observable<MediaResponse> getHealthCheck();

    /* Get Media */
    @GET("/media")
    Observable<MediaResponse> getMedia();

    /* Get a Media By Id */
    @GET("/media/_id}")
    Observable<MediaResponse> getMediaById(@Path("_id") long id);

}