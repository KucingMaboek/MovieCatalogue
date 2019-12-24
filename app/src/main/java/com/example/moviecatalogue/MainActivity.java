package com.example.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private String[] dataTitle;
    private String[] dataSynopsis;
    private TypedArray dataPhoto;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lv_list);
        movieAdapter = new MovieAdapter(this);
        listView.setAdapter(movieAdapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepare(){
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataSynopsis = getResources().getStringArray(R.array.data_synopsis);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem(){
        movies = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++){
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setSysnopsis(dataSynopsis[i]);
            movies.add(movie);
        }

        movieAdapter.setMovies(movies);
    }
}
