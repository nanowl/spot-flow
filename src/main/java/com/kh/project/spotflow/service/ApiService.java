package com.kh.project.spotflow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Service
@Slf4j
public class ApiService {
//  private final static String serviceKey = "sample";
  private final static String serviceKey = "6657764d6e68616e3130377659564968";

  // 서울시 도시 공공데이터를 가져옴
  public String getData(String place) throws IOException {
    StringBuilder endpoint = new StringBuilder("http://openapi.seoul.go.kr:8088/");
    endpoint.append(URLEncoder.encode(serviceKey, "UTF-8"))
            .append("/" + URLEncoder.encode("xml", "UTF-8"))
            .append("/" + URLEncoder.encode("citydata", "UTF-8"))
            .append("/" + URLEncoder.encode("1", "UTF-8"))
            .append("/" + URLEncoder.encode("5", "UTF-8"))
            .append("/" + URLEncoder.encode( place, "UTF-8"));
    log.info(endpoint.toString());
    URL url = new URL(endpoint.toString());
    BufferedReader bf;
    bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

    String result = bf.readLine();
    log.info(result);

    return xmlToJson(result);
  }

  // xml to json convert
  public String xmlToJson(String xml) {
    String output = "";
    try {
      JSONObject jsonObject = XML.toJSONObject(xml);
      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      Object json = mapper.readValue(jsonObject.toString(), Object.class);
      output = mapper.writeValueAsString(json);
      log.info(output);
    } catch(Exception e) {
      e.printStackTrace();
    }
    return output;
  }
}
