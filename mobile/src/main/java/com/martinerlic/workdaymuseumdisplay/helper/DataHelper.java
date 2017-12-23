package com.martinerlic.workdaymuseumdisplay.helper;

import android.util.Log;
import android.widget.Toast;

import com.martinerlic.workdaymuseumdisplay.activity.MainActivity;
import com.martinerlic.workdaymuseumdisplay.adapter.CardAdapter;
import com.martinerlic.workdaymuseumdisplay.model.MediaImage;
import com.martinerlic.workdaymuseumdisplay.model.MediaResponse;
import com.martinerlic.workdaymuseumdisplay.service.CausticRetrofitService;
import com.martinerlic.workdaymuseumdisplay.service.ServiceFactory;

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
        CausticRetrofitService service = ServiceFactory.createRetrofitService(CausticRetrofitService.class, CausticRetrofitService.SERVICE_ENDPOINT);
        service.getMedia()
                .doOnNext(data -> cacheData(data, db, mCardAdapter))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MediaResponse>() {
                    @Override
                    public final void onCompleted() {

                        Log.e("CausticRetrofitService", "Caustic Request Completed!");

                        /* Cancel all progress indicators after data retrieval complete */
                        mainActivity.setRefreshingFalse();

                        Toast.makeText(mainActivity.getApplicationContext(), "Download complete.", Toast.LENGTH_SHORT).show();

                        // TODO: Add media to local data store and then display them one-by-one in real-time
                        mCardAdapter.addData(db.getAllMediaImages()); // Add all media images to card views
                        mCardAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        /* Cancel all progress indicators on data retrieval error */
                        mainActivity.setRefreshingFalse();

                        Toast.makeText(mainActivity.getApplicationContext(), "Cannot retrieve data. Please try again later.", Toast.LENGTH_SHORT).show();
                        Log.e("CausticRetrofitService", e.getMessage());
                    }

                    @Override
                    public final void onNext(MediaResponse mediaResponse) {
                        if (mediaResponse != null) {

                            mCardAdapter.addData(db.getAllMediaImages());
                            Log.d(mainActivity.getClass().toString(), "Added to local database: " + db.getAllMediaImages());
                            mCardAdapter.notifyDataSetChanged();

                        } else {
                            Log.e("CausticRetrofitService", "Object returned is null.");
                        }

                    }

                });
    }

    private void cacheData(MediaResponse mediaResponse, SQLiteHelper db, CardAdapter mCardAdapter) {
        if (mediaResponse != null) {

            Log.e("CausticRetrofitService", "Returned objects: " + mediaResponse.getResults());

            for (String mediaId : mediaResponse.getResults()) {
                Log.e("CausticRetrofitService", mediaId);
            }

            List<String> mediaIds = mediaResponse.getResults();
            Log.d(mainActivity.getClass().toString(), "All Media IDs: " + mediaIds);

            if (mediaIds.isEmpty()) {
                Toast.makeText(mainActivity.getApplicationContext(), "Cannot retrieve data. Please try again later.", Toast.LENGTH_SHORT).show();
            }

            mCardAdapter.clear();
            mCardAdapter.notifyDataSetChanged();

            /* Store objects from remote web service to local database */
            for (String mediaId : mediaIds) {
                Log.d(mainActivity.getClass().toString(), "Media Id: " + mediaId);

                MediaImage newMediaImage = new MediaImage();
                newMediaImage.setTitle(mediaId);

                db.addMediaImage(newMediaImage); // Add media image to local database
                Log.d(mainActivity.getClass().toString(), "Added to local database: " + newMediaImage);
            }

        } else {
            Log.e("CausticRetrofitService", "Object returned is null.");
        }

    }
}