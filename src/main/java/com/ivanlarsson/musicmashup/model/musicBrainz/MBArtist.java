package com.ivanlarsson.musicmashup.model.musicBrainz;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

public class MBArtist {
    private UUID uuid;
    private String name;
    private String type;
    private String Country;
 //   private MBRelations[] relations;
    private ArrayList<MBRelations> relations;

    @JsonProperty("release-groups")
    private ArrayList<MBReleaseGroups> releasegroups;


    public MBArtist() {
    }

    public UUID getUuid() {
        return uuid;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return Country;
    }

    public ArrayList<MBRelations> getRelations() {
        return relations;
    }

    public ArrayList<MBReleaseGroups> getReleaseGroups() {
        return releasegroups;
    }
/* public MBRelations[] getRelations() {
        return relations;
    }*/

}
