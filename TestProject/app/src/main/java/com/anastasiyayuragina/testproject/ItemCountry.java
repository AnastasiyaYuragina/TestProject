package com.anastasiyayuragina.testproject;

import com.anastasiyayuragina.testproject.jsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.jsonCountriesClasses.PageInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * Created by anastasiya yuragina on 7/27/16.
 *
 */
@JsonDeserialize (using = ItemCountryDeserializer.class)
public class ItemCountry {
    private PageInfo pageInfo;
    private List<Country> countryList;

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
}
