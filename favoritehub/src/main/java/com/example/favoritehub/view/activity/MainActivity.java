package com.example.favoritehub.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.favoritehub.R;
import com.example.favoritehub.adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(this, getSupportFragmentManager(), "favorite");
        ViewPager viewPager = findViewById(R.id.favorite_view_pager);
        viewPager.setAdapter(sectionPagerAdapter);
        TabLayout tabs = findViewById(R.id.favorite_tabs);
        tabs.setupWithViewPager(viewPager);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setTitle("Consumer Favorite Hub");
        }
    }
}
