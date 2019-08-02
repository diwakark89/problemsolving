/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.repository;

import com.excercise.dal.AccountDAL;
import com.excercise.dto.AccountDTO;
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
public class AccountRepositoryIT extends BaseTest {

  private AccountRepository accountRepository;
   private final String ACCOUNT_Id="A00001";

  @Before
  public void startUp() {

    accountRepository = BeanProvider.getContextualReference(AccountRepository.class, false);
  }

  @Test
  public void testGetAccountBalance() throws SQLException {

    float balance = accountRepository.getAccountBalance(ACCOUNT_Id);
    System.out.println(balance);
  }

  @Test
  public void updateAccountDetails() throws SQLException {
    float amount=10000;
    accountRepository.updateAccountDetails(ACCOUNT_Id, amount);
  }

  @Test
  public void getAllAccountDetails() throws SQLException {
    List<AccountDTO> list= accountRepository.getAllAccountDetails();
    assertEquals(10, list.size());
  }

}
