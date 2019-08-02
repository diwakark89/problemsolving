/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.dal;

import com.excercise.dto.TransactionDTO;
import com.excercise.service.BaseTest;
import java.sql.SQLException;
import java.util.List;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author dikushwa
 */
public class TransactionDALIT extends BaseTest {

  private TransactionDAL transactionDAL;

  @Before
  public void startUP() {
    transactionDAL = BeanProvider.getContextualReference(TransactionDAL.class, false);
  }

  @Test
  public void testGetTranasctionDetail() throws Exception {
    String transactionId = "100000";
    TransactionDTO transactionDTO = transactionDAL.getTranasctionDetail(transactionId);
    assertEquals(transactionDTO.getSrcAccount(), "A000001");
    System.out.println("Source account it: "+transactionDTO.getSrcAccount());
  }

  @Test
  public void testGetLastTranasctionDetail() throws Exception {
    String transactionid = transactionDAL.getLastTranasctionDetail();
    assertEquals("100006", transactionid);
    System.out.println("transactionid: "+transactionid);
  }

  @Test
  public void testCreateTransaction() throws SQLException, Exception {
    String lastTransactionid = transactionDAL.getLastTranasctionDetail();
    TransactionDTO transactionDto = new TransactionDTO();
    transactionDto.setSrcAccount("A000001");
    transactionDto.setDestAccount("A000002");
    transactionDto.setAmount("100000");
    transactionDAL.createTransaction(transactionDto);
    String newTransactionid = transactionDAL.getLastTranasctionDetail();
    assertNotEquals(lastTransactionid, newTransactionid);
    System.out.println("newTransactionid: "+newTransactionid);
  }

  @Test
  public void testUpdateStatus() throws Exception {
    String transactionId = "100000";
    transactionDAL.updateStatus(transactionId);
    TransactionDTO transactionDTO = transactionDAL.getTranasctionDetail(transactionId);
    assertEquals("Y", transactionDTO.getStatus());
    System.out.println("transactionDTO.getStatus(): "+transactionDTO.getStatus());
  }

  @Test
  public void testGetAllTransactionsById() throws Exception {
    String accountId = "A000003";
    List<TransactionDTO> transactionsList = transactionDAL.getAllTransactionsById(accountId);
    assertEquals(10, transactionsList.size());
    System.out.println("transactionsList.size(): "+transactionsList.size());
  }
}
