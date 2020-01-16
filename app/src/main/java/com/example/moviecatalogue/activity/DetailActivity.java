package com.example.moviecatalogue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.data.Item;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Item movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TextView tvObjectTitle = findViewById(R.id.tv_object_title);
        TextView tvObjectSynopsis = findViewById(R.id.tv_object_synopsis);
        ImageView tvObjectPhoto = findViewById(R.id.tv_object_photo);
        progressBar = findViewById(R.id.progressBar);

        showLoading(true);
        if (movie != null) {
            tvObjectTitle.setText(movie.getTitle());
            tvObjectSynopsis.setText(movie.getSysnopsis());
            Glide.with(this)
                    .load(movie.getPhoto())
                    .into(tvObjectPhoto);
            showLoading(false);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
