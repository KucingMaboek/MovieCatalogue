package com.example.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int photo;
    private String title;
    private String sysnopsis;

    protected Movie(Parcel in) {
        photo = in.readInt();
        title = in.readString();
        sysnopsis = in.readString();
    }

    public Movie() {
        this.photo = photo;
        this.title = title;
        this.sysnopsis = sysnopsis;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeString(title);
        dest.writeString(sysnopsis);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
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
