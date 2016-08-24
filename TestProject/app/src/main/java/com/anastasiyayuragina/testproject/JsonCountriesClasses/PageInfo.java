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
    "page",
    "pages",
    "per_page",
    "total"
})
public class PageInfo {

    @JsonProperty("page")
    private Integer page;
    @JsonProperty("pages")
    private Integer pages;
    @JsonProperty("per_page")
    private String perPage;
    @JsonProperty("total")
    private Integer total;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * 
     * @return
     *     The page
     */
    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 
     * @return
     *     The pages
     */
    @JsonProperty("pages")
    public Integer getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The pages
     */
    @JsonProperty("pages")
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * 
     * @return
     *     The perPage
     */
    @JsonProperty("per_page")
    public String getPerPage() {
        return perPage;
    }

    /**
     * 
     * @param perPage
     *     The per_page
     */
    @JsonProperty("per_page")
    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    /**
     * 
     * @return
     *     The total
     */
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
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
