/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.repository;

import com.excercise.dal.AccountDAL;
import com.excercise.dto.AccountDTO;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author dikushwa
 */
public class AccountRepository {
  
    @Inject
    private AccountDAL accountDAL;
    public float getAccountBalance(String accountId) throws SQLException{
      String balance=accountDAL.getAccountBalance(accountId);
      if(null != balance){
        return Float.parseFloat(balance);
      }
      throw new SQLException(accountId+ ": Account does not exists to transfer amount");
    }
    public void updateAccountDetails(String accountId,float amount) throws SQLException{
      float balance=getAccountBalance(accountId);
       accountDAL.updateAccountDetails(accountId, amount+balance);
    }

  public List<AccountDTO> getAllAccountDetails() throws SQLException {
    List<AccountDTO> accountLists = accountDAL.getAllAccounts();
    return accountLists;
  }
    
}
