package com.ivanlarsson.musicmashup.model.wikipedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "ns" })
public class WikiPageInfo {
    private int pageid;
    private String title;
    private String extract;

    public int getPageid() {
        return pageid;
    }

    public String getTitle() {
        return title;
    }

    public String getExtract() {
        return extract;
    }
}
