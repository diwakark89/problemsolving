/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.service;

import com.excercise.util.DBManager;
import com.ibatis.common.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.DeleteDbFiles;

/**
 *
 * @author dikushwa
 */
public class SetupTestData {

  private static final String DB_DRIVER = "org.h2.Driver";
  private static final String DB_CONNECTION = "jdbc:h2:~/test";
  private static final String DB_USER = "";
  private static final String DB_PASSWORD = "";
  DBManager dBManager = new DBManager();

  public void runScript() throws ClassNotFoundException, SQLException {
    String aSQLScriptFilePath = "./data.sql";
    String filePath = getClass().getClassLoader().getResource("data.sql").getPath();
    Class.forName("org.h2.Driver");
    Connection con = dBManager.getDBConnection();
    try {
      ScriptRunner sr = new ScriptRunner(con, false, false);

      Reader reader = new BufferedReader(new FileReader(filePath));

      sr.runScript(reader);

    } catch (IOException | SQLException e) {
      System.err.println("Failed to Execute" + aSQLScriptFilePath + " The error is " + e.getMessage());
    }
  }

  public void load() {
    try {

      SetupTestData h2e = new SetupTestData();
      h2e.runScript();
      DeleteDbFiles.execute("~", "test", true);
    } catch (ClassNotFoundException | SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
  public static void main(String[] args) {
    SetupTestData data = new SetupTestData();
    data.load();
  }

//  private static Connection getDBConnection() {
//    Connection dbConnection = null;
//    try {
//      Class.forName(DB_DRIVER);
//    } catch (ClassNotFoundException e) {
//      System.out.println(e.getMessage());
//    }
//    try {
//      dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
//              DB_PASSWORD);
//      return dbConnection;
//    } catch (SQLException ex) {
//      System.out.println(ex.getMessage());
//    }
//    return dbConnection;
//  }
}
