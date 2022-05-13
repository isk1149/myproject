--------------------------------------------------------------------------------------------------------------
-- 게시판 예제 기초 데이터 생성
create table USERS (
	ID varchar2(8),
	PASSWORD varchar2(8),
	NAME varchar2(20),
	ROLE varchar2(5),
	constraint USERS_PK primary key (ID)
);

create table BOARD (
	SEQ number(5),
	TITLE varchar2(200),
	WRITER varchar2(20),
	CONTENT varchar2(2000),
	REGDATE date default sysdate,
	CNT number(5) default 0,
	constraint BOARD_PK primary key (SEQ)
);

insert into users values ('test', 'test123', '관리자', 'Admin');
insert into users values ('user1', 'user1', '홍길동', 'User');
--------------------------------------------------------------------------------------------------------------
-- 파킹통장 기초 데이터 생성
create table BANK_TB (
	BANK_CD varchar2(6),
	BANK_NM varchar2(12) not null,
	constraint BANK_TB_PK primary key (BANK_CD)
);

create table USER_TB (
	USER_ID 		varchar2(12),
	USER_NM 		varchar2(12)	not null,
	PWD 			varchar2(20) 	not null,
	CREATE_DT 		date 			not null,
	constraint USER_TB_PK primary key (USER_ID) 
);

create table ACCOUNT_TB ( 
    ACCT_NO 		varchar2(10),
    USER_ID 		varchar2(12) 	not null,
    CREATE_DT 		date 			not null,
    DEPOSIT 		number(19,0) 	not null,
    RATE 			number(4,2) 	not null,
    RATE_LIMIT_AMT 	number(15) 		not null,
    OVER_RATE 		number(4,2) 	not null,
    constraint ACCOUNT_TB_PK primary key (ACCT_NO)
);

create table INTEREST_TB (
    ACCT_NO 				varchar2(10),
    APPLY_CNT 				number(3) 		not null,
    INTEREST 				number(15) 		not null,
    constraint BANKBOOK_INTEREST_TB_PK primary key (ACCT_NO)
);

create table TRANSACTION_HISTORY_TB ( -- 생각해볼것
	TRANSACTION_HISTORY_ID
	BANK_CD varchar2(6),
	BANK_NM varchar2(12) not null,
    ACCT_NO varchar2(10),
    TX_DT date,
    TX_BANK_CD varchar2(6),
    TX_ACCT_NO varchar2(10),
    TX_AMT number(15) not null,
    IO_GB varchar2(6) not null,
    constraint TRANSACTION_HISTORY_TB_PK primary key (BANK_CD, ACCT_NO, TX_DT, TX_BANK_CD, TX_ACCT_NO)
);

insert into BANK_TB values ('000001','대한은행');
insert into USER_TB values ('hong', '홍길동', 'gildong', sysdate);
insert into USER_TB values ('kim', '김철수', 'chulsoo', sysdate);
insert into ACCOUNT_TB values ('0000003402', 'hong', sysdate, 0, 2.0, 100000000, 0.1);
insert into ACCOUNT_TB values ('0000006747', 'kim', sysdate, 0, 2.0, 100000000, 0.1);
insert into INTEREST_TB values('0000003402', 0, 0);
insert into INTEREST_TB values('0000006747', 0, 0);

drop table BANK_TB;
drop table USER_TB;
drop table ACCOUNT_TB;
drop table INTEREST_TB;
drop table TRANSACTION_HISTORY_TB;

--------------------------------------------------------------------------------------------------------------

select to_char(transactiondate, 'yyyy-mm-dd hh24:mi:ss') from transactionhistory;
insert into transactionhistory values('000001', '0000006747', to_date('20220403113006', 'YYYYMMDDHH24MISS'), '000001', '0000003402', 100000000, 'O');
insert into transactionhistory values('000001', '0000003402', to_date('20220403113006', 'YYYYMMDDHH24MISS'), '000001', '0000006747', 100000000, 'I');
to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')

select * from bankbook;
update bankbook set deposit = 100000000 where accountnumber = '0000003402';
select * from transactionhistory;
delete from transactionhistory where transactionaccountnumber = '0000000001';
select * from interesthistory;
delete from interesthistory where accountnumber = '0000003402';


update bankbook set deposit = 100000000 where accountnumber = '0000003402';
delete from transactionhistory where transactionaccountnumber = '0000000001' and accountnumber = '0000003402';
delete from interesthistory where accountnumber = '0000003402';

update bankbookinterest set interestapplydaycount = 0, interest = 0 where accountnumber = '0000003402';