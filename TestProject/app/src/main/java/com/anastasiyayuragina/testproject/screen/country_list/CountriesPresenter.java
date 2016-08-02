package com.anastasiyayuragina.testproject.screen.country_list;

import android.support.annotation.Nullable;

import com.anastasiyayuragina.testproject.Item;

/**
 * Created by anastasiyayuragina on 8/2/16.
 */
public class CountriesPresenter implements CountriesMvp.Presenter, CountriesMvp.Model.OnDataLoaded {

    private CountriesMvp.Model model;
    @Nullable CountriesMvp.View view;
    private Item item = null;

    public CountriesPresenter(CountriesMvp.Model model, CountriesMvp.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadData() {
        if (item == null) {
            model.loadData(1, this);
        } else {
            model.loadData(item.getPageInfo().getPage(), this);
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
        }
    }
}
