CREATE TABLE IF NOT EXISTS contact (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) ,
  company varchar(100) ,
  email varchar(50),
  birthdate Timestamp,
  phone_Id int,
  address_Id int,
  image blob,
  PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS phone_Number (
  phone_Id int NOT NULL AUTO_INCREMENT,
  work varchar(10) ,
  personal varchar(10) ,
  PRIMARY KEY (phone_Id) 
);

CREATE TABLE IF NOT EXISTS Address (
  address_Id int NOT NULL AUTO_INCREMENT,
  address1 varchar(30),
  address2 varchar(30),
  city varchar(20),
  state varchar(2),
  zip varchar(5),
  PRIMARY KEY (address_Id) 
);

insert into contact(name,company,email,birthdate) values('Test1','xyz company','abc@gmail.com','2000-1-1');
insert into contact(name) values('Test2');
insert into phone_number (work,personal) values ('1111111111','2222222222');
update contact set phone_id = 1 where id = 1;
insert into address(address1 ,address2, city ,state ,zip ) values('123 Main st','Apt 100', 'Chicago','IL','60000');
update contact set  address_Id= 1 where id = 1;

--CREATE TABLE contact (
--  id int NOT NULL, 
--  --AUTO_INCREMENT,
--  name varchar(100) not null,
--  --first_name varchar(50) NOT NULL,
--  --last_name varchar(50) DEFAULT NULL,
--  PRIMARY KEY (id)
--  --, UNIQUE KEY UK_username (username)
--) ;
