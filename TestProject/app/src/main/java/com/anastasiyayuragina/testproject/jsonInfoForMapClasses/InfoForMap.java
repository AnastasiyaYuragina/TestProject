
package com.anastasiyayuragina.testproject.jsonInfoForMapClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "name",
    "capital",
    "altSpellings",
    "relevance",
    "region",
    "subregion",
    "translations",
    "population",
    "latlng",
    "demonym",
    "area",
    "gini",
    "timezones",
    "borders",
    "nativeName",
    "callingCodes",
    "topLevelDomain",
    "alpha2Code",
    "alpha3Code",
    "currencies",
    "languages"
})
public class InfoForMap {

    @JsonProperty("name")
    private String name;
    @JsonProperty("capital")
    private String capital;
    @JsonProperty("altSpellings")
    private List<String> altSpellings = new ArrayList<>();
    @JsonProperty("relevance")
    private String relevance;
    @JsonProperty("region")
    private String region;
    @JsonProperty("subregion")
    private String subregion;
    @JsonProperty("translations")
    private Translations translations;
    @JsonProperty("population")
    private Integer population;
    @JsonProperty("latlng")
    private List<Double> latlng = new ArrayList<>();
    @JsonProperty("demonym")
    private String demonym;
    @JsonProperty("area")
    private Double area;
    @JsonProperty("gini")
    private Object gini;
    @JsonProperty("timezones")
    private Object timezones;
    @JsonProperty("borders")
    private List<Object> borders = new ArrayList<>();
    @JsonProperty("nativeName")
    private String nativeName;
    @JsonProperty("callingCodes")
    private List<String> callingCodes = new ArrayList<>();
    @JsonProperty("topLevelDomain")
    private List<String> topLevelDomain = new ArrayList<>();
    @JsonProperty("alpha2Code")
    private String alpha2Code;
    @JsonProperty("alpha3Code")
    private String alpha3Code;
    @JsonProperty("currencies")
    private List<String> currencies = new ArrayList<>();
    @JsonProperty("languages")
    private List<String> languages = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

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
     *     The capital
     */
    @JsonProperty("capital")
    public String getCapital() {
        return capital;
    }

    /**
     * 
     * @param capital
     *     The capital
     */
    @JsonProperty("capital")
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * 
     * @return
     *     The altSpellings
     */
    @JsonProperty("altSpellings")
    public List<String> getAltSpellings() {
        return altSpellings;
    }

    /**
     * 
     * @param altSpellings
     *     The altSpellings
     */
    @JsonProperty("altSpellings")
    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    /**
     * 
     * @return
     *     The relevance
     */
    @JsonProperty("relevance")
    public String getRelevance() {
        return relevance;
    }

    /**
     * 
     * @param relevance
     *     The relevance
     */
    @JsonProperty("relevance")
    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    /**
     * 
     * @return
     *     The region
     */
    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    /**
     * 
     * @param region
     *     The region
     */
    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 
     * @return
     *     The subregion
     */
    @JsonProperty("subregion")
    public String getSubregion() {
        return subregion;
    }

    /**
     * 
     * @param subregion
     *     The subregion
     */
    @JsonProperty("subregion")
    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    /**
     * 
     * @return
     *     The translations
     */
    @JsonProperty("translations")
    public Translations getTranslations() {
        return translations;
    }

    /**
     * 
     * @param translations
     *     The translations
     */
    @JsonProperty("translations")
    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    /**
     * 
     * @return
     *     The population
     */
    @JsonProperty("population")
    public Integer getPopulation() {
        return population;
    }

    /**
     * 
     * @param population
     *     The population
     */
    @JsonProperty("population")
    public void setPopulation(Integer population) {
        this.population = population;
    }

    /**
     * 
     * @return
     *     The latlng
     */
    @JsonProperty("latlng")
    public List<Double> getLatlng() {
        return latlng;
    }

    /**
     * 
     * @param latlng
     *     The latlng
     */
    @JsonProperty("latlng")
    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    /**
     * 
     * @return
     *     The demonym
     */
    @JsonProperty("demonym")
    public String getDemonym() {
        return demonym;
    }

    /**
     * 
     * @param demonym
     *     The demonym
     */
    @JsonProperty("demonym")
    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    /**
     * 
     * @return
     *     The area
     */
    @JsonProperty("area")
    public Double getArea() {
        return area;
    }

    /**
     * 
     * @param area
     *     The area
     */
    @JsonProperty("area")
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * 
     * @return
     *     The gini
     */
    @JsonProperty("gini")
    public Object getGini() {
        return gini;
    }

    /**
     * 
     * @param gini
     *     The gini
     */
    @JsonProperty("gini")
    public void setGini(Object gini) {
        this.gini = gini;
    }

    /**
     * 
     * @return
     *     The timezones
     */
    @JsonProperty("timezones")
    public Object getTimezones() {
        return timezones;
    }

    /**
     * 
     * @param timezones
     *     The timezones
     */
    @JsonProperty("timezones")
    public void setTimezones(Object timezones) {
        this.timezones = timezones;
    }

    /**
     * 
     * @return
     *     The borders
     */
    @JsonProperty("borders")
    public List<Object> getBorders() {
        return borders;
    }

    /**
     * 
     * @param borders
     *     The borders
     */
    @JsonProperty("borders")
    public void setBorders(List<Object> borders) {
        this.borders = borders;
    }

    /**
     * 
     * @return
     *     The nativeName
     */
    @JsonProperty("nativeName")
    public String getNativeName() {
        return nativeName;
    }

    /**
     * 
     * @param nativeName
     *     The nativeName
     */
    @JsonProperty("nativeName")
    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    /**
     * 
     * @return
     *     The callingCodes
     */
    @JsonProperty("callingCodes")
    public List<String> getCallingCodes() {
        return callingCodes;
    }

    /**
     * 
     * @param callingCodes
     *     The callingCodes
     */
    @JsonProperty("callingCodes")
    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    /**
     * 
     * @return
     *     The topLevelDomain
     */
    @JsonProperty("topLevelDomain")
    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    /**
     * 
     * @param topLevelDomain
     *     The topLevelDomain
     */
    @JsonProperty("topLevelDomain")
    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    /**
     * 
     * @return
     *     The alpha2Code
     */
    @JsonProperty("alpha2Code")
    public String getAlpha2Code() {
        return alpha2Code;
    }

    /**
     * 
     * @param alpha2Code
     *     The alpha2Code
     */
    @JsonProperty("alpha2Code")
    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    /**
     * 
     * @return
     *     The alpha3Code
     */
    @JsonProperty("alpha3Code")
    public String getAlpha3Code() {
        return alpha3Code;
    }

    /**
     * 
     * @param alpha3Code
     *     The alpha3Code
     */
    @JsonProperty("alpha3Code")
    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    /**
     * 
     * @return
     *     The currencies
     */
    @JsonProperty("currencies")
    public List<String> getCurrencies() {
        return currencies;
    }

    /**
     * 
     * @param currencies
     *     The currencies
     */
    @JsonProperty("currencies")
    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    /**
     * 
     * @return
     *     The languages
     */
    @JsonProperty("languages")
    public List<String> getLanguages() {
        return languages;
    }

    /**
     * 
     * @param languages
     *     The languages
     */
    @JsonProperty("languages")
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
