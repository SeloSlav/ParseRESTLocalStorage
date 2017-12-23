package com.martinerlic.parserestlocalstorage.application;

import android.app.Application;

import com.martinerlic.parserestlocalstorage.R;
import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created by mnxe on 8/14/2017.
 */

public class GlobalApplication extends Application {

    public GlobalApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getString(R.string.parse_app_id))
                .clientKey(getString(R.string.parse_client_key))
                .server("https://parseapi.back4app.com/")
                .enableLocalDataStore()
                .build()
        );

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        /* Login automatically to test Parse requests */
        ParseUser.logInInBackground("martin", "test123");

    }

}
