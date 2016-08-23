package com.anastasiyayuragina.testproject.screen.country_list;

import android.support.annotation.Nullable;
import android.util.Log;
import com.anastasiyayuragina.testproject.ItemCountry;

/**
 * Created by anastasiyayuragina on 8/2/16.
 */
public class CountriesPresenter implements CountriesMvp.Presenter, CountriesMvp.Model.OnDataLoaded {

    private static final String TAG = "MyLogs";
    private CountriesMvp.Model model;
    @Nullable CountriesMvp.View view;
    private ItemCountry itemCountry = null;
    private boolean dataLoaded = false;

    public CountriesPresenter(CountriesMvp.Model model, CountriesMvp.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadData() {
        if (itemCountry == null) {
            model.loadData(1, this);
        } else {
            view.showLoadMore();
            int page = itemCountry.getPageInfo().getPage() + 1;
            if (page <= itemCountry.getPageInfo().getPages()){
                dataLoaded = false;
                model.loadData(page, this);
                Log.d(TAG, "loadData2: " + page);
            }
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onDataLoaded(ItemCountry itemCountry) {
        this.itemCountry = itemCountry;

        if(view != null){
            view.setData(itemCountry.getCountryList());
            Log.d(TAG, "onDataLoaded: true");
            dataLoaded = true;
        }
    }

    public boolean isDataLoaded() {
        return dataLoaded;
    }
}
