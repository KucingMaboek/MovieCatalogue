package com.example.favoritehub.model;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Item> mapCursorToArrayList(Cursor itemCursor){
        ArrayList<Item> items = new ArrayList<>();

        while (itemCursor.moveToNext()){
            items.add(new Item(itemCursor));
        }
        return items;
    }
}
