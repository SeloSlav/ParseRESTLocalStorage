package com.martinerlic.parserestlocalstorage.activity;

/**
 * Created by mnxe (martin erlic) on 8/14/2017.
 */

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.martinerlic.parserestlocalstorage.R;
import com.martinerlic.parserestlocalstorage.adapter.CardAdapter;
import com.martinerlic.parserestlocalstorage.helper.DataHelper;
import com.martinerlic.parserestlocalstorage.helper.SQLiteHelper;

public class MainActivity extends AppCompatActivity {

    private final DataHelper dataHelper = new DataHelper(this);
    private SwipeRefreshLayout swipeRefreshLayout;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Set Main Layout XML */
        setContentView(R.layout.activity_main);

        /* User instruction */
        Toast.makeText(getApplicationContext(), "Please swipe to retrieve remote data.", Toast.LENGTH_SHORT).show();

        /* Remove tool bar */
        // TODO: Is this really the best way to hide the action bar?
        getSupportActionBar().hide();

         /* Set SQLite db */
        SQLiteHelper db = new SQLiteHelper(this);

        /* Setup RecyclerView and CardView */
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final CardAdapter mCardAdapter = new CardAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mCardAdapter);

        /* Add data from local storage to RecyclerView adapter */
        mCardAdapter.addData(db.getAllPosts());
        Log.d(getClass().toString(), "Retrieved from local database: " + db.getAllPosts());

        /* Define SwipeRefreshLayout */
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        /*Set SwipeRefreshListener */
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            dataHelper.retrieveData(mCardAdapter, db);
        });

    }


    public void setRefreshingFalse() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}