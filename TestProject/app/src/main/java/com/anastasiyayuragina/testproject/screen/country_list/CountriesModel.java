package com.anastasiyayuragina.testproject.screen.country_list;

import android.support.v4.util.ArrayMap;
import com.anastasiyayuragina.testproject.CountriesAPIService;
import com.anastasiyayuragina.testproject.ItemCountry;
import com.anastasiyayuragina.testproject.MySingleton;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anastasiya yuragina on 8/2/16.
 *
 */
public class CountriesModel implements CountriesMvp.Model {

    @Override
    public void loadData(int page, final OnDataLoaded listener) {
        MySingleton ms = MySingleton.getInstance();
        ms.setRetrofit("http://api.worldbank.org/");
        CountriesAPIService service = ms.getRetrofit().create(CountriesAPIService.class);

        Call<ItemCountry> itemCall = service.loadItem(pageParam(String.valueOf(page)));
        itemCall.enqueue(new Callback<ItemCountry>() {
            @Override
            public void onResponse(Call<ItemCountry> call, Response<ItemCountry> response) {
                ItemCountry itemCountry = response.body();
                listener.onDataLoaded(itemCountry);
            }

            @Override
            public void onFailure(Call<ItemCountry> call, Throwable t) {
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
