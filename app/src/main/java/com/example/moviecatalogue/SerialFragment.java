package com.example.moviecatalogue;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SerialFragment extends Fragment {
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();

    public SerialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        rvMovies = view.findViewById(R.id.rv_movies);

        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);
        showRecyclerList();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list.addAll(getListMovies());

    }

    private ArrayList<Movie> getListMovies() {
        String[] dataTitle = getResources().getStringArray(R.array.data_title_2);
        String[] dataSynopsis = getResources().getStringArray(R.array.data_synopsis_2);
        String[] dataPhoto = getResources().getStringArray(R.array.data_photo_2);

        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(dataTitle[i]);
            movie.setSysnopsis(dataSynopsis[i]);
            movie.setPhoto(dataPhoto[i]);

            listMovie.add(movie);
        }
        return listMovie;
    }

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(list);
        rvMovies.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickCallBack(new ListMovieAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });
    }

    private void showSelectedMovie(Movie movie) {
        Toast.makeText(getActivity(), movie.getTitle(), Toast.LENGTH_SHORT).show();
        Intent moveWithObjectActivity = new Intent(getContext(), DetailActivity.class);
        moveWithObjectActivity.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(moveWithObjectActivity);
    }

}
