package com.anastasiyayuragina.testproject.screen.country_list;

import com.anastasiyayuragina.testproject.ItemCountry;
import com.anastasiyayuragina.testproject.jsonCountriesClasses.Country;
import java.util.List;

/**
 * Created by anastasiyayuragina on 8/2/16.
 *
 */
public interface CountriesMvp {
    interface Presenter{
        void loadData();
        void onDestroy();
        boolean isDataLoaded();
    }
    interface View{
        void setData(List<Country> countryList);
        void showLoadMore();
    }
    interface Model{
        interface OnDataLoaded{
            void onDataLoaded(ItemCountry itemCountry);
        }
        void loadData(int page, OnDataLoaded listener);
    }
}
