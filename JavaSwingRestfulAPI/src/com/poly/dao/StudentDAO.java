/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Student;
import com.poly.bean.StudentMap;
import com.poly.restfulapi.RestfulAPI;

/**
 *
 * @author tungt
 */
public class StudentDAO {
    ObjectMapper mapper = new ObjectMapper();
    
    public StudentMap findAll(){
        JsonNode resp = RestfulAPI.get("/students");
        return mapper.convertValue(resp, StudentMap.class);
    }
    
    public Student findByKey(String key){
        JsonNode resp = RestfulAPI.get("/students/"+key);
        return mapper.convertValue(resp, Student.class);
    }
    
    public String create(Student data){
        JsonNode resp = RestfulAPI.post("/students", data);
        return resp.get("name").asText();
    }
    
    public Student update(String key, Student data){
        JsonNode resp = RestfulAPI.put("/students/" + key, data);
        return mapper.convertValue(resp, Student.class);
    }
    
    public void delete(String key){
        RestfulAPI.delete("/students/" + key);
    }
}
