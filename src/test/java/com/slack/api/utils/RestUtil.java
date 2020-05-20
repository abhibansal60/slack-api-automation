package com.slack.api.utils;

import com.jayway.jsonpath.JsonPath;
import com.slack.api.world.APIWorld;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
//import static java.util.Map.entry;

@Component
public class RestUtil {
    @Autowired
    APIWorld apiWorld;



   /* For Java 9 and above
   private Map<String,String> getHeaders(){
        return Map.ofEntries(
                entry("Authorization", "Bearer "+apiWorld.getBearerToken()),
                entry("Accept","application/json")
        );
    }*/
    private Map<String,String> getHeaders(){
            Map<String,String> myMap = new HashMap<>();
            myMap.put("Authorization", "Bearer "+apiWorld.getBearerToken());
            myMap.put("Accept","application/json");
            return myMap;
    }

    public Response postData(String endPoint, String... paramsList){
        if(paramsList.length%2!=0) throw new IllegalArgumentException("invalid query params!");
        Map<String,String> paramsMap= new HashMap<>();
        for(int param=0;param<paramsList.length-1;param+=2){
            paramsMap.put(paramsList[param],paramsList[param+1]);
        }
        Response response =
                given()
                        .headers(getHeaders())
                        .when()
                        .queryParams(paramsMap)
                       // .log().all()
                        .post(apiWorld.getBaseURL()+"/"+endPoint)
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract()
                        .response();
        Assert.assertTrue("Error: "+response.jsonPath().get("error"),response.jsonPath().getBoolean("ok"));
        return response;
    }

    public Response getData(String endPoint,String... paramsList){
        if(paramsList.length%2!=0) throw new IllegalArgumentException("invalid query params!");
        Map<String,String> paramsMap= new HashMap<>();
        for(int param=0;param<paramsList.length-1;param+=2){
            paramsMap.put(paramsList[param],paramsList[param]);
        }
        Response response =
                given()
                        .headers(getHeaders())
                        .when()
                        .queryParams(paramsMap)
                        //.log().all()
                        .get(apiWorld.getBaseURL()+"/"+endPoint)
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract()
                        .response();
        Assert.assertTrue("Error: "+response.jsonPath().get("error"),response.jsonPath().getBoolean("ok"));
        return response;
    }

    public Response fetchChannelList(){
        return getData("channels.list");
    }

    public String getChannelAttribute(String channelName,String attribute){
        try {
            return ((JSONArray) JsonPath.parse(fetchChannelList().asString()).read("$[*][?(@.name=='" + channelName + "')]['" + attribute + "']")).get(0).toString();
        }catch (IndexOutOfBoundsException ie){
            throw new IllegalArgumentException("Invalid attribute: "+attribute +" for channel: "+channelName);
        }
    }
}
