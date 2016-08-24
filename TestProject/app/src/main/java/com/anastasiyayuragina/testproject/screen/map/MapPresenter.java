package com.anastasiyayuragina.testproject.screen.map;

import com.anastasiyayuragina.testproject.ItemForMap;

/**
 * Created by anastasiyayuragina on 8/10/16.
 *
 */
public class MapPresenter implements MapMvp.PresenterMap, MapMvp.ModelMap.OnDataLoadedMap {
    private MapMvp.ModelMap modelMap;
    private MapMvp.ViewMap viewMap;
    private String countryName;

    public MapPresenter(MapMvp.ModelMap modelMap, MapMvp.ViewMap viewMap) {
        this.modelMap = modelMap;
        this.viewMap = viewMap;
    }

    @Override
    public void loadData() {
        modelMap.loadData(countryName, this);
    }

    @Override
    public void onDataLoadedMap(ItemForMap itemForMap) {

        viewMap.setMapMarker(itemForMap);
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


}
