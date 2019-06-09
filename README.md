# Mashup
Mashup is a REST api that uses Apache Maven to build and keep track of dependencies. The framework used id Spring boot.
Mashup is using an N-tier architecture. The different tiers used in this api are:
- Api layer
    * Here are all the endpoints made. All the GET, POST DELETES
        In this case there is only one GET endpoint.
-  Service layer
    * Here is all logic made. It gets called by the API layer.
-  Data access layer
    * This layer is used to access data, it could be MYSQL, MongoDB etc.
        But in this case it is the external APIs such as MusicBrainz, Wikidata, Wikipedia and Cover Art Archive.
         And then converting the response into POJOs and then sending it back to the service layer 

The data flow is as such:
Client -> API Layer -> Service Layer -> Data access Layer -> Service Layer -> API Layer -> Client

The API is  running on port 8090

### Build and Run
There is a executable jar in /target that can be used to run the API.

The way this project was build was using Intellij own maven installer. Otherwise a command can be used to generate a jar file.
```sh
$ mvn package
```

To run API, make sure port 8090 is unused:
```sh
$ cd target
$ java -jar musicmashup-1.0.0.jar
```

### Calls
Can now try the API using curl.

```sh
$ curl --request GET localhost:8090/api/v1/mash/{id}
```
Replace {id} with a mbid. Here are some mbid that can be used:
```sh
# 5b11f4ce-a62d-471e-81fc-a69a8278c7da // Nirvana
# 53b106e7-0cc6-42cc-ac95-ed8d30a3a98e // John Williams
# b95ce3ff-3d05-4e87-9e01-c97b66af13d4 // Eminem
```
There is also a bash script that can be used to test the api.
```sh
$ cd _scripts/curlTest
$ ./getByMBID.sh {id}
```


### TODO
Things to do in order make it an official release
*   Dockerize the application
*   Make it into service to be runned a server

Things I would like to have implemented but was unable for whatever reason (mostly time).
* Unit tests
* Better or more error handels for the external api calls