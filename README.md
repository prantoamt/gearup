# Gear-Up #

#### An Android app to find mechanics near by your location ####

## Test the android app: ##
* Create a database named gearupDb
* create a table named mechanic_table SQL query is below 
* [ CREATE TABLE `gearupDb`.`mechanic_table` ( `m_phone` VARCHAR(11) NOT NULL , `m_full_name` VARCHAR(50) NOT NULL , `m_pass` VARCHAR(20) NOT NULL , `m_email` VARCHAR(30) NOT NULL , `m_service` VARCHAR(20) NOT NULL , `m_lat` VARCHAR(500) NOT NULL , `m_lon` VARCHAR(500) NOT NULL , PRIMARY KEY (`m_phone`(11)), UNIQUE (`m_email`(20))) ENGINE = InnoDB;  ]
* Create a table named mechanic_rating SQL query is below
* [  CREATE TABLE `mechanic_rating` ( `m_phone` VARCHAR(11) NOT NULL ,  `c_phone` VARCHAR(11) NOT NULL ,  `m_rating` VARCHAR(10) NOT NULL, FOREIGN KEY (m_phone) REFERENCES mechanic_table(m_phone)) ENGINE = InnoDB;  ]


### Why this project? ###

  This app gathers all the information of evey kind of mechanics in our city. The mechanics needs to download the app and turn on the job requesting
  option. Customers will get the status of mechanics if he/she is accepting job request or not. Customer will search for a mechanic near by his location when needed and send him 
  request. The mechanics will be able to accept the job request and communicate with customers. 

### How do I get set up? ###

* Create a new folder
* Open your terminal/git bash inside the folder
* Type `git clone https://prantoamt@bitbucket.org/prantoamt/gearup.git`
* Open the project in Android Studio