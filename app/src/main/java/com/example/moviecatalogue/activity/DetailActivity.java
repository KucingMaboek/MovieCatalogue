package com.example.moviecatalogue.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.AppDatabase;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.Item;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private AppDatabase db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "itemdb").build();

        final Item item = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TextView tvObjectTitle = findViewById(R.id.tv_object_title);
        TextView tvObjectSynopsis = findViewById(R.id.tv_object_synopsis);
        ImageView tvObjectPhoto = findViewById(R.id.tv_object_photo);
        Button btnFavorite = findViewById(R.id.btn_favorite);
        progressBar = findViewById(R.id.progressBar);

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
                insertData(item);
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
    private void insertData(final Item item){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.itemDAO().insertItem(item);
                return status;
            }
            @Override
            protected void onPostExecute(Long status){
                Toast.makeText(DetailActivity.this, "status row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
