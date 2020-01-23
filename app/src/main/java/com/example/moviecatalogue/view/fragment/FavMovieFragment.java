package com.example.moviecatalogue.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.ItemAdapter;
import com.example.moviecatalogue.database.AppDatabase;
import com.example.moviecatalogue.model.Item;
import com.example.moviecatalogue.view.activity.DetailActivity;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment {
    private RecyclerView recyclerView;

    public FavMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        recyclerView = view.findViewById(R.id.favorite_recyclerView);
        showRecyclerList();
        return view;
    }

    private void showRecyclerList() {
        AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "itemdb").allowMainThreadQueries().build();

        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ArrayList<Item> list = new ArrayList<>(Arrays.asList(db.itemDAO().selectAllMovie()));
        ItemAdapter adapter = new ItemAdapter(list);
        adapter.setOnItemClickCallBack(new ItemAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Item data) {
                showSelectedMovie(data);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void showSelectedMovie(Item movie) {
        Intent moveWithObjectActivity = new Intent(getContext(), DetailActivity.class);
        moveWithObjectActivity.putExtra(DetailActivity.EXTRA_MOVIE, (Parcelable) movie);
        startActivity(moveWithObjectActivity);
    }
}
