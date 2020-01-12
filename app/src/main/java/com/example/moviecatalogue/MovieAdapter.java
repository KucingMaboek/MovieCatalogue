package com.example.moviecatalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);
        return itemView;

    }
}


class ViewHolder {
    private TextView txtTitle;
    private TextView txtSynopsis;
    private ImageView imgPhoto;

    ViewHolder(View view) {
        txtTitle = view.findViewById(R.id.txt_title);
        txtSynopsis = view.findViewById(R.id.txt_synopsis);
        imgPhoto = view.findViewById(R.id.img_photo);
    }

    void bind(Movie movie) {
        txtTitle.setText(movie.getTitle());
        txtSynopsis.setText(movie.getSysnopsis());
        imgPhoto.setImageResource(movie.getPhoto());
    }
}