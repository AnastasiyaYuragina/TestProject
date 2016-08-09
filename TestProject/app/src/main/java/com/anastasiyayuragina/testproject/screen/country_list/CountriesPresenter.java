package com.anastasiyayuragina.testproject.screen.country_list;

import android.support.annotation.Nullable;
import android.util.Log;
import com.anastasiyayuragina.testproject.Item;

/**
 * Created by anastasiyayuragina on 8/2/16.
 */
public class CountriesPresenter implements CountriesMvp.Presenter, CountriesMvp.Model.OnDataLoaded {

    private static final String TAG = "MyLogs";
    private CountriesMvp.Model model;
    @Nullable CountriesMvp.View view;
    private Item item = null;
    private boolean dataLoaded = false;

    public CountriesPresenter(CountriesMvp.Model model, CountriesMvp.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadData() {
        if (item == null) {
            model.loadData(1, this);
        } else {
            view.showLoadMore();
            int page = item.getPageInfo().getPage() + 1;
            if (page<= item.getPageInfo().getPages()){
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
    public void onDataLoaded(Item item) {
        this.item = item;

        if(view != null){
            view.setData(item.getCountryList());
            Log.d(TAG, "onDataLoaded: true");
            dataLoaded = true;
        }
    }

    public boolean isDataLoaded() {
        return dataLoaded;
    }
}
