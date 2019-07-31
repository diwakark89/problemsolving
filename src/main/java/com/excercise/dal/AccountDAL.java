/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.dal;

import com.excercise.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;

/**
 *
 * @author dikushwa
 */
public class AccountDAL {

  @Inject
  private DBManager dbManager;

  public String getAccountBalance(String accountId) throws SQLException {
    String SelectQuery = "select * from ACCOUNT where accountId=?";

    String balance = null;
    try (Connection connection = dbManager.getDBConnection()) {
      PreparedStatement selectPreparedStatement = null;

      selectPreparedStatement = connection.prepareStatement(SelectQuery);
      selectPreparedStatement.setString(1, accountId);
      ResultSet rs = selectPreparedStatement.executeQuery();

      while (rs.next()) {
      balance= rs.getString("balance");
      }
      selectPreparedStatement.close();
      
    } catch (SQLException e) {
      System.out.println("Exception Message " + e.getLocalizedMessage());
    } catch (Exception e) {
      System.out.println("Exception Message " + e.getLocalizedMessage());
    }
    return balance;
  }

  public void updateAccountDetails(String accountId, float amount) throws SQLException {
    String updateQuey = "update ACCOUNT set balance=? where accountId=?";
    PreparedStatement preparedStatement = null;
    try (Connection connection = dbManager.getDBConnection()) {
      connection.setAutoCommit(true);
      preparedStatement = connection.prepareStatement(updateQuey);
      preparedStatement.setString(1, String.valueOf(amount));
      preparedStatement.setString(2, accountId);
      preparedStatement.close();
      connection.commit();
    } catch (SQLException e) {
      System.out.println("Exception Message " + e.getLocalizedMessage());
    } catch (Exception e) {
      System.out.println("Exception Message " + e.getLocalizedMessage());
    }
  }

}
