package com.anastasiyayuragina.testproject;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by anastasiyayuragina on 8/1/16.
 *
 */
public class MySingleton {
    private static MySingleton instance;
    private Retrofit retrofit;

    public static void initInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
    }

    public static MySingleton getInstance() {
        return instance;
    }

    private MySingleton() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.worldbank.org/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


}
