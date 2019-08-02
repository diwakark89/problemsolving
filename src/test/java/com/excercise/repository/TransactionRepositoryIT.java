/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.repository;

import com.excercise.dto.TransactionDTO;
import com.excercise.service.BaseTest;
import java.util.List;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author dikushwa
 */
public class TransactionRepositoryIT extends BaseTest {

  private TransactionRepository repository;

  @Before
  public void startUp() {

    repository = BeanProvider.getContextualReference(TransactionRepository.class, false);
  }

  @Test
  public void testGetTranasctionDetail() throws Exception {
    String transactionId = "100000";
    TransactionDTO transactionDTO = repository.getTranasctionDetail(transactionId);
    assertEquals(transactionDTO.getSrcAccount(), "A000001");
    System.out.println("=======Test Result=======");
    System.out.println("Source account it: " + transactionDTO.getSrcAccount());
    System.out.println("=========================");
  }

  @Test
  public void TestCreateTransaction() throws Exception {
    TransactionDTO transactionDto = new TransactionDTO();
    transactionDto.setSrcAccount("A000001");
    transactionDto.setDestAccount("A000002");
    transactionDto.setAmount("100000");
    repository.createTransaction(transactionDto);
  }

  @Test
  public void TestGetAllTransactionsMesasge() throws Exception {
    
    String accountId="A000001";
    List<String> list = repository.getAllTransactionsMesasge(accountId);
    System.out.println("=======Test Result=======");
    assertEquals(10, list.size());
    System.out.println("=========================");

  }

  @Test
  public void testGetAllTransactions() throws Exception {
    String accountId = "A00001";
    String list = repository.getAllTransactions(accountId);
    System.out.println("=======Test Result=======");
    assertNotEquals(null, list);
    System.out.println("=========================");
  }

  @Test
  public void getCreditMessage() {
    String date="2019-08-08",dest="A00001", amount="300000";
    String message= repository.getCreditMessage(date, dest, amount);
    System.out.println("=======Test Result=======");
    System.out.println(message);
    System.out.println("=========================");
  }

  @Test
  public void getDebitMessage() {
    String date="2019-08-08",dest="A00001", amount="300000";
    String message=repository.getDebitMessage(date, dest, amount);
    System.out.println("=======Test Result=======");
    System.out.println(message);
    System.out.println("=========================");
  }
}
