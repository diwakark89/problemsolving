/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.repository;

import com.excercise.dal.AccountDAL;
import java.sql.SQLException;
import javax.inject.Inject;

/**
 *
 * @author dikushwa
 */
public class AccountRepository {
  
    @Inject
    private AccountDAL accountDAL;
    public float getAccountDetails(String accountId) throws SQLException{
      return Float.parseFloat(accountDAL.getAccountBalance(accountId));
    }
    public void updateAccountDetails(String accountId,float amount) throws SQLException{
      float balance=getAccountDetails(accountId);
       accountDAL.updateAccountDetails(accountId, amount+balance);
    }
    
}
