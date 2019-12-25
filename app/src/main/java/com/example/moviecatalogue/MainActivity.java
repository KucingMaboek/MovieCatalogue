package com.example.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        Movie movie = new Movie();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = new Movie();
                movie.setPhoto(dataPhoto.getResourceId(position, -1));
                movie.setTitle(dataTitle[position]);
                movie.setSysnopsis(dataSynopsis[position]);
//                Toast.makeText(MainActivity.this,Integer.toString(movie.getPhoto()), Toast.LENGTH_SHORT).show();
                Intent moveWithObjectActivity = new Intent(MainActivity.this,MoveWithObjectActivity.class);
                moveWithObjectActivity.putExtra(MoveWithObjectActivity.EXTRA_MOVIE, movie);
                startActivity(moveWithObjectActivity);
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
