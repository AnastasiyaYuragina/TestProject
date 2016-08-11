package com.anastasiyayuragina.testproject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by anastasiyayuragina on 8/10/16.
 */
public interface MapsAPIService {
    @GET("rest/v1/name/{country}")
    Call<ItemForMap> loadItem(@Path("country") String country);
}
