package com.ivanlarsson.musicmashup.dataAccess;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanlarsson.musicmashup.model.coverart.CAImages;
import com.ivanlarsson.musicmashup.model.coverart.CAObj;
import com.ivanlarsson.musicmashup.model.musicBrainz.MBArtist;
import com.ivanlarsson.musicmashup.model.musicBrainz.MBReleaseGroups;
import com.ivanlarsson.musicmashup.model.response.Albums;
import com.ivanlarsson.musicmashup.model.wikidata.WDEnwiki;
import com.ivanlarsson.musicmashup.model.wikipedia.WikiPage;
import com.ivanlarsson.musicmashup.model.wikipedia.WikiPageInfo;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class GetDataFromExternalAPI {
    private static final ObjectMapper mapper = new ObjectMapper();


    // Add headers
    // What I need: relations, sitelinks, release-grups
    public MBArtist getFromMusicBrainz(UUID mbid) {
        System.out.println("Getting from MusicBrainz");
        final String uri = "http://musicbrainz.org/ws/2/artist/" + mbid + "?&fmt=json&inc=url-rels+release-groups";

        RestTemplate restTemplate = new RestTemplate();

        MBArtist response = restTemplate.getForObject(
                uri,
                MBArtist.class);

        return response;
    }


    public WDEnwiki getFromWikidata(String wikidataID) throws IOException {
        System.out.println("Getting from Wikidata");
        final String uri = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=" + wikidataID + "&format=json&props=sitelinks";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);


        JsonNode root = mapper.readTree(result);
        JsonNode enwikiNode = root.path("entities").path(wikidataID).path("sitelinks").path("enwiki");

        WDEnwiki wikiDataRes = mapper.treeToValue(enwikiNode, WDEnwiki.class);

        return wikiDataRes;
    }

    public WikiPageInfo getFromWikipedia(String wikidataTitle) throws IOException, URISyntaxException {
        System.out.println("Getting from Wikipedia");
        wikidataTitle = URLEncoder.encode(wikidataTitle, "UTF-8")
                .replace("+", "%20")
                .replace("%28", "(")
                .replace("%29", ")");

        URI myURI = new URI("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=" +
                    "extracts&exintro=true&redirects=true&titles=" + wikidataTitle);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(myURI, String.class);

        JsonNode root = mapper.readTree(result);
        JsonNode wiki = root.path("query").path("pages");

        WikiPage wikiData = mapper.treeToValue(wiki, WikiPage.class);

        return wikiData.getPage();
    }

    public List<Albums> geFromCoverArtArchive(List<MBReleaseGroups> releasegroups) {
        System.out.println("Getting from CoverArt");
        List<Albums> albumsList = new ArrayList<>();

        for (MBReleaseGroups r : releasegroups){
            final String uri = "http://coverartarchive.org/release-group/" + r.getId();
            RestTemplate restTemplate = new RestTemplate();
            CAObj response;

            try {
                response = restTemplate.getForObject(
                        uri,
                        CAObj.class);
            } catch (HttpClientErrorException e) {
                continue;
            }
            Albums a = new Albums(r.getTitle(), r.getId());
            for (CAImages img : response.getImages()){
                a.addImage(img.getImage());
            }
            albumsList.add(a);
        }
            return albumsList;
    }
}
