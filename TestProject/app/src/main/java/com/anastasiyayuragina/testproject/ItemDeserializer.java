package com.anastasiyayuragina.testproject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by vladimirkondratenko on 7/26/16.
 */
public class ItemDeserializer extends JsonDeserializer<Item>{

    private static final int PAGE_INFO = 0;
    private static final int COUNTRIES_ARRAY = 1;

    @Override
    public Item deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        Item item = new Item();
        ObjectMapper mapper = new ObjectMapper();
        TreeNode treeNode = p.readValueAsTree();

        item.pageInfo = mapper.readerFor(new TypeReference<PageInfo>() {}).readValue((JsonNode) treeNode.get(PAGE_INFO));
        item.countryList = mapper.readerFor(new TypeReference<List<Country>>(){}).readValue((JsonNode) treeNode.get(COUNTRIES_ARRAY));

        return item;

    }

}
