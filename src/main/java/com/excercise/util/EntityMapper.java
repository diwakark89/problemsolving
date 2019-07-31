/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author dikushwa
 */
public class EntityMapper {

  ObjectMapper objectMapper = new ObjectMapper();

  public <T> T generateEntity(String src, Class<T> dest) throws Exception {
    try {
      return objectMapper.readValue(src, dest);
    } catch (IOException e) {
      throw new Exception("Exception encountered during json parsing.", e);
    }
  }
   public <T> String generateString(T entity) throws Exception {
    try {
      return objectMapper.writeValueAsString(entity);
    } catch (IOException e) {
      throw new Exception("Exception encountered during entity parsing.", e);
    }
  }
}
