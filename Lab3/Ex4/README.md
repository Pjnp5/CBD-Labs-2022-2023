
**TABLE CREATION**

---

**USER**

```sql
CREATE TABLE User( username text, name text, email text, timestamp_register timestamp, PRIMARY KEY (username));
```

**PHONE**

```sql
CREATE TABLE Phone( name text, description text, launchdate date, price float, colors set<text>,developer text, avg_review double, PRIMARY KEY(name));
```

**PHONE_PER_SELLER**

```sql
CREATE TABLE Phone_Per_Seller ( name text, description text, launchdate date, price float, colors set<text>,seller text, avg_review double, PRIMARY KEY(seller,name)) WITH CLUSTERING ORDER BY(name ASC);

CREATE INDEX colors_index ON phone_per_seller(colors);
CREATE INDEX avg_review_index ON phone_per_seller(avg_review); 
```

**SELLER**

```sql
CREATE TABLE Seller (name text, foundation_date date, city text, country text, phones_selling set<text>, PRIMARY KEY (name));
```

**SALE**

```SQL
CREATE TABLE Sale(sale_id int, phone_list list<text>, client text, price_per_phone map<text,float>, total_price float, purchase_timestamp timestamp, PRIMARY KEY ( client, purchase_timestamp)) WITH CLUSTERING ORDER BY (purchase_timestamp DESC);

CREATE INDEX totprice_ind ON sale(total_price);
CREATE INDEX phone_list_ind ON sale(phone_list);
```

**REVIEW**

```sql
CREATE TABLE Review ( phone_name text, author text, review_text text, review_timestamp timestamp, rating float, PRIMARY KEY (phone_name,review_timestamp,author )) WITH CLUSTERING ORDER BY(review_timestamp DESC, author ASC);
```

---

**INSERTS**

---

```SQL
INSERT INTO  User(username,name,email,timestamp_register) VALUES('PP_Segundo', 'Paulo', 'pp@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Paradex', 'Paradinha', 'paradex@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Mankings', 'Manco', 'manco@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Sora', 'Tiago', 'sora@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Lilipops', 'Lili', 'lilipops@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Goncas', 'Gonçalo', 'goncas@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Joni', 'João', 'joni@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Beatrix', 'Beatriz', 'belatrix@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Ruilex', 'Rui', 'ruiliex@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Zezinho', 'Zezinho', 'zezinho@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('Arturito', 'Artur', 'arturito@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('SeiLa', 'SeiNão', 'sei@ua.pt', toTimestamp(now()));
```



```sql
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('SAMSUNG Galaxy S20 FE', 'O Samsung Galaxy S20 FE é um smartphone Android avancado e abrangente em todos os pontos de vista com algumas caracteristicas excelentes.', '2021-03-31', 589.97, {'Blue', 'Black', 'White'}, 'Samsung', 4);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Redmi Note 9S', 'O Redmi Note 9S é um smartphone Android de bom nível, ótimo para fotos, que pode satisfazer até o mais exigente dos usuários.', '2020-03-23', 189.90, {'Red', 'Black', 'White'}, 'Xiaomi', 3.5);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('OnePlus', 'The OnePlus 8T is an Android-based smartphone designed and marketed by OnePlus. It is the sixteenth phone released by OnePlus.', '2020-10-16', 424.79, {'Green', 'Brown', 'Cyan'}, 'OnePlus', 3);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('NOKIA 3310', 'NOKIA 3310, what else?', '2000-09-01', 57.24, {'Black'}, 'Nokia', 5);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Huawei P8 Lite', 'O Huawei P8 Lite é um smartphone Android mediano, ideal para quem não tem muitas exigências mas não abre mão de um bom display touchscreen.', '2015-05-01', 57.24, {'Black', 'Brown'}, 'Huawei', 3);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Iphone 3GS', 'O iPhone 3GS (originalmente denominado iPhone 3G S) é um smartphone que foi projetado e comercializado pela Apple', '2008-08-01', 99.33, {'Black', 'Brown'}, 'Apple', 3.5);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Oppo Reno 9 Pro 5G', 'Oppo Reno 9, Reno 9 Pro, and Reno 9 Pro+ were launched in China on Thursday. They feature 6.7-inch OLED displays with up to 120Hz refresh rate and pack 32-megapixel selfie shooters.', '2022-11-24', 700.32, {'Black', 'White', 'Blue'}, 'Oppo', 4.5);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Redmi Note 12 Pro+', 'The Redmi Note 12 Pro+ runs Android 12 and is powered by a 5000mAh battery.', '2022-10-27', 440.22, {'Black', 'Red', 'Blue'}, 'Xiaomi', 3.5);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Samsung Galaxy A04s', 'Samsung Galaxy A04s is powered by an octa-core Samsung Exynos 850 processor.', '2022-10-03', 567.45, {'Brown', 'Red', 'Blue'}, 'Samsung', 4.5);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Motorola Edge 30 Ultra', 'The display sports Gorilla Glass 5 for protection.', '2022-09-08', 234.55, {'Brown', 'Red', 'Blue', 'Green'}, 'Motorola', 2.5);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('iPhone 14', ' iPhone 14 is powered by a hexa-core Apple A15 Bionic processor.', '2022-09-07', 987.65, {'Brown', 'Red'}, 'Apple', 4.5);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Huawei Mate 50 Pro', 'Huawei Mate 50 Pro is powered by an octa-core Qualcomm Snapdragon 8+ Gen 1 processor.', '2022-09-06', 531.33, {'Brown','Blue'}, 'Huawei', 3);
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Nokia G60 5G', 'The display sports Gorilla Glass 5 for protection.Nokia G60 5G comes with 6GB of RAM.', '2022-09-01', 953.22, {'Blue', 'Green'}, 'Nokia', 3.5);
```

```SQL
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('SAMSUNG Galaxy S20 FE', 'O Samsung Galaxy S20 FE e um smartphone Android avancado e abrangente em todos os pontos de vista com algumas caracteristicas excelentes.', '2021-03-31', 589.97, {'Blue', 'Black', 'White'}, 'Samsung', 4);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Redmi Note 9S', 'O Redmi Note 9S e um smartphone Android de bom nivel, otimo para fotos, que pode satisfazer ate o mais exigente dos usuarios.', '2020-03-23', 189.90, {'Red', 'Black', 'White'}, 'Xiaomi', 3.5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('OnePlus 8T', 'The OnePlus 8T is an Android-based smartphone designed and marketed by OnePlus. It is the sixteenth phone released by OnePlus.', '2020-10-16', 424.79, {'Green', 'Brown', 'Cyan'}, 'OnePlus', 3);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('NOKIA 3310', 'NOKIA 3310, what else?', '2000-09-01', 57.24, {'Black'}, 'Nokia', 5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Huawei P8 Lite', 'O Huawei P8 Lite e um smartphone Android mediano, ideal para quem nao tem muitas exigencias mas nao abre mao de um bom display touchscreen.', '2015-05-01', 543.21, {'Black', 'Brown'}, 'Huawei', 3);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Iphone 3GS', 'O iPhone 3GS (originalmente denominado iPhone 3G S) e um smartphone que foi projetado e comercializado pela Apple', '2008-08-01', 99.33, {'Black', 'Brown'}, 'Apple', 3.5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Oppo Reno 9 Pro 5G', 'Oppo Reno 9, Reno 9 Pro, and Reno 9 Pro+ were launched in China on Thursday. They feature 6.7-inch OLED displays with up to 120Hz refresh rate and pack 32-megapixel selfie shooters.', '2022-11-24', 700.32, {'Black', 'White', 'Blue'}, 'Oppo', 4.5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Redmi Note 12 Pro+', 'The Redmi Note 12 Pro+ runs Android 12 and is powered by a 5000mAh battery.', '2022-10-27', 440.22, {'Black', 'Red', 'Blue'}, 'Xiaomi', 3.5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Samsung Galaxy A04s', 'Samsung Galaxy A04s is powered by an octa-core Samsung Exynos 850 processor.', '2022-10-03', 567.45, {'Brown', 'Red', 'Blue'}, 'Samsung', 4.5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Motorola Edge 30 Ultra', 'The display sports Gorilla Glass 5 for protection.', '2022-09-08', 234.55, {'Brown', 'Red', 'Blue', 'Green'}, 'Motorola', 2.5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('iPhone 14', ' iPhone 14 is powered by a hexa-core Apple A15 Bionic processor.', '2022-09-07', 987.65, {'Brown', 'Red'}, 'Apple', 4.5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Huawei Mate 50 Pro', 'Huawei Mate 50 Pro is powered by an octa-core Qualcomm Snapdragon 8+ Gen 1 processor.', '2022-09-06', 531.33, {'Brown','Blue'}, 'Huawei', 3);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Nokia G60 5G', 'The display sports Gorilla Glass 5 for protection.Nokia G60 5G comes with 6GB of RAM.', '2022-09-01', 953.22, {'Blue', 'Green'}, 'Nokia', 3.5);

```


```sql
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Apple', '1976-04-01','California', 'EUA', {'Iphone 3GS', 'iPhone 14'});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Samsung', '1969-01-13','Suwon', 'Coreia do Sul', {'SAMSUNG Galaxy S20 FE', 'Samsung Galaxy A04s'});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Xiaomi', '2010-04-06','Pequim', 'China', {'Redmi Note 9S', 'Redmi Note 12 Pro+'});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('OnePlus', '2013-12-16','Pequim', 'China', {'OnePlus 8T'});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Nokia', '1865-05-12','Tampere', 'Finlandia', {'NOKIA 3310', 'Nokia G60 5G'});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Huawei', '1987-09-15','Shenzhen', 'China', {'Huawei P8 Lite', 'Huawei Mate 50 Pro'});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Motorola', '1928-09-25','Chicago', 'EUA', {'Motorola Edge 30 Ultra'});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Oppo', '2004-10-10','Dongguan', 'China', {'Oppo Reno 9 Pro 5G'});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Asus', '1989-04-02','Taipei', 'Taiwan', {});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Lava', '2008-02-14','Noida', 'India', {});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('Google', '1999-09-04','California', 'EUA', {});
INSERT INTO seller(name, foundation_date, city, country, phones_selling) VALUES('LG', '1927-01-05','Busan', 'Coreia do sul', {});
```

```sql
INSERT INTO sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(1, ['SAMSUNG Galaxy S20 FE'], 'PP_Segundo', {'SAMSUNG Galaxy S20 FE': 589.97}, 589.97, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(2, ['Iphone 3GS', 'NOKIA 3310'], 'Paradex', {'Iphone 3GS': 99.33, 'Nokia 3310': 57.24}, 156.57, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(3, ['Huawei P8 Lite', 'Oppo Reno 9 Pro 5G'], 'Sora', {'Huawei P8 Lite': 543.21, 'Oppo Reno 9 Pro 5G': 700.32}, 1243.53 , toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(4, ['Motorola Edge 30 Ultra'], 'Mankings', {'Motorola Edge 30 Ultra': 234.55}, 234.55, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(5, ['Huawei Mate 50 Pro', 'Nokia G60 5G'], 'Ruilex', {'Huawei Mate 50 Pro': 531.33, 'Nokia G60 5G': 953.22}, 1484.55, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(6, ['iPhone 14', 'Samsung Galaxy A04s'], 'Arturito', {'iPhone 14': 987.65, 'Samsung Galaxy A04s': 567.45}, 1555.10, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(7, ['Redmi Note 12 Pro+'], 'Lilipops', {'Redmi Note 12 Pro+': 440.22}, 440.22, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(8, ['Oppo Reno 9 Pro 5G'], 'Zezinho', {'Oppo Reno 9 Pro 5G': 700.32}, 700.32, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(9, ['Iphone 3GS'], 'Beatrix', {'Iphone 3GS': 99.33}, 99.33, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(10, ['Oppo Reno 9 Pro 5G', 'Huawei Mate 50 Pro', 'Huawei P8 Lite'], 'SeiLa', {'Oppo Reno 9 Pro 5G': 700.32, 'Huawei Mate 50 Pro': 531.33, 'Huawei P8 Lite': 543.21}, 1774.86, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(11, ['NOKIA 3310'], 'Joni', {'Nokia 3310': 57.24}, 57.24, toTimestamp(now()));
INSERT INTO Sale(sale_id, phone_list, client, price_per_phone, total_price, purchase_timestamp) VALUES(12, ['Redmi Note 9S'], 'Goncas', {'Redmi Note 9S': 189.90}, 189.90, toTimestamp(now()));
```


```sql
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('SAMSUNG Galaxy S20 FE', 'PP_Segundo', 'Cool Phone', toTimestamp(now()), 4);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Redmi Note 9S', 'Goncas', 'Seems noice', toTimestamp(now()), 3.5);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('OnePlus 8T', 'Paradex', 'Parei no tempo', toTimestamp(now()), 3);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('NOKIA 3310', 'Mankings','Até fiquei manco', toTimestamp(now()), 5);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Huawei P8 Lite', 'Sora', 'De facto, pylance', toTimestamp(now()),4.5);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Iphone 3GS', 'Lilipops', 'Telemovel Doce', toTimestamp(now()), 5);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Oppo Reno 9 Pro 5G', 'Arturito', 'gostei', toTimestamp(now()), 3);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Redmi Note 12 Pro+', 'Zezinho', 'Read mi', toTimestamp(now()), 2);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Samsung Galaxy A04s', 'Joni', 'Joni Gosti', toTimestamp(now()), 4.5);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Motorola Edge 30 Ultra', 'Beatrix', 'Beatri Aproves', toTimestamp(now()), 4);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('iPhone 14', 'Ruilex', 'Telemovel Rolex', toTimestamp(now()), 2.5);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Huawei Mate 50 Pro', 'SeiLa', 'Eu sei la', toTimestamp(now()), 3);
INSERT INTO Review(phone_name, author, review_text, review_timestamp, rating) VALUES('Nokia G60 5G', 'PP_Segundo', 'Review top', toTimestamp(now()), 4);
```

---

**QUERIES**

---
