package com.example.favoritehub.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.favoritehub.R;
import com.example.favoritehub.view.fragment.FavMovieFragment;
import com.example.favoritehub.view.fragment.FavSerialFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;
    private final String menu;

    public SectionPagerAdapter(Context context, FragmentManager fm, String menu) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        this.menu = menu;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1,
            R.string.tab_text_2
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (menu.equalsIgnoreCase("favorite")) {
            switch (position) {
                case 0:
                    fragment = new FavMovieFragment();
                    break;
                case 1:
                    fragment = new FavSerialFragment();
                    break;
            }
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
