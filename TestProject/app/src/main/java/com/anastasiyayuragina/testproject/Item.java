package com.anastasiyayuragina.testproject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * Created by anastasiyayuragina on 7/27/16.
 */
@JsonDeserialize (using = ItemDeserializer.class)
public class Item {
    PageInfo pageInfo;
    List<Country> countryList;
}
