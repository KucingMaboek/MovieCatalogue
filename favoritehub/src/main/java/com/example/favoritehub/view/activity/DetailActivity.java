package com.example.favoritehub.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.favoritehub.R;
import com.example.favoritehub.database.AppDatabase;
import com.example.favoritehub.model.Item;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private AppDatabase db;
    ProgressBar progressBar;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "itemdb").allowMainThreadQueries().build();


        final Item item = getIntent().getParcelableExtra(EXTRA_MOVIE);
        try {
            String titles = db.itemDAO().favoriteChecker(item.getTitle()).get(0).getTitle();
            isFavorite = true;
        } catch (Exception ignored) {
            isFavorite = false;
        }

        TextView tvObjectTitle = findViewById(R.id.tv_object_title);
        TextView tvObjectSynopsis = findViewById(R.id.tv_object_synopsis);
        ImageView tvObjectPhoto = findViewById(R.id.tv_object_photo);
        Button btnFavorite = findViewById(R.id.btn_favorite);
        progressBar = findViewById(R.id.progressBar);

        if (isFavorite) {
            btnFavorite.setText(R.string.delete_from_favorite);
        } else {
            btnFavorite.setText(R.string.add_to_favorite);
        }

        showLoading(true);
        if (item != null) {
            tvObjectTitle.setText(item.getTitle());
            tvObjectSynopsis.setText(item.getSysnopsis());
            Glide.with(this)
                    .load(item.getPhoto())
                    .into(tvObjectPhoto);
            showLoading(false);
        }

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    deleteData(item);
                    refreshActivity();
                } else {
                    insertData(item);
                    refreshActivity();
                }
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void insertData(final Item item) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return db.itemDAO().insertItem(item);
            }
        }.execute();
    }

    private void deleteData(Item item) {
        db.itemDAO().deleteItem(item);
    }

    private void refreshActivity() {
        Intent refresh = getIntent();
        startActivity(refresh);
        finish();
    }
}
