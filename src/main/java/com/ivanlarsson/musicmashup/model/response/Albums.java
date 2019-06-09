package com.ivanlarsson.musicmashup.model.response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Albums {
    private String title;
    private UUID id;
    private List<String> image;

    public Albums(String title, UUID id) {
        this.title = title;
        this.id = id;
        this.image = new ArrayList<String>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<String> getImage() {
        return image;
    }

    public void addImage(String link){
        this.image.add(link);
    }

    public void setImage(List<String> image) {
        this.image = image;
    }
}
