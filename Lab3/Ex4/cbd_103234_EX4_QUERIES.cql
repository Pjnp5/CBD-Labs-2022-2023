USE cbd_103234_ex4;

-- Update Phone colors
UPDATE TABLE Phone SET colors = colors + {'Yellow'} WHERE phone_name='Iphone 3GS';
UPDATE phone_per_seller SET colors = colors + {'Yellow'} WHERE seller='Apple' AND name='Iphone 3GS';

-- UPDATE Phones an Seller sells
INSERT INTO Phone(name, description, launchdate, price, colors,developer, avg_review) VALUES('Iphone 3GS', 'O iPhone 13 é um smartphone que foi projetado e comercializado pela Apple', '2019-10-01', 799.33, {'Black', 'Brown'}, 'Apple', 3.5);
INSERT INTO Phone_Per_Seller( name, description, launchdate, price, colors,seller, avg_review) VALUES('Iphone 3GS', 'O iPhone 13 é um smartphone que foi projetado e comercializado pela Apple', '2019-10-01', 799.33, {'Black', 'Brown'}, 'Apple', 3.5);
UPDATE Seller SET phones_selling={'Iphone 3GS', 'iPhone 14', 'iPhone 13'} WHERE name='Apple';

-- UPDATE Review And Phone rating 
UPDATE Review SET rating=2.3  WHERE  phone_name='Iphone 3GS' and review_timestamp='2022-11-28 15:49:16.601000+0000'and author = 'Lilipops';

UPDATE phone set avg_review = 3.3 WHERE name='Iphone 3GS';
UPDATE phone_per_seller set avg_review = 3.3 WHERE seller='Apple' AND name='Iphone 3GS';

-- Used an total of 6 Updates




-- Delete Seller
DELETE FROM Phone where name='Oppo Reno 9 Pro 5G';
DELETE FROM phone_per_seller WHERE seller='Oppo';

-- Delete Review
DELETE from Review WHERE phone_name='Motorola Edge 30 Ultra' AND review_timestamp='2022-11-28 15:49:16.603000+0000';

DELETE avg_review from phone_per_seller WHERE seller='Motorola' and name='Motorola Edge 30 Ultra';
DELETE avg_review from phone WHERE name='Motorola Edge 30 Ultra';

-- used an total of 5 Deletes



-- 1. Selecionar todos os telemoveis com cor "Black"
select seller,name,colors from phone_per_seller where colors CONTAINS 'Black';

-- 2. Todas as reviews de um Telemovel
SELECT * from review WHERE phone_name='Nokia G60 5G';

-- 3. Ver a quando um certo user fez a sua ultima compra
SELECT client,purchase_timestamp FROM SALE where client='Paradex' ORDER BY purchase_timestamp DESC LIMIT 1;

-- 4. Obter todos os telemoveis vendidos por um seller
SELECT seller,name from phone_per_seller where seller='Samsung';

-- 5. Obter 4 vendas com valor superior a 1000€
select client,purchase_timestamp,total_price from sale where total_price >1000.00 LIMIT 4 ALLOW FILTERING; 

-- 6. Obter compras que envolvem um certo Telemovel
select client,purchase_timestamp,phone_list,total_price from Sale where phone_list CONTAINS 'NOKIA 3310';

-- 7. Selecionar Telemoveis com rating medio igual a 3.5
SELECT name,avg_review from phone_per_seller where avg_review = 4;

-- 8. O preço de cada Telemovel de 3 compras e o preço toal desta
SELECT price_per_phone,total_price from sale LIMIT 3;

-- 9. Selecionar a ultima review feita de um Telemovel.
SELECT * from review WHERE phone_name='Redmi Note 9S' ORDER BY review_timestamp DESC LIMIT 1;

-- 10. Obter o valor médio do rating das reviews
SELECT avg(rating) FROM review;

