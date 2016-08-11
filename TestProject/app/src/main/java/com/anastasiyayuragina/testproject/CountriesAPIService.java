package com.anastasiyayuragina.testproject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface CountriesAPIService {
    @GET("country")
    Call<ItemCountry> loadItem(@QueryMap Map<String, String> param);
}
