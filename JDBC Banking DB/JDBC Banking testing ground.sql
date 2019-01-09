call add_user('leon','wilson','lwilson','555-555-5555','password');
call add_account(27,'checking',0,'leon''s checking');
call add_account(27,'savings',400,'leon''s savings');
call add_card('0020',500,4);
call add_super_user('root','password');
call add_transaction('Cedar Point',300,'credit',4);
call add_transfer('internal',27,20,4,200);
call add_deposit_withdrawl('deposit',104,20);
call add_error(4,'insuffienct funds');

call update_account_balance(20,300);
--select * from transactions where TO_CHAR(TO_DATE(transaction_date)) = '05-JAN-19';
--find out how to select based on timestamp so I can remove a certain transaction;


call remove_super_user('root');
call remove_card(4);
call remove_account(0);
call remove_user(27);

update accounts
set balance = 0
where account_id = 43;