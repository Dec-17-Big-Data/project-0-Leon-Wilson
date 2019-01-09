/*




*/
--TABLES
create table users(
    user_id  number(10) primary key,
    first_name varchar2(255) not null,
    last_name varchar2(255) not null,
    username varchar2(255) unique not null,
    phone_number varchar2(255) not null,
    user_password varchar2(255) not null
);

create table accounts(
    account_id number(10) primary key,
    user_id number(10) not null references users(user_id) on delete cascade,
    account_type varchar2(255) not null,
    balance binary_float not null,
    account_name varchar2(255) not null
);

create table cards(
    card_id number(10) primary key,
    card_number number(10) not null,
    card_pin varchar2(4) not null,
    credit_limit binary_float not null,
    account_id number(10) not null unique references accounts(account_id) on delete cascade
);

create table transactions(
    transaction_id number(10) primary key,
    recipient varchar2(255) not null,
    transaction_amount binary_float not null,
    transaction_type varchar2(255) not null,
    account_id number(10) not null references accounts(account_id) on delete cascade,
    transaction_date timestamp not null
);
    
create table super_users(
    user_id number(10) primary key,
    username varchar2(255) not null unique,
    user_password varchar2(255) not null
);

create table transfers(
    transfer_id number(10) primary key,
    transfer_type varchar2(255) not null,
    user_id number(10) not null references users(user_id) on delete cascade,
    to_account_id number(10) not null references accounts(account_id) on delete cascade,
    from_account_id number(10) not null references accounts(account_id) on delete cascade,
    amount binary_float not null,
    transfer_date timestamp not null
);

create table deposits_and_withdrawls(
    deposit_withdrawl_id number(10) primary key,
    deposit_withdrawl_type varchar2(255) not null,
    amount binary_float not null,
    account_id number(10) not null references accounts(account_id) on delete cascade,
    deposit_withdrawl_date timestamp not null
);
    
create table errors(
    error_id number(10) primary key,
    account_id number(10) not null references accounts(account_id) on delete cascade,
    error_type varchar2(255) not null,
    error_date timestamp not null
);

--sequences
CREATE SEQUENCE ACCOUNT_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 0 CACHE 20;

CREATE SEQUENCE CARD_NUMBER_SEQUENCE INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 10000000 CACHE 20;

CREATE SEQUENCE CARD_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 0 CACHE 20;

CREATE SEQUENCE DEPOSIT_WITHDRAWL_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 0 CACHE 20;

CREATE SEQUENCE ERROR_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 0 CACHE 20;

CREATE SEQUENCE SUPER_USER_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 0 CACHE 20;

CREATE SEQUENCE TRANSACTION_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 0 CACHE 20;

CREATE SEQUENCE TRANSFER_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 0 CACHE 20;

CREATE SEQUENCE USER_SEQ INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 0 CACHE 20;

--procedures
create or replace PROCEDURE ADD_ACCOUNT (userID in number
, account_type in varchar2
, balance in binary_float
, account_name in varchar2
, oAccountID out number)
AS 
BEGIN
    insert into accounts(account_id,user_id,account_type,balance,account_name)
    values (ACCOUNT_SEQ.nextval,userID,account_type,balance,account_name)
    returning account_id into oAccountID;
END ADD_ACCOUNT;
/
create or replace PROCEDURE ADD_CARD(card_pin in varchar2
, credit_limit in binary_float
, account_id in number)
AS 
BEGIN
    insert into cards(card_id, card_number, card_pin, credit_limit, account_id)
    values (CARD_SEQ.nextval, CARD_NUMBER_SEQUENCE.nextval, card_pin, credit_limit, account_id);
    commit;
END ADD_CARD;
/
create or replace PROCEDURE ADD_DEPOSIT_WITHDRAWL(dwtype in varchar2,
amount in binary_float,
account_id in number)AS 
BEGIN
    insert into deposits_and_withdrawls(deposit_withdrawl_id,deposit_withdrawl_type,amount,account_id,deposit_withdrawl_date)
    values (DEPOSIT_WITHDRAWL_SEQ.nextval, dwtype, amount,account_id, systimestamp);
    commit;
END ADD_DEPOSIT_WITHDRAWL;
/
create or replace PROCEDURE ADD_ERROR (account_id in number,
error_type in varchar2)AS 
BEGIN
    insert into errors(error_id,account_id,error_type, error_date)
    values (ERROR_SEQ.nextval, account_id, error_type, systimestamp);
    commit;
END ADD_ERROR;
/
create or replace PROCEDURE ADD_SUPER_USER (username in varchar2
, user_password in varchar2) AS 
BEGIN
    insert into super_users(user_id,username,user_password)
    values (SUPER_USER_SEQ.nextval,username,user_password);
    commit;
END ADD_SUPER_USER;
/
create or replace PROCEDURE ADD_TRANSACTION(recipient in varchar
, transaction_amount in binary_float
, transaction_type in varchar2
, accountID in number)AS 
BEGIN
    insert into transactions(transaction_id,recipient,transaction_amount, transaction_type, account_id, transaction_date)
    values (TRANSACTION_SEQ.nextval,recipient,transaction_amount,transaction_type,accountID,systimestamp);
    --update value in account with new amount
    commit;
END ADD_TRANSACTION;
/
create or replace PROCEDURE ADD_TRANSFER(transfer_type in varchar2,
user_id in number,
to_account in number,
from_account in number,
transfer_amount in binary_float) AS 
BEGIN
    insert into transfers(transfer_id, transfer_type, user_id, to_account_id, from_account_id, amount, transfer_date)
    values (TRANSFER_SEQ.nextval, transfer_type, user_id, to_account, from_account, transfer_amount,systimestamp);
    commit;
END ADD_TRANSFER;
/
create or replace PROCEDURE ADD_USER(first_name in varchar2
, last_name in varchar2
, username in varchar2
, phone_number in varchar2
, user_password in varchar2
, oUID out number) 
AS 
BEGIN
    insert into users(user_id,first_name,last_name,username,phone_number,user_password)
    values (USER_SEQ.nextval,first_name,last_name,username,phone_number,user_password)
    returning user_id into oUID;
END ADD_USER;
/
create or replace PROCEDURE REMOVE_ACCOUNT(accountID in number) AS 
BEGIN
    delete from accounts
    where accounts.account_id = accountID;
    commit;
END REMOVE_ACCOUNT;
/
create or replace PROCEDURE REMOVE_CARD(accountID in number) AS 
BEGIN
    delete from cards
    where account_id = accountID;
    commit;
END REMOVE_CARD;
/
create or replace PROCEDURE REMOVE_DEPOSIT_WITHDRAWL AS 
BEGIN
  NULL;
END REMOVE_DEPOSIT_WITHDRAWL;
/
create or replace PROCEDURE REMOVE_ERROR AS 
BEGIN
  NULL;
END REMOVE_ERROR;
/
create or replace PROCEDURE REMOVE_SUPER_USER(uname in varchar2) AS 
BEGIN
    delete from super_users
    where username = uname;
    commit;
END REMOVE_SUPER_USER;
/
create or replace PROCEDURE UPDATE_ACCOUNT_BALANCE(accountID in NUMBER
, ibalance in BINARY_FLOAT)AS 
BEGIN
    update accounts
    set accounts.balance = ROUND(ibalance, 2)
    where accounts.account_id =accountID;
    commit;
END UPDATE_ACCOUNT_BALANCE;
/
create or replace PROCEDURE UPDATE_FIRST_NAME(userID in NUMBER
, firstName in VARCHAR2) AS 
BEGIN
  update users
    set users.first_name = firstName
    where user_id = userID;
    commit;
END UPDATE_FIRST_NAME;
/
create or replace PROCEDURE UPDATE_LAST_NAME(userID in NUMBER, lastName in VARCHAR2)AS 
BEGIN
    update users
    set users.last_name = lastName
    where users.user_id = userID;
    commit;
END UPDATE_LAST_NAME;
/
create or replace PROCEDURE UPDATE_PASSWORD(userID in NUMBER, userPassword in VARCHAR2) AS 
BEGIN
    update users
    set users.user_password = userPassword
    where users.user_id = userID;
    commit;
END UPDATE_PASSWORD;
/
create or replace PROCEDURE UPDATE_PHONE_NUMBER (userID in NUMBER, phoneNumber in VARCHAR2) AS 
BEGIN
    update users
    set users.phone_number = phoneNumber
    where users.user_id = userID;
    commit;
END UPDATE_PHONE_NUMBER;
/
create or replace PROCEDURE UPDATE_SUPER_PASSWORD(userID in NUMBER, userPassword in VARCHAR2) AS 
BEGIN
  update super_users
    set super_users.user_password = userPassword
    where super_users.user_id = userID;
    commit;
END UPDATE_SUPER_PASSWORD;
/
create or replace PROCEDURE UPDATE_SUPER_USERNAME(iuserID in NUMBER, iuserName in VARCHAR2) AS 
BEGIN
  update super_users
    set super_users.username = iuserName
    where super_users.user_id = iuserID;
    commit;
END UPDATE_SUPER_USERNAME;
/
create or replace PROCEDURE UPDATE_USERNAME(iuserID in NUMBER, iuserName in VARCHAR2) AS 
BEGIN
  update users
    set users.username = iuserName
    where users.user_id = iuserID;
    commit;
END UPDATE_USERNAME;
/