/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.repository;

import com.excercise.dal.TransactionDAL;
import com.excercise.dal.AccountDAL;
import com.excercise.dto.TransactionDTO;
import com.excercise.util.EntityMapper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author dikushwa
 */
public class TransactionRepository {

  @Inject
  private TransactionDAL transactionDAL;

  @Inject
  private AccountDAL accountDAL;
  
  @Inject
  private AccountRepository accountRepository;

  @Inject
  private EntityMapper entityMapper;

  public TransactionDTO getTranasctionDetail(String transactionId) throws Exception {
   return transactionDAL.getTranasctionDetail(transactionId);
  }

  public String createTransaction(TransactionDTO transactionDto) throws Exception {
    String transactionId,message = null;
    float srcBalance, dstBalance, transferAmout;
    try {
      transferAmout = Float.parseFloat(transactionDto.getAmount());
      srcBalance = accountRepository.getAccountBalance(transactionDto.getSrcAccount());
      if (srcBalance < transferAmout) {
        throw new Exception("Insufficient fund to transact");
      } else {
        dstBalance = accountRepository.getAccountBalance(transactionDto.getDestAccount());
        transactionDAL.createTransaction(transactionDto);
        transactionId = transactionDAL.getLastTranasctionDetail();
        accountDAL.updateAccountDetails(transactionDto.getSrcAccount(), srcBalance - transferAmout);
        accountDAL.updateAccountDetails(transactionDto.getDestAccount(), dstBalance + transferAmout);
        if(null != transactionId)
          transactionDAL.updateStatus(transactionId);
      }
      message="Fund transfered successfully";
    } catch (SQLException ex) {
      System.out.println("Exception occured at Transaction getAllTransactions: ");
      throw new Exception(ex.getMessage());
    }
    return message;
  }

  public List<String> getAllTransactionsMesasge(String accountId) throws Exception {
    List<String> transactionList = null;
    List<TransactionDTO> list = transactionDAL.getAllTransactionsById(accountId);
    if (null != list && list.size() > 0) {
      transactionList = new ArrayList<>();
      for (TransactionDTO dto : list) {
        try {
          if (dto.getSrcAccount().equals(accountId)) {
            transactionList.add(getCreditMessage(dto.getDate(), dto.getDestAccount(), dto.getAmount()));
          } else if (dto.getDestAccount().equals(accountId)) {
            transactionList.add(getDebitMessage(dto.getDate(), dto.getDestAccount(), dto.getAmount()));
          }
        } catch (Exception ex) {
          throw new Exception("Exception occured at Transaction getAllTransactions" + ex.getMessage());
        }
      }
    }
    return transactionList;
  }

  public String getAllTransactions(String accountId) throws Exception {
    List<TransactionDTO> list = transactionDAL.getAllTransactionsById(accountId);
    if (null != list && list.size() > 0) {
      try {
        return entityMapper.generateString(list);
      } catch (Exception ex) {
        throw new Exception("Exception occured at Transaction getAllTransactions" + ex.getMessage());
      }
    }
    return null;
  }

  public String getCreditMessage(String date, String dest, String amount) {
    return "On " + date + " " + amount + " has been recieved from " + dest;
  }

  public String getDebitMessage(String date, String dest, String amount) {
    return "On " + date + " " + amount + " has been transfered to " + dest;
  }
}
