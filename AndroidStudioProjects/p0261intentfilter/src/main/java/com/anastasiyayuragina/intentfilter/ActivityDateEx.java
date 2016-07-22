package com.anastasiyayuragina.intentfilter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anastasiyayuragina on 7/5/16.
 */
public class ActivityDateEx extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        TextView textViewDate = (TextView) findViewById(R.id.tvDate);
        textViewDate.setText(date);
    }
}
