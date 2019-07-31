/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dikushwa
 */
public class DBManager {

  private static final String DB_DRIVER = "org.h2.Driver";
  private static final String DB_CONNECTION = "jdbc:h2:~/test";
  private static final String DB_USER = "";
  private static final String DB_PASSWORD = "";
  private static Connection dbConnection = null;

  public Connection getDBConnection() {

    try {
      Class.forName(DB_DRIVER);
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {

      return dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return dbConnection;
  }
}
