create database sprk_travels;

use sprk_travels;

create table hotel(
	property_id int primary key auto_increment,
	property_name varchar(255),
    property_price double,
    property_description varchar(1000),
    property_url varchar(1000)
);


desc hotel;

select * from hotel;