package com.anastasiyayuragina.layoutinflaterlist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] nameArray = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    String[] positionArray = { "Программер", "Бухгалтер", "Программер", "Программер", "Бухгалтер", "Директор", "Программер", "Охранник" };
    int salaryArray[] = { 13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000 };
    int[] colors = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors[0] = Color.parseColor("#9966CC");
        colors[1] = Color.parseColor("#336699");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater layoutInflater = getLayoutInflater();

        for (int i = 0; i < nameArray.length; i++) {
            Log.d("myLogs", "onCreate: ");
            View item = layoutInflater.inflate(R.layout.item, linearLayout, false);
            TextView name = (TextView) item.findViewById(R.id.tvName);
            name.setText(nameArray[i]);
            TextView position = (TextView) item.findViewById(R.id.tvPosition);
            position.setText("Должность: " + positionArray[i]);
            TextView salary = (TextView) item.findViewById(R.id.tvSalary);
            salary.setText("Оклад: " + String.valueOf(salaryArray[i]));
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linearLayout.addView(item);
        }

    }
}
