-- myproject 계정 만드는 과정( Run SQL Command Line )
-- conn / as sysdba
-- create user myproject identified by myproject;
-- grant connect, resource, dba to myproject;

-- 접속해제 : disconnect
-- 오라클 기동 : startup
-- 오라클 중지 : shutdown abort

--패스워드 변경 
alter profile default limit password_life_time unlimited;

-- PORT  미사용으로 변경 
exec dbms_xdb.sethttpport(0); 

-- 먼저  C:\myproject\oracle_database 폴더를 생성
create tablespace myproject_data01
datafile 'C:\myproject\oracle_database\myproject_data01.dbf'  
size 200m
autoextend on next 20m 
maxsize unlimited
;

create tablespace myproject_idx01
datafile 'C:\myproject\oracle_database\myproject_idx01.dbf'  
size 200m
autoextend on next 20m 
maxsize unlimited
;