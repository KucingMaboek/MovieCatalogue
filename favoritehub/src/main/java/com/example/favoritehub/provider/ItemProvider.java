package com.example.favoritehub.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.room.Room;

import com.example.favoritehub.database.AppDatabase;

public class ItemProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.moviecatalogue";
    private static final String MOVIE_PATH = "movie";
    private static final String TV_PATH = "tv";
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, MOVIE_PATH, 1);
        sUriMatcher.addURI(AUTHORITY, TV_PATH, 2);
    }

    AppDatabase appDatabase;

    @Override
    public boolean onCreate() {
        String DBNAME = "itemdb";
        appDatabase = Room.databaseBuilder(getContext(),
                AppDatabase.class, DBNAME).allowMainThreadQueries().build();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {
            case 1:
                cursor = appDatabase.itemDAO().selectMovieProvider();
                break;
            case 2:
                cursor = appDatabase.itemDAO().selectTvShowProvider();
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
