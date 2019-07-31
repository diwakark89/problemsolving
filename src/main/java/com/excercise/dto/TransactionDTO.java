/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.dto;

/**
 *
 * @author dikushwa
 */
enum Status {
  SUCCESS, FAIL
}

public class TransactionDTO {

  private String srcAccount;
  private String destAccount;
  private String amount;
  private String status;
  private String transactionId;
  private String date;

  public String getSrcAccount() {
    return srcAccount;
  }

  public void setSrcAccount(String srcAccount) {
    this.srcAccount = srcAccount;
  }

  public String getDestAccount() {
    return destAccount;
  }

  public void setDestAccount(String destAccount) {
    this.destAccount = destAccount;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "TransactionDTO{" + "srcAccount=" + srcAccount + ", destAccount=" + destAccount + ", amount=" + amount + ", status=" + status + ", transactionId=" + transactionId + ", date=" + date + '}';
  }

}
