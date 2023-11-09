package com.poly.dao;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.bean.Student;
import com.poly.bean.StudentMap;

@Repository
public class StudentDAO {
	RestTemplate rest = new RestTemplate();

	String url = "https://poly-java-6-c6bbe-default-rtdb.firebaseio.com/students.json";

	private String getURL(String key) {
		return url.replace(".json", "/" + key + ".json");
	}

	public StudentMap findAll() {
		return rest.getForObject(url, StudentMap.class);
	}
	
	public Student findByKey(String key) {
		return rest.getForObject(getURL(key), Student.class);
	}
	
	public String add(Student data) {
		HttpEntity<Student> entity = new HttpEntity<>(data);
		JsonNode resp = rest.postForObject(url, entity, JsonNode.class);
		return resp.get("name").asText();
	}
	
	public Student update(String key, Student data) {
		HttpEntity<Student> entity = new HttpEntity<>(data);
		rest.put(getURL(key), entity);
		return data;
	}
	
	public void delete(String key) {
		rest.delete(getURL(key));
	}
	
}
