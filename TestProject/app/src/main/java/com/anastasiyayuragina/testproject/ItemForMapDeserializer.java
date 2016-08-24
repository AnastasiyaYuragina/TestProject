package com.anastasiyayuragina.testproject;

import com.anastasiyayuragina.testproject.jsonInfoForMapClasses.InfoForMap;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Created by anastasiyayuragina on 8/11/16.
 *
 */
public class ItemForMapDeserializer extends JsonDeserializer<ItemForMap> {

    @Override
    public ItemForMap deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        ItemForMap itemForMap = new ItemForMap();
        ObjectMapper mapper = new ObjectMapper();
        TreeNode treeNode = p.readValueAsTree();

        itemForMap.setInfoForMap((InfoForMap) mapper.readerFor(new TypeReference<InfoForMap>() {}).readValue((JsonNode) treeNode.get(0)));

        return itemForMap;
    }
}
