package com.example.favoritehub.database;

import android.database.Cursor;

public interface LoadCallback {
    void postExecute(Cursor cursor);
}