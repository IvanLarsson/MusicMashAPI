package com.ivanlarsson.musicmashup.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanlarsson.musicmashup.dataAccess.GetDataFromExternalAPI;
import com.ivanlarsson.musicmashup.model.musicBrainz.MBArtist;
import com.ivanlarsson.musicmashup.model.musicBrainz.MBRelations;
import com.ivanlarsson.musicmashup.model.response.Albums;
import com.ivanlarsson.musicmashup.model.response.MashResponse;
import com.ivanlarsson.musicmashup.model.wikidata.WDEnwiki;
import com.ivanlarsson.musicmashup.model.wikipedia.WikiPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class MashService {
    private UUID mbid;

    private final GetDataFromExternalAPI dfea;
    private MBArtist mbArtist;
    private WDEnwiki wikidata;
    private WikiPageInfo wikiPageInfo;

    private List<Albums> albums;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MashService(GetDataFromExternalAPI dfea) {
        this.dfea = dfea;
    }

    public String getAPIs(UUID mbid){
        this.mbid = mbid;

        getFromMusicBrainz();
        getFromWikidata();
        try {
            getFromWikipedia();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        geFromCoverArtArchive();

        String mashRes = "";

        try {
            mashRes = responseBuilder();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return mashRes;

    }

    public void getFromMusicBrainz(){
        mbArtist = dfea.getFromMusicBrainz(mbid);
    }

    public void getFromWikidata(){
        // error handle if res is null

        List<MBRelations> l = mbArtist.getRelations().stream()
                .filter(mbRelations -> mbRelations.getType().equals("wikidata"))
                .collect(Collectors.toList());

        for (MBRelations rel : l) {
            URL aURL = null;
            try {
                aURL = new URL(rel.getUrl().getResource());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String wikidataID = aURL.getPath().substring(6);

            try {
                wikidata = dfea.getFromWikidata(wikidataID);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void getFromWikipedia() throws URISyntaxException, IOException {
        wikiPageInfo =  dfea.getFromWikipedia(wikidata.getTitle());
    }

    public void geFromCoverArtArchive(){
        albums = dfea.geFromCoverArtArchive(mbArtist.getReleaseGroups());
    }

    public String responseBuilder() throws JsonProcessingException {
        return mapper.writeValueAsString(
                new MashResponse(
                        mbid,
                        wikiPageInfo.getExtract(),
                        albums
                )
        );
    }
}
