package com.anastasiyayuragina.testproject;

import com.anastasiyayuragina.testproject.JsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.JsonCountriesClasses.PageInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * Created by anastasiyayuragina on 7/27/16.
 */
@JsonDeserialize (using = ItemDeserializer.class)
public class Item {
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    PageInfo pageInfo;
    List<Country> countryList;
}
