package com.example.moviecatalogue.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moviecatalogue.notification.AlarmReceiver;
import com.example.moviecatalogue.R;

public class SettingFragment extends Fragment {
    private Button lang_setting;
    private Switch notif_daily;
    private Switch notif_news;
    private static String REMINDER;
    private static String NEWS;
    private boolean notif_daily_check = false;
    private boolean notif_news_check = false;
    private static String SETTING_PREFS = "";
    private AlarmReceiver alarmReceiver;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alarmReceiver = new AlarmReceiver();
        lang_setting = view.findViewById(R.id.btn_languange_setting);
        lang_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        });
        notif_daily = view.findViewById(R.id.switch_daily);
        notif_news = view.findViewById(R.id.switch_news);
        loadPref();
        notif_daily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean reminderIsChecked) {
                notif_daily_check = reminderIsChecked;
                setPref();
                if (reminderIsChecked){
                    alarmReceiver.setAlarm(getActivity(), AlarmReceiver.ID_REMINDER, 7);
                } else {
                    alarmReceiver.cancelAlarm(getActivity(), AlarmReceiver.ID_REMINDER);
                }
            }
        });
        notif_news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean newsIsChecked) {
                notif_news_check = newsIsChecked;
                setPref();
                if (newsIsChecked){
                    alarmReceiver.setAlarm(getActivity(), AlarmReceiver.ID_NEWS, 8);
                } else {
                    alarmReceiver.cancelAlarm(getActivity(), AlarmReceiver.ID_NEWS);
                }
            }
        });
    }

    private void setPref(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SETTING_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(REMINDER, notif_daily_check);
        editor.putBoolean(NEWS, notif_news_check);
        editor.apply();
    }

    private void loadPref(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SETTING_PREFS, Context.MODE_PRIVATE);
        notif_daily.setChecked(sharedPreferences.getBoolean(REMINDER, false));
        notif_news.setChecked(sharedPreferences.getBoolean(NEWS, false));
    }
}