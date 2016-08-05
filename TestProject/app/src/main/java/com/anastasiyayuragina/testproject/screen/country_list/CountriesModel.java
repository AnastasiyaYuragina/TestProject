package com.anastasiyayuragina.testproject.screen.country_list;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.anastasiyayuragina.testproject.CountriesAPIService;
import com.anastasiyayuragina.testproject.Item;
import com.anastasiyayuragina.testproject.MySingleton;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anastasiyayuragina on 8/2/16.
 */
public class CountriesModel implements CountriesMvp.Model {

    String TAG = "MyLog";

    @Override
    public void loadData(int page, final OnDataLoaded listener) {
        MySingleton ms = MySingleton.getInstance();
        CountriesAPIService service = ms.getRetrofit().create(CountriesAPIService.class);

        Call<Item> itemCall = service.loadItem(pageParam(String.valueOf(page)));
        itemCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                listener.onDataLoaded(item);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private Map<String, String> pageParam(String page) {
        Map<String, String> urlParams = new ArrayMap<>();
        urlParams.put("per_page", "10");
        urlParams.put("format", "json");
        urlParams.put("page", page);

        return urlParams;
    }
}
