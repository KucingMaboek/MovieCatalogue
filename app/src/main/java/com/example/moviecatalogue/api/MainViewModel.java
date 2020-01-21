package com.example.moviecatalogue.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.BuildConfig;
import com.example.moviecatalogue.model.Item;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private String API_KEY = BuildConfig.TMDB_API_KEY;
    private String currentLanguange = Locale.getDefault().getDisplayLanguage();
    private MutableLiveData<ArrayList<Item>> listItem = new MutableLiveData<>();

    public void setMovie() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Item> listMovie = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";

        if (currentLanguange.equalsIgnoreCase("INDONESIA")) {
            url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=id-ID";
        } else if (currentLanguange.equalsIgnoreCase("ENGLISH")) {
            url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";

        }

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Item item = new Item();

                        item.setId(movie.getString("id"));
                        item.setPhoto("http://image.tmdb.org/t/p/w185" + movie.getString("poster_path"));
                        item.setTitle(movie.getString("title"));
                        item.setSysnopsis(movie.getString("overview"));
                        item.setCategory("movie");

                        listMovie.add(item);
                    }
                    listItem.postValue(listMovie);
                } catch (Exception e) {
                    Log.d("Exception", "ERROR: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", "ERROR: " + error.getMessage());
            }
        });
    }

    public void setTvShow() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Item> listTvShow = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        if (currentLanguange.equalsIgnoreCase("INDONESIA")) {
            url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=id-ID";
        } else if (currentLanguange.equalsIgnoreCase("ENGLISH")) {
            url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        }

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject serial = list.getJSONObject(i);
                        Item item = new Item();

                        item.setId(serial.getString("id"));
                        item.setPhoto("http://image.tmdb.org/t/p/w185" + serial.getString("poster_path"));
                        item.setTitle(serial.getString("name"));
                        item.setSysnopsis(serial.getString("overview"));
                        item.setCategory("tvshow");
                        listTvShow.add(item);
                    }
                    listItem.postValue(listTvShow);
                } catch (Exception e) {
                    Log.d("Exception", "ERROR: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", "ERROR: " + error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Item>> getItem() {
        return listItem;
    }
}
