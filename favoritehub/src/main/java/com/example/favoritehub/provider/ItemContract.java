package com.example.favoritehub.provider;

import android.database.Cursor;
import android.net.Uri;

public class ItemContract {
    private static final String AUTHORITY = "com.example.moviecatalogue";
    private static final String MOVIE_PATH = "movie";
    private static final String TV_PATH = "tv";
    private static final String SCHEMA = "content";

    public ItemContract() {
    }

    public static String getColumnString(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName){
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static final class EntryMovie{
        public static final Uri CONTENT_URI_MOVIE = new Uri.Builder().scheme(SCHEMA)
                .authority(AUTHORITY)
//                .appendPath(MOVIE_PATH)
                .appendPath("movie")
                .build();
    }

    public static final class EntryTV{
        public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme(SCHEMA)
                .authority(AUTHORITY)
                .appendPath(TV_PATH)
                .build();
    }
}
