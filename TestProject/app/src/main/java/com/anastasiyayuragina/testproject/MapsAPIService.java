package com.anastasiyayuragina.testproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by anastasiyayuragina on 8/10/16.
 *
 */
public interface MapsAPIService {
    @GET("rest/v1/name/{country}")
    Call<ItemForMap> loadItem(@Path("country") String country);
}
