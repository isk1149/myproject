-- myproject ���� ����� ����( Run SQL Command Line )
-- conn / as sysdba
-- create user myproject identified by myproject;
-- grant connect, resource, dba to myproject;

-- �������� : disconnect
-- ����Ŭ �⵿ : startup
-- ����Ŭ ���� : shutdown abort

--�н����� ���� 
alter profile default limit password_life_time unlimited;

-- PORT  �̻������ ���� 
exec dbms_xdb.sethttpport(0); 

-- ����  C:\myproject\oracle_database ������ ����
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