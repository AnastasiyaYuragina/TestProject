package com.anastasiyayuragina.testproject.screen.map;

import android.util.Log;

import com.anastasiyayuragina.testproject.ItemForMap;
import com.anastasiyayuragina.testproject.MapsAPIService;
import com.anastasiyayuragina.testproject.MySingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anastasiyayuragina on 8/10/16.
 */
public class MapModel implements MapMvp.ModelMap {

    private String TAG = "Mylogs";

    @Override
    public void loadData(String countryName, final OnDataLoadedMap listener) {
        MySingleton ms = MySingleton.getInstance();
        ms.setRetrofit("https://restcountries.eu/");
        MapsAPIService service = ms.getRetrofit().create(MapsAPIService.class);

        Call<ItemForMap> itemCall = service.loadItem(countryName);
        itemCall.enqueue(new Callback<ItemForMap>() {
            @Override
            public void onResponse(Call<ItemForMap> call, Response<ItemForMap> response) {
                ItemForMap itemForMap = response.body();
                listener.onDataLoadedMap(itemForMap);
            }

            @Override
            public void onFailure(Call<ItemForMap> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
