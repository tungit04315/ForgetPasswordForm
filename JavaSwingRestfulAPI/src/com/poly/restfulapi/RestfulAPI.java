/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.restfulapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author tungt
 */
public class RestfulAPI {
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static JsonNode get(String path){
        return request("GET", path, null);
    }
    
    public static JsonNode post(String path, Object data){
        return request("POST", path, data);
    }
    
    public static JsonNode put(String path, Object data){
        return request("PUT", path, data);
    }
    
    public static void delete(String path){
        request("DELETE", path, null);
    }
    private static JsonNode request(String method, String path, Object data){
        try {
            // 1 Request
            String uri = "https://poly-java-6-c6bbe-default-rtdb.firebaseio.com" + path + ".json";
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod(method);
            
            // 1.1 Data (Post & Put only)
            if(method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")){
                connection.setDoOutput(true);
                objectMapper.writeValue(connection.getOutputStream(), data);
            }
            
            // 2 Response
            int responseCode = connection.getResponseCode();
            if(responseCode == 200){
                return objectMapper.readTree(connection.getInputStream());
            }
            
            connection.disconnect();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 

}
