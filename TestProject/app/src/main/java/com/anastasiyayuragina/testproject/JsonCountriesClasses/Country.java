
package com.anastasiyayuragina.testproject.jsonCountriesClasses;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "iso2Code",
    "name",
    "region",
    "adminregion",
    "incomeLevel",
    "lendingType",
    "capitalCity",
    "longitude",
    "latitude"
})
public class Country {

    @JsonProperty("id")
    private String id;
    @JsonProperty("iso2Code")
    private String iso2Code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("region")
    private Region region;
    @JsonProperty("adminregion")
    private Adminregion adminregion;
    @JsonProperty("incomeLevel")
    private IncomeLevel incomeLevel;
    @JsonProperty("lendingType")
    private LendingType lendingType;
    @JsonProperty("capitalCity")
    private String capitalCity;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("latitude")
    private String latitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    private String comment;

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The iso2Code
     */
    @JsonProperty("iso2Code")
    public String getIso2Code() {
        return iso2Code;
    }

    /**
     * 
     * @param iso2Code
     *     The iso2Code
     */
    @JsonProperty("iso2Code")
    public void setIso2Code(String iso2Code) {
        this.iso2Code = iso2Code;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The region
     */
    @JsonProperty("region")
    public Region getRegion() {
        return region;
    }

    /**
     * 
     * @param region
     *     The region
     */
    @JsonProperty("region")
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * 
     * @return
     *     The adminregion
     */
    @JsonProperty("adminregion")
    public Adminregion getAdminregion() {
        return adminregion;
    }

    /**
     * 
     * @param adminregion
     *     The adminregion
     */
    @JsonProperty("adminregion")
    public void setAdminregion(Adminregion adminregion) {
        this.adminregion = adminregion;
    }

    /**
     * 
     * @return
     *     The incomeLevel
     */
    @JsonProperty("incomeLevel")
    public IncomeLevel getIncomeLevel() {
        return incomeLevel;
    }

    /**
     * 
     * @param incomeLevel
     *     The incomeLevel
     */
    @JsonProperty("incomeLevel")
    public void setIncomeLevel(IncomeLevel incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    /**
     * 
     * @return
     *     The lendingType
     */
    @JsonProperty("lendingType")
    public LendingType getLendingType() {
        return lendingType;
    }

    /**
     * 
     * @param lendingType
     *     The lendingType
     */
    @JsonProperty("lendingType")
    public void setLendingType(LendingType lendingType) {
        this.lendingType = lendingType;
    }

    /**
     * 
     * @return
     *     The capitalCity
     */
    @JsonProperty("capitalCity")
    public String getCapitalCity() {
        return capitalCity;
    }

    /**
     * 
     * @param capitalCity
     *     The capitalCity
     */
    @JsonProperty("capitalCity")
    public void setCapitalCity(String capitalCity) {
        this.capitalCity = capitalCity;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
