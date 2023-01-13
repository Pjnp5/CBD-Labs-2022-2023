DROP keyspace if exists cbd_103234_ex4;
CREATE KEYSPACE IF NOT EXISTS cbd_103234_ex4 WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE cbd_103234_ex4;



CREATE TABLE User( username text, name text, email text, timestamp_register timestamp, PRIMARY KEY (username));
CREATE TABLE Phone( name text, description text, launchdate date, price float, colors set<text>,developer text, avg_review double, PRIMARY KEY(name));
CREATE TABLE Phone_Per_Seller ( name text, description text, launchdate date, price float, colors set<text>,seller text, avg_review double, PRIMARY KEY(seller,name)) WITH CLUSTERING ORDER BY(name ASC);

CREATE INDEX colors_index ON phone_per_seller(colors);
CREATE INDEX avg_review_index ON phone_per_seller(avg_review); 

CREATE TABLE Seller (name text, foundation_date date, city text, country text, phones_selling set<text>, PRIMARY KEY (name));

CREATE TABLE Sale(sale_id int, phone_list list<text>, client text, price_per_phone map<text,float>, total_price float, purchase_timestamp timestamp, PRIMARY KEY ( client, purchase_timestamp)) WITH CLUSTERING ORDER BY (purchase_timestamp DESC);

CREATE INDEX totprice_ind ON sale(total_price);
CREATE INDEX phone_list_ind ON sale(phone_list);

CREATE TABLE Review ( phone_name text, author text, review_text text, review_timestamp timestamp, rating float, PRIMARY KEY (phone_name,review_timestamp,author )) WITH CLUSTERING ORDER BY(review_timestamp DESC, author ASC);
