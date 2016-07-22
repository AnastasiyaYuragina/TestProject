package com.anastasiyayuragina.intentfilter;

import android.app.Activity;
import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by anastasiyayuragina on 7/5/16.
 */
public class ActivityTime extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        TextView textView = (TextView) findViewById(R.id.tvTime);
        textView.setText(time);
    }
}
