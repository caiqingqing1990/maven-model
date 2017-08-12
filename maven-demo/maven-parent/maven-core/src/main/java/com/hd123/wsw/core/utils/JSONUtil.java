package com.hd123.wsw.core.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.hd123.wsw.api.entity.Person;

public class JSONUtil {
  /**
   * 分页专用
   * 
   * @param lists
   * @param rows
   * @return
   */
  public static String getPersonString(List<Person> lists, int rows) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("total", rows);
    map.put("items", lists);
    String json = null;
    try {
      json = mapper.writeValueAsString(map);
    } catch (JsonGenerationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return json;
  }

  public static String objectToJson(Object object) {
    ObjectMapper mapper = new ObjectMapper();
    String json = null;
    try {
      json = mapper.writeValueAsString(object);
    } catch (JsonGenerationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return json;
  }

  public static String listToJson(List objList) {
    ObjectMapper mapper = new ObjectMapper();
    String json = null;
    try {
      json = mapper.writeValueAsString(objList);
    } catch (JsonGenerationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return json;
  }
  
  public static Object jsonToObject(String json, Object object) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      object = mapper.readValue(json, Object.class);
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return object;
  }

  public static String objectToXml(Object object) {
    XmlMapper xmlMapper = new XmlMapper();
    String xml = null;
    try {
      xml = xmlMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return xml;
  }

  public static Object XmlToObject(String xml, Object object) {
    XmlMapper xmlMapper = new XmlMapper();
    try {
      object = xmlMapper.readValue(xml, Object.class);
    } catch (com.fasterxml.jackson.core.JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (com.fasterxml.jackson.databind.JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return object;
  }
}
