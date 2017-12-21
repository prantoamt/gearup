# README #

This README would normally document whatever steps are necessary to get your application up and running.

## Test the android app: ##
* Create a database named gearupDb
* create a table named mechanic_table SQL query is below 
* [ CREATE TABLE `gearupDb`.`mechanic_table` ( `m_phone` VARCHAR(11) NOT NULL , `m_full_name` VARCHAR(50) NOT NULL , `m_pass` VARCHAR(20) NOT NULL , `m_email` VARCHAR(30) NOT NULL , `m_service` VARCHAR(20) NOT NULL , `m_lat` VARCHAR(500) NOT NULL , `m_lon` VARCHAR(500) NOT NULL , PRIMARY KEY (`m_phone`(11)), UNIQUE (`m_email`(20))) ENGINE = InnoDB;  ]
* Create a table named mechanic_rating SQL query is below
* [  CREATE TABLE `mechanic_rating` ( `m_phone` VARCHAR(11) NOT NULL ,  `c_phone` VARCHAR(11) NOT NULL ,  `m_rating` VARCHAR(10) NOT NULL, FOREIGN KEY (m_phone) REFERENCES mechanic_table(m_phone)) ENGINE = InnoDB;  ]


### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact