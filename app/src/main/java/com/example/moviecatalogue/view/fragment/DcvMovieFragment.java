package com.example.moviecatalogue.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.view.activity.DetailActivity;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.ItemAdapter;
import com.example.moviecatalogue.model.Item;
import com.example.moviecatalogue.api.MainViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DcvMovieFragment extends Fragment {
    private RecyclerView rvMovies;
    private ArrayList<Item> list = new ArrayList<>();
    private ProgressBar progressBar;
    private static ItemAdapter listMovieAdapter;


    public DcvMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_list, container, false);
        rvMovies = view.findViewById(R.id.discover_recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        showRecyclerList();
        return view;
    }

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        listMovieAdapter = new ItemAdapter(list);
        listMovieAdapter.notifyDataSetChanged();
        rvMovies.setAdapter(listMovieAdapter);


        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setMovie();
        showLoading(true);

        listMovieAdapter.setOnItemClickCallBack(new ItemAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Item data) {
                showSelectedMovie(data);
            }
        });
        if (getActivity() != null) {
            mainViewModel.getItem().observe(getActivity(), new Observer<ArrayList<Item>>() {
                @Override
                public void onChanged(ArrayList<Item> items) {
                    if (items != null) {
                        listMovieAdapter.setData(items);
                        showLoading(false);
                    }
                }
            });
        }
    }

    private void showSelectedMovie(Item movie) {
        Intent moveWithObjectActivity = new Intent(getContext(), DetailActivity.class);
        moveWithObjectActivity.putExtra(DetailActivity.EXTRA_MOVIE, (Parcelable) movie);
        startActivity(moveWithObjectActivity);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
