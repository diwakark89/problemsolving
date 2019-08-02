/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.dal;

import com.excercise.dto.AccountDTO;
import com.excercise.service.BaseTest;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author dikushwa
 */
public class AccountDALIT extends BaseTest {

  public final String TEST_ACCOUNT_ID = "A000005";
  private AccountDAL accountDAL;

  @Before
  public void startUp() {
    accountDAL = BeanProvider.getContextualReference(AccountDAL.class, false);
  }

  @Test
  public void testGetAccountBalance() throws SQLException {
    String accountId = "A000005";
    String balance = accountDAL.getAccountBalance(accountId);
    assertEquals("300000", balance);
    System.out.println("Balance: " + balance);

  }

  @Test
  public void testUpdateAccountDetails() throws SQLException {
    String accountId = "A000001";
    float amount = 10000;
    accountDAL.updateAccountDetails(accountId, amount);
    String newBalance = accountDAL.getAccountBalance(accountId);

    assertEquals("310000", newBalance);
    System.out.println("Updated Balance: " + newBalance);

  }

  @Test
  public void testUpdateNonExistingAccount() throws SQLException {
    String accountId = "A000101";
    float amount = 10000;
    accountDAL.updateAccountDetails(accountId, amount);
    String newBalance = accountDAL.getAccountBalance(accountId);

    assertEquals(null, newBalance);
    System.out.println("Updated Balance: " + newBalance);

  }

  @Test
  public void testGetAllAccounts() throws SQLException {
    List<AccountDTO> accountList = accountDAL.getAllAccounts();
    int count = accountList.size();
    System.out.println("Number of acccounts: " + count);
    assertEquals(8, count);
  }

}
