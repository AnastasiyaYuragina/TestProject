package com.anastasiyayuragina.testproject.screen.map;

import com.anastasiyayuragina.testproject.ItemForMap;

/**
 * Created by anastasiyayuragina on 8/10/16.
 *
 */
public interface MapMvp {
    interface PresenterMap {
        void loadData();
        void setCountryName(String countryName);
    }

    interface ViewMap {
        void setMapMarker(ItemForMap itemForMap);
    }

    interface ModelMap {
        interface OnDataLoadedMap {
            void onDataLoadedMap(ItemForMap itemForMap);
        }
        void loadData(String countryName, OnDataLoadedMap listener);
    }
}
