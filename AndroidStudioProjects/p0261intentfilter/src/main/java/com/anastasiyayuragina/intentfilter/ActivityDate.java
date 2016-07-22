package com.anastasiyayuragina.intentfilter;

import android.app.Activity;
import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by anastasiyayuragina on 7/5/16.
 */
public class ActivityDate extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        TextView textViewDate = (TextView) findViewById(R.id.tvDate);
        textViewDate.setText(date);

    }
}
