package com.anastasiyayuragina.getintentaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anastasiyayuragina on 7/5/16.
 */
public class Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        Intent intent = getIntent();
        String action = intent.getAction();
        String format = "";
        String textInfo = "";

        if(action.equals("intent.action.showtime")) {
            format = "HH:mm:ss";
            textInfo = "Time: ";
        } else if (action.equals("intent.action.showdate")) {
            format = "dd.MM.yyyy";
            textInfo = "Date: ";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String datetime = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        TextView textViewDate = (TextView) findViewById(R.id.tvInfo);
        textViewDate.setText(textInfo + datetime);
    }
}
