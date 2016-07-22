package com.anastasiyayuragina.simplelistchoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "myLogs";

    ListView mainListView;
    String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainListView = (ListView) findViewById(R.id.lvMain);
        mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_multiple_choice);
        mainListView.setAdapter(adapter);

        Button checked = (Button) findViewById(R.id.btnChecked);
        checked.setOnClickListener(this);

        names = getResources().getStringArray(R.array.names);

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "checked: " );
        SparseBooleanArray sparseBooleanArray = mainListView.getCheckedItemPositions();
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            int key = sparseBooleanArray.keyAt(i);
            Log.d(TAG, names[key]);
        }
    }
}
