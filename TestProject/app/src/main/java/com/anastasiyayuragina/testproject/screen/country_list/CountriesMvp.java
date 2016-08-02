package com.anastasiyayuragina.testproject.screen.country_list;

import com.anastasiyayuragina.testproject.Item;
import com.anastasiyayuragina.testproject.JsonCountriesClasses.Country;
import java.util.List;

/**
 * Created by anastasiyayuragina on 8/2/16.
 */
public interface CountriesMvp {
    interface Presenter{
        void loadData();
        void onDestroy();
    }
    interface View{
        void setData(List<Country> countryList);
    }
    interface Model{
        interface OnDataLoaded{
            void onDataLoaded(Item item);
        }
        void loadData(int page, OnDataLoaded listener);
    }
}
