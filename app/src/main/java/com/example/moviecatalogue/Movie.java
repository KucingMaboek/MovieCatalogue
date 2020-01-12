package com.example.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String photo;
    private String title;
    private String sysnopsis;

    private Movie(Parcel in) {
        photo = in.readString();
        title = in.readString();
        sysnopsis = in.readString();
    }

    Movie() {
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
