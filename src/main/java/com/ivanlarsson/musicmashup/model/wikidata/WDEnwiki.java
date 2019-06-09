package com.ivanlarsson.musicmashup.model.wikidata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "badges" })
public class WDEnwiki {
    private String site;
    private String title;

    public WDEnwiki() {
    }


    public String getSite() {
        return site;
    }

    public String getTitle() {
        return title;
    }
}
