package com.example.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tItem")
public class Item implements Parcelable, Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    private String id;

    @ColumnInfo(name = "photo")
    private String photo;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "synopsis")
    private String sysnopsis;

    private Item(Parcel in) {
        id = in.readString();
        photo = in.readString();
        title = in.readString();
        sysnopsis = in.readString();
    }

    public Item() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(photo);
        dest.writeString(title);
        dest.writeString(sysnopsis);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSysnopsis() {
        return sysnopsis;
    }

    public void setSysnopsis(String sysnopsis) {
        this.sysnopsis = sysnopsis;
    }
}
