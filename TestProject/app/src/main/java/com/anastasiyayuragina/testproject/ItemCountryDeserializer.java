package com.anastasiyayuragina.testproject;

import com.anastasiyayuragina.testproject.jsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.jsonCountriesClasses.PageInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class ItemCountryDeserializer extends JsonDeserializer<ItemCountry> {

    private static final int PAGE_INFO = 0;
    private static final int COUNTRIES_ARRAY = 1;

    @Override
    public ItemCountry deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        ItemCountry itemCountry = new ItemCountry();
        ObjectMapper mapper = new ObjectMapper();
        TreeNode treeNode = p.readValueAsTree();

        itemCountry.setPageInfo((PageInfo) mapper.readerFor(new TypeReference<PageInfo>() {}).readValue((JsonNode) treeNode.get(PAGE_INFO)));
        itemCountry.setCountryList((List<Country>) mapper.readerFor(new TypeReference<List<Country>>(){}).readValue((JsonNode) treeNode.get(COUNTRIES_ARRAY)));

        return itemCountry;

    }
}
