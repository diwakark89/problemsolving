/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  dikushwa
 * Created: Jul 29, 2019
 */

drop table ACCOUNT;
create table ACCOUNT (
   accountId VARCHAR(20) NOT NULL ,
   personId VARCHAR(20) NOT NULL ,
   balance VARCHAR(20) default NULL,
   PRIMARY KEY (accountId)
);

INSERT INTO ACCOUNT(accountId, personId,balance) values('A000001','P000001','100000');
INSERT INTO ACCOUNT(accountId, personId,balance) values('A000002','P000002','150000');
INSERT INTO ACCOUNT(accountId, personId,balance) values('A000003','P000003','200000');
INSERT INTO ACCOUNT(accountId, personId,balance) values('A000004','P000004','250000');
INSERT INTO ACCOUNT(accountId, personId,balance) values('A000005','P000005','300000');
INSERT INTO ACCOUNT(accountId, personId,balance) values('A000006','P000006','3500000');
INSERT INTO ACCOUNT(accountId, personId,balance) values('A000007','P000007','400000');
INSERT INTO ACCOUNT(accountId, personId,balance) values('A000008','P000008','450000');


drop table TRANSACTION;
create table TRANSACTION (
   transactionId int IDENTITY(100000,1) ,
   srcAccountId VARCHAR(20) NOT NULL ,
   destAccountId VARCHAR(20) default NULL,
   amount VARCHAR(20) default 0,
   transactionDate Timestamp default NULL,
   status VARCHAR(1) default 'Y',
   PRIMARY KEY (transactionId)
);

INSERT INTO TRANSACTION(transactionId, srcAccountId, destAccountId,amount,status,transactionDate) values(100000,'A000001','A000002','1000','Y','2019-01-07 00:00:00');
INSERT INTO TRANSACTION(transactionId, srcAccountId, destAccountId,amount,status,transactionDate) values(100001,'A000002','A000001','2000','Y','2019-02-06 00:00:00');
INSERT INTO TRANSACTION(transactionId, srcAccountId, destAccountId,amount,status,transactionDate) values(100002,'A000002','A000001','2000','Y','2019-03-05 00:00:00');
INSERT INTO TRANSACTION(transactionId, srcAccountId, destAccountId,amount,status,transactionDate) values(100003,'A000003','A000001','2000','Y','2019-04-04 00:00:00');
INSERT INTO TRANSACTION(transactionId, srcAccountId, destAccountId,amount,status,transactionDate) values(100004,'A000003','A000002','3000','Y','2019-05-03 00:00:00');
INSERT INTO TRANSACTION(transactionId, srcAccountId, destAccountId,amount,status,transactionDate) values(100005,'A000004','A000006','2000','N','2019-06-02 00:00:00');
INSERT INTO TRANSACTION(transactionId, srcAccountId, destAccountId,amount,status,transactionDate) values(100006,'A000002','A000001','2000','Y','2019-07-01 00:00:00');