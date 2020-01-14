package com.example.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String photo;
    private String title;
    private String sysnopsis;

    private Item(Parcel in) {
        photo = in.readString();
        title = in.readString();
        sysnopsis = in.readString();
    }

    Item() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getSysnopsis() {
        return sysnopsis;
    }

    void setSysnopsis(String sysnopsis) {
        this.sysnopsis = sysnopsis;
    }
}
