package com.anastasiyayuragina.mystopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Stopwatch stopwatch;
    private TextView timeView;
    private ListView circleList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeView = (TextView) findViewById(R.id.time_view);
        circleList = (ListView) findViewById(R.id.circle_list);

        stopwatch = new Stopwatch(timeView, circleList);

    }


    public void onClickStart(View view) {
        stopwatch.startStopwatch();
    }

    public void onClickStop(View view) {
        stopwatch.stopStopwatch();
    }

    public void onClickCircle(View view) {
        itemArrayList.add(stopwatch.circleStopwatch());
        adapter = new ArrayAdapter<>(MainActivity.this, R.layout.item, itemArrayList);
        circleList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onClickClear(View view) {
        stopwatch.clearStopwatch();
        itemArrayList.clear();
        adapter = new ArrayAdapter<>(MainActivity.this, R.layout.item, itemArrayList);
        circleList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
