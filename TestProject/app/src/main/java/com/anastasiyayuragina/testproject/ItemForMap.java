package com.anastasiyayuragina.testproject;

import com.anastasiyayuragina.testproject.jsonInfoForMapClasses.InfoForMap;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by anastasiyayuragina on 8/10/16.
 *
 */
@JsonDeserialize(using = ItemForMapDeserializer.class)
public class ItemForMap {

    private InfoForMap infoForMap;

    public InfoForMap getInfoForMap() {
        return infoForMap;
    }

    public void setInfoForMap(InfoForMap infoForMap) {
        this.infoForMap = infoForMap;
    }
}
