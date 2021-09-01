create database dynamic_sql23;
use dynamic_sql23;

CREATE TABLE customer (
  CUST_ID int(11) NOT NULL AUTO_INCREMENT,
  FIRST_NAME varchar(50) NOT NULL,
  LAST_NAME varchar(50) NOT NULL,
  ADDRESS1 varchar(50) DEFAULT NULL,
  ADDRESS2 varchar(50) DEFAULT NULL,
  CITY varchar(20) DEFAULT NULL,
  STATE varchar(2) DEFAULT NULL,
  ZIP varchar(5) DEFAULT NULL,
  PHONE varchar(10) DEFAULT NULL,
  PASSWORD varchar(10) NOT NULL,
  EMAIL varchar(100) DEFAULT NULL,
  PRIMARY KEY (CUST_ID)
);
    

    

CREATE TABLE r_dynamic_sql (
  SQL_ID int(11) NOT NULL,
  NUMBER_OF_PARAMETERS int(11) DEFAULT NULL,
  SQL_COLUMNS varchar(500) DEFAULT NULL,
  SQL_STATEMENT varchar(4000) DEFAULT NULL,
  PRIMARY KEY (SQL_ID)
) ;


INSERT INTO customer(CUST_ID,FIRST_NAME,LAST_NAME,ADDRESS1,ADDRESS2,CITY,STATE,ZIP,PHONE,PASSWORD,EMAIL)
VALUES(1,'John','Smith','600 E Main st.','Ste 100','Richmond','VA','23059','123456789','123','test@yahoo.com');



INSERT INTO r_dynamic_sql(SQL_ID,NUMBER_OF_PARAMETERS,SQL_COLUMNS,SQL_STATEMENT)
VALUES(1,1,'firstName,lastName,city','select first_name,last_name,city from customer where CUST_ID=?1');

commit;

