-- EX:2:1
SELECT  * from comment_video where video_id=5 LIMIT 3;
-- EX:2:2
select tags from video where video_id=0;
-- EX:2:3
select video from video_tags where tag='tag1';
-- EX:2:4
select * from event where video_id = 1 and author= 'user2' LIMIT 5;
-- EX:2:5
select * from video where author='user0' and timestamp_upload<='2022-11-27' ALLOW FILTERING;
-- EX:2:6
 -- Não é possivel realizar esta query pois necessitamos de especificar o autor do video para ordenar os videos, além disso seria necessário usar o ALLOW FILTERING porque a timestamp é uma clustering key.
-- EX:2:7
select followers from video where video_id=9;
-- EX:2:8
select * from CVF  where follower='user0';
-- EX:2:9
 -- Para esta querie "ser possivel" era necessário colocar o rating como clustering key para ordenar em ordem a este, mas como também teriamos de incluir a partition key esta query deixa de ser viável.
-- EX:2:10
 -- Tentei realizar a query de algumas maneiras mas não consegui, acabei por apenas assumir que esta não é possivel.
-- EX:2:11
 -- Era preciso colocar na tabela ``Video_tags`` que contasse quantos videos possuem a tag que pedirmos, visto que em cassandra não existe nenhum metodo para contar os elementos num conjunto, que neste caso era a coluna video.
-- EX:2:12
select avg(rating_value) from rating where video_id=2;
-- EX:2:13
select * from comment_user where author='user5';