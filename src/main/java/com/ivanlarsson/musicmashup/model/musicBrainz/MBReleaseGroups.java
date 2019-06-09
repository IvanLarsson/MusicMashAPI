package com.ivanlarsson.musicmashup.model.musicBrainz;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class MBReleaseGroups {
    private String title;
    private UUID id;

    @JsonProperty("primary-type")
    private String primary_type;

    @JsonProperty("first-release-date")
    private String first_release_date;

    public MBReleaseGroups() {
    }

    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }

    public String getPrimaryType() {
        return primary_type;
    }

    public String getFirstReleaseDate() {
        return first_release_date;
    }
}
