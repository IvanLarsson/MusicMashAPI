package com.ivanlarsson.musicmashup.api;

import com.ivanlarsson.musicmashup.service.MashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/v1/mash")
@RestController
public class Controller {

    private final MashService mashService;

    @Autowired
    public Controller(MashService mashService) {
        this.mashService = mashService;
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getInfoByMbid(@PathVariable("id") UUID mbid){
        return mashService.getAPIs(mbid);

    }
}
