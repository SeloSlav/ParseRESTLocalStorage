package com.martinerlic.parserestlocalstorage.helper;

import android.util.Log;
import android.widget.Toast;

import com.martinerlic.parserestlocalstorage.activity.MainActivity;
import com.martinerlic.parserestlocalstorage.adapter.CardAdapter;
import com.martinerlic.parserestlocalstorage.model.Post;
import com.martinerlic.parserestlocalstorage.model.PostResponse;
import com.martinerlic.parserestlocalstorage.service.ParseRetrofitService;
import com.martinerlic.parserestlocalstorage.service.ServiceFactory;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataHelper {
    private final MainActivity mainActivity;

    public DataHelper(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void retrieveData(final CardAdapter mCardAdapter, SQLiteHelper db) {
        ParseRetrofitService service = ServiceFactory.createRetrofitService(ParseRetrofitService.class, ParseRetrofitService.SERVICE_ENDPOINT);
        service.getPosts()
                .doOnNext(data -> cacheData(data, db, mCardAdapter))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PostResponse>() {
                    @Override
                    public final void onCompleted() {

                        Log.e("ParseRetrofitService", "Caustic Request Completed!");

                        /* Cancel all progress indicators after data retrieval complete */
                        mainActivity.setRefreshingFalse();

                        Toast.makeText(mainActivity.getApplicationContext(), "Download complete.", Toast.LENGTH_SHORT).show();

                        // TODO: Add media to local data store and then display them one-by-one in real-time
                        mCardAdapter.addData(db.getAllPosts()); // Add all media images to card views
                        mCardAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        /* Cancel all progress indicators on data retrieval error */
                        mainActivity.setRefreshingFalse();

                        Toast.makeText(mainActivity.getApplicationContext(), "Cannot retrieve data. Please try again later.", Toast.LENGTH_SHORT).show();
                        Log.e("ParseRetrofitService", e.getMessage());
                    }

                    @Override
                    public final void onNext(PostResponse postResponse) {
                        if (postResponse != null) {

                            mCardAdapter.addData(db.getAllPosts());
                            Log.d(mainActivity.getClass().toString(), "Added to local database: " + db.getAllPosts());
                            mCardAdapter.notifyDataSetChanged();

                        } else {
                            Log.e("ParseRetrofitService", "Object returned is null.");
                        }

                    }

                });
    }

    private void cacheData(PostResponse mediaResponse, SQLiteHelper db, CardAdapter mCardAdapter) {
        if (mediaResponse != null) {

            Log.e("ParseRetrofitService", "Returned objects: " + mediaResponse.getResults());

            for (Post mediaId : mediaResponse.getResults()) {
                Log.e("ParseRetrofitService", mediaId.getObjectId());
            }

            List<Post> posts = mediaResponse.getResults();
            Log.d(mainActivity.getClass().toString(), "All Media IDs: " + posts);

            if (posts.isEmpty()) {
                Toast.makeText(mainActivity.getApplicationContext(), "Cannot retrieve data. Please try again later.", Toast.LENGTH_SHORT).show();
            }

            mCardAdapter.clear();
            mCardAdapter.notifyDataSetChanged();

            /* Store objects from remote web service to local database */
            for (Post post : posts) {
                Log.d(mainActivity.getClass().toString(), "Post: " + post);

                Post newMediaImage = new Post();
                newMediaImage.setText(post.getText());

                db.addPost(post); // Add media image to local database
                Log.d(mainActivity.getClass().toString(), "Added to local database: " + newMediaImage);
            }

        } else {
            Log.e("ParseRetrofitService", "Object returned is null.");
        }

    }
}