package com.anastasiyayuragina.testjson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyLogs";
    private static final int PAGE_INFO = 0;
    private static final int COUNTRIES_ARRAY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Parse().execute();
    }

    private class Parse extends AsyncTask<Void, Void, String> {

        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... voids) {

            try {
//                URL url = new URL("http://androiddocs.ru/api/friends.json");
                URL url = new URL("http://api.worldbank.org/country?per_page=10&format=json&page=1");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return resultJson;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            Log.d(TAG, s);

//            JSONObject jsonObject = null;
            JSONArray jsonArray = null;
            String secondName = "";

            try {
//                jsonObject = new JSONObject(s);
//                JSONArray friends = jsonObject.getJSONArray("friends");

                jsonArray = new JSONArray(s);
                JSONArray countries = jsonArray.getJSONArray(COUNTRIES_ARRAY);


//                JSONObject secondFriend = friends.getJSONObject(1);
//                secondName = secondFriend.getString("name");
//
                Log.d(TAG, countries.toString());
//                Log.d(TAG, "second name: " + secondName);
//
//                for (int i = 0; i < friends.length(); i++) {
//                    JSONObject friend = friends.getJSONObject(i);
//
//                    Log.d(TAG, "name:" + friend.getString("name"));
//                    Log.d(TAG, "city:" + friend.getString("city"));
//
//                    JSONObject contacts = friend.getJSONObject("contacts");
//
//                    String phone = contacts.getString("mobile");
//                    String email = contacts.getString("email");
//                    String skype = contacts.getString("skype");
//
//                    Log.d(TAG, "phone: " + phone);
//                    Log.d(TAG, "email: " + email);
//                    Log.d(TAG, "skype: " + skype);
//                }

                for (int i = 0; i < countries.length(); i++) {
                    JSONObject country = countries.getJSONObject(i);

                    Log.d(TAG, "id:" + country.getString("id"));
                    Log.d(TAG, "iso2Code:" + country.getString("iso2Code"));
                    Log.d(TAG, "name:" + country.getString("name"));

                    JSONObject region = country.getJSONObject("region");

                    Log.d(TAG, "region id:" + region.getString("id"));
                    Log.d(TAG, "region value:" + region.getString("value"));

                    JSONObject adminregion = country.getJSONObject("adminregion");

                    Log.d(TAG, "adminregion id:" + adminregion.getString("id"));
                    Log.d(TAG, "adminregion value:" + adminregion.getString("value"));

                    JSONObject incomeLevel = country.getJSONObject("incomeLevel");

                    Log.d(TAG, "incomeLevel id:" + incomeLevel.getString("id"));
                    Log.d(TAG, "incomeLevel value:" + incomeLevel.getString("value"));

                    JSONObject lendingType = country.getJSONObject("lendingType");

                    Log.d(TAG, "lendingType id:" + lendingType.getString("id"));
                    Log.d(TAG, "lendingType value:" + lendingType.getString("value"));

                    Log.d(TAG, "capitalCity:" + country.getString("capitalCity"));
                    Log.d(TAG, "longitude:" + country.getString("longitude"));
                    Log.d(TAG, "latitude:" + country.getString("latitude"));

                    Log.d(TAG, "---- // ----");
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
