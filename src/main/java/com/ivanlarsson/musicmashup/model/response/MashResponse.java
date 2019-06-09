package com.ivanlarsson.musicmashup.model.response;

import java.util.List;
import java.util.UUID;

public class MashResponse {
    private UUID mbid;
    private String description;
    private List<Albums> albums;

    public MashResponse(UUID mbid, String description, List<Albums> albums) {
        this.mbid = mbid;
        this.description = description;
        this.albums = albums;
    }

    public UUID getMbid() {
        return mbid;
    }

    public void setMbid(UUID mbid) {
        this.mbid = mbid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Albums> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Albums> albums) {
        this.albums = albums;
    }
}
