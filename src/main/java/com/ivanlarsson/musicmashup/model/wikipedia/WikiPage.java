package com.ivanlarsson.musicmashup.model.wikipedia;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WikiPage {
    private static final ObjectMapper mapper = new ObjectMapper();
    private WikiPageInfo page;

    public WikiPage() {
    }

    public WikiPageInfo getPage() {
        return page;
    }

    @JsonAnySetter
    public void set(String newKey, JsonNode newValue) {
        try {
            page = mapper.treeToValue(newValue, WikiPageInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
