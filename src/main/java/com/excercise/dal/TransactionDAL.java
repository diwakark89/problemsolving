/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.dal;

import com.excercise.util.DBManager;
import com.excercise.dto.TransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author dikushwa
 */
public class TransactionDAL {

  @Inject
  private DBManager dbManager;

  public TransactionDTO getTranasctionDetail(String transactionId) throws Exception {
    String SelectQuery = "select * from TRANSACTION where transactionId=?";

    Connection connection = null;
    TransactionDTO transactionDTO = null;
    try {
      connection = dbManager.getDBConnection();

      PreparedStatement selectPreparedStatement = connection.prepareStatement(SelectQuery);
      selectPreparedStatement.setString(1, transactionId);
      ResultSet rs = selectPreparedStatement.executeQuery();

      while (rs.next()) {
        transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(rs.getString("transactionId"));
        transactionDTO.setSrcAccount(rs.getString("srcAccountId"));
        transactionDTO.setDestAccount(rs.getString("destAccountId"));
        transactionDTO.setAmount(rs.getString("amount"));
        transactionDTO.setStatus(rs.getString("status"));
        transactionDTO.setDate(rs.getString("transactionDate"));
      }
      selectPreparedStatement.close();
    } catch (SQLException e) {
      throw new SQLException("Exception occured at getLastTranasctionDetail " + e.getMessage());
    } catch (Exception e) {
      throw new Exception("Exception occured at getLastTranasctionDetail " + e.getMessage());
    }
    return transactionDTO;
  }

  public String getLastTranasctionDetail() throws Exception {
    String SelectQuery = "select * from TRANSACTION order by transactionDate desc";
    String transactionId = null;
    Connection connection = null;
    try {
      connection = dbManager.getDBConnection();
      PreparedStatement selectPreparedStatement = connection.prepareStatement(SelectQuery);

      ResultSet rs = selectPreparedStatement.executeQuery();
      if (rs.next()) {
        transactionId = rs.getString("transactionId");
      }
      selectPreparedStatement.close();
    } catch (SQLException e) {
      throw new SQLException("Exception occured at getLastTranasctionDetail " + e.getMessage());
    } catch (Exception e) {
      throw new Exception("Exception occured at getLastTranasctionDetail " + e.getMessage());
    } finally {
      connection.close();
    }
    return transactionId;
  }

  public void createTransaction(TransactionDTO transactionDto) throws SQLException {
    String InsertQuery = "INSERT INTO TRANSACTION(srcAccountId, destAccountId,amount,status,transactionDate) values(?,?,?,?,?)";
    Connection connection = null;
    try {
      connection = dbManager.getDBConnection();
      connection.setAutoCommit(false);
      PreparedStatement insertPreparedStatement = connection.prepareStatement(InsertQuery);

      insertPreparedStatement.setString(1, transactionDto.getSrcAccount());
      insertPreparedStatement.setString(2, transactionDto.getDestAccount());
      insertPreparedStatement.setString(3, transactionDto.getAmount());
      insertPreparedStatement.setString(4, "N");
      insertPreparedStatement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
      insertPreparedStatement.executeUpdate();
      insertPreparedStatement.close();
      connection.commit();
      
    } catch (SQLException e) {
      System.out.println("Exception Message " + e.getLocalizedMessage());
    } catch (Exception e) {
      System.out.println("Exception Message " + e.getLocalizedMessage());
    } finally {
      connection.close();
    }
  }

  public void updateStatus(String transactionId) throws Exception {
    String InsertQuery = "update TRANSACTION set status='Y' where transactionId=?";
    Connection connection = null;
    try {
      connection = dbManager.getDBConnection();
      connection.setAutoCommit(false);
      PreparedStatement insertPreparedStatement = connection.prepareStatement(InsertQuery);
      insertPreparedStatement.setString(1, transactionId);
      insertPreparedStatement.executeUpdate();
      insertPreparedStatement.close();
      connection.commit();

    } catch (SQLException e) {
      connection.rollback();
      throw new SQLException("Exception occured at getLastTranasctionDetail " + e.getMessage());
    } catch (Exception e) {
      throw new Exception("Exception occured at getLastTranasctionDetail " + e.getMessage());
    } finally {
      connection.close();
    }
  }

  public List<TransactionDTO> getAllTransactionsById(String accountId) throws Exception {
    String SelectQuery = "select * from TRANSACTION where srcAccountId=? or destAccountId=? order by transactionDate desc";

    List<TransactionDTO> transactions = Collections.EMPTY_LIST;
    TransactionDTO transactionDTO = null;
    Connection connection = null;
    try {
      connection = dbManager.getDBConnection();
      PreparedStatement selectPreparedStatement = connection.prepareStatement(SelectQuery);
      selectPreparedStatement.setString(1, accountId);
      selectPreparedStatement.setString(2, accountId);
      ResultSet rs = selectPreparedStatement.executeQuery();
      transactions = new ArrayList<>();
      while (rs.next()) {
        transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(rs.getString("transactionId"));
        transactionDTO.setSrcAccount(rs.getString("srcAccountId"));
        transactionDTO.setDestAccount(rs.getString("destAccountId"));
        transactionDTO.setAmount(rs.getString("amount"));
        transactionDTO.setStatus(rs.getString("status"));
        transactionDTO.setDate(rs.getString("transactionDate"));
        transactions.add(transactionDTO);
      }
      selectPreparedStatement.close();

    } catch (SQLException e) {
      throw new SQLException("Exception occured at getLastTranasctionDetail " + e.getMessage());
    } catch (Exception e) {
      throw new Exception("Exception occured at getLastTranasctionDetail " + e.getMessage());
    } finally {
      connection.close();
    }
    return transactions;
  }
}
