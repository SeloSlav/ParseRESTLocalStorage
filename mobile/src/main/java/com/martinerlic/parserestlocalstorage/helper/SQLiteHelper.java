package com.martinerlic.parserestlocalstorage.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.martinerlic.parserestlocalstorage.model.Post;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by marti on 2017-12-21.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    /* Database Version */
    private static final int DATABASE_VERSION = 1;

    /* Database Name */
    private static final String DATABASE_NAME = "MediaImageDB";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create mediaImage table
        String CREATE_MEDIAIMAGE_TABLE = "CREATE TABLE mediaImages ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT )";

        // Create mediaImages table
        db.execSQL(CREATE_MEDIAIMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older media images table if exists
        db.execSQL("DROP TABLE IF EXISTS mediaImages");

        // Create new media images table
        this.onCreate(db);
    }

    // Media images Table Name
    private static final String TABLE_MEDIAIMAGES = "mediaImages";

    // Media Images Table Columns Names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";

    private static final String[] COLUMNS = {KEY_ID, KEY_TITLE};

    /* ----- Initial CRUD Operations ----- */

    /* Add new media file to local storage */
    public void addPost(Post post) {

        Log.d("addPost", post.getText());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, post.getText()); // Get text

        db.insert(TABLE_MEDIAIMAGES,
                null,
                values);

        db.close();
    }

    /* Retrieve list of media files from local storage */
    public List<Post> getAllPosts() {
        List<Post> posts = new LinkedList<>();

        String query = "SELECT  * FROM " + TABLE_MEDIAIMAGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Post post;
        if (cursor.moveToFirst()) {
            do {
                post = new Post();
                post.set_id(Integer.parseInt(cursor.getString(0)));

                // Add post to posts
                posts.add(post);
            } while (cursor.moveToNext());
        }

        Log.d("getAllPosts()", posts.toString());

        // return posts
        return posts;
    }

}
