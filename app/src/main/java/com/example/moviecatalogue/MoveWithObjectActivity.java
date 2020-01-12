package com.example.moviecatalogue;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MoveWithObjectActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TextView tvObjectTitle = findViewById(R.id.tv_object_title);
        TextView tvObjectSynopsis = findViewById(R.id.tv_object_synopsis);
        ImageView tvObjectPhoto = findViewById(R.id.tv_object_photo);

        tvObjectTitle.setText(movie.getTitle());
        tvObjectSynopsis.setText(movie.getSysnopsis());
        tvObjectPhoto.setImageResource(movie.getPhoto());


    }
}
