package com.anastasiyayuragina.layoutinflater;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater layoutInflater = getLayoutInflater();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linLayout);
        View view1 = layoutInflater.inflate(R.layout.text, linearLayout, true);
        LayoutParams layoutParams1 = view1.getLayoutParams();

        Log.d(TAG, "Class of view1: " + view1.getClass().toString());
        Log.d(TAG, "Class of layoutParams of view1: " + layoutParams1.getClass().toString());
//        Log.d(TAG, "Text of view1: " + ((TextView) view1).getText());

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = layoutInflater.inflate(R.layout.text, relativeLayout, true);
        LayoutParams layoutParams2 = view2.getLayoutParams();

        Log.d(TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(TAG, "Class of layoutParams of view2: " + layoutParams2.getClass().toString());
//        Log.d(TAG, "Text of view2: " + ((TextView) view2).getText());
    }
}
