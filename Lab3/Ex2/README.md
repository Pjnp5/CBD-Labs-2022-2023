
**TABLE CREATION**

---

**USER**

```sql
CREATE TABLE User( username text, name text, email text, timestamp_register timestamp, PRIMARY KEY (username));
```

**VIDEO**

```sql
CREATE TABLE Video( video_id int, author text, video_name text, description text, tags set <text>, followers set<text>, timestamp_upload timestamp, PRIMARY KEY (video_id,author,timestamp_upload));
```

**COMMENT_USER**

```sql
CREATE TABLE Comment_User ( author text, video_id int, comment_text text, timestamp_comment timestamp, PRIMARY KEY (author,timestamp_comment)) with CLUSTERING ORDER BY (timestamp_comment DESC);
```

**COMMENT_VIDEO**

```sql
CREATE TABLE Comment_video ( author text, video_id int , comment_text text, timestamp_comment timestamp, PRIMARY KEY ( video_id, timestamp_comment)) WITH CLUSTERING ORDER BY ( timestamp_comment DESC);
```

**CVF**

```SQL
CREATE TABLE CVF(follower text, video_id int, comments list <text>, PRIMARY KEY(video_id));
```

**EVENT	**

```sql
CREATE TABLE Event ( video_id int, author text, type text, event_timestamp timestamp, moment int, PRIMARY KEY (video_id, author,moment,event_timestamp));
```

**RATING**

```sql
CREATE TABLE Rating ( rating_id int , video_id int , rating_value int, PRIMARY KEY (video_id,rating_id));
```

**VIDEO_TAGS**

```sql
CREATE TABLE Video_Tags ( tag text, video set<int>, PRIMARY KEY (tag));
```

---

**INSERTS**

---

```SQL
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user1', 'user1', 'user1@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user2', 'user2', 'user2@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user3', 'user3', 'user3@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user4', 'user4', 'user4@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user5', 'user5', 'user5@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user6', 'user6', 'user6@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user7', 'user7', 'user7@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user8', 'user8', 'user8@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user9', 'user9', 'user9@ua.pt', toTimestamp(now()));
INSERT INTO  User(username,name,email,timestamp_register) VALUES('user0', 'user0', 'user0@ua.pt', toTimestamp(now()));
```

```sql
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (1, 'user1', 'Video1', 'Description1', {'tag1','tag2'}, {'user2','user3','user4'},toTimestamp(now()));
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (2, 'user2', 'Video2', 'Description2', {'tag3','tag4'}, {'user3','user4','user5'},toTimestamp(now())) ;
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (3, 'user3', 'Video3', 'Description3', {'tag5','tag6'}, {'user4','user5','user6'},toTimestamp(now())) ;
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (4, 'user4', 'Video4', 'Description4', {'tag7','tag8'}, {'user5','user6','user7'},toTimestamp(now())) ;
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (5, 'user5', 'Video5', 'Description5', {'tag9','tag0'}, {'user7','user8'},toTimestamp(now())) ;
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (6, 'user6', 'Video6', 'Description6', {'tag7','tag3'}, {'user9','user0'},toTimestamp(now())) ;
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (7, 'user7', 'Video7', 'Description7', {'tag9','tag6'}, {'user4','user2'},toTimestamp(now())) ;
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (8, 'user8', 'Video8', 'Description8', {'tag5','tag1'}, {'user0','user3'},toTimestamp(now()));
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (9, 'user9', 'Video9', 'Description9', {'tag5','tag1'}, {'user1','user4','user6','user0'},toTimestamp(now()));
INSERT INTO Video(video_id,author,video_name,description ,tags,followers,timestamp_upload) VALUES (0, 'user0', 'Video0', 'Description0', {'tag4','tag2','tag9'}, {'user1'},toTimestamp(now()));
```

```SQL
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user9',2, 'wrgvbrthg',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user8',3, 'erbrtevcsas',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user7',4, 'Sem ideias',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user6',5, 'Porque?',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user5',6, 'Rick Roll',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user4',7, 'Depression',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user3',8, 'Eu cresci numa zona',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user2',9, 'Onde estou à tona',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user1',10, 'Mimimimimi',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user3',11, 'Keep on',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user6',12, 'Almost',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user9',13, 'Finally',toTimestamp(now())) ;
INSERT INTO Comment_Video (author,video_id,comment_text,timestamp_comment) VALUES ('user0',1, 'lalalala',toTimestamp(now()));
```

```sql
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user9',2, 'wrgvbrthg',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user8',3, 'erbrtevcsas',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user7',4, 'Sem ideias',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user6',5, 'Porque?',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user5',6, 'Rick Roll',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user4',7, 'Depression',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user3',8, 'Eu cresci numa zona',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user2',9, 'Onde estou à tona',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user1',10, 'Mimimimimi',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user3',11, 'Keep on',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user6',12, 'Almost',toTimestamp(now())) ;
INSERT INTO Comment_User (author,video_id,comment_text,timestamp_comment) VALUES ('user9',13, 'Finally',toTimestamp(now())) ;
```

```sql
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user2' , 'PLAY' , toTimestamp(now()),0 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user2' , 'PAUSE' , toTimestamp(now()),100 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user2' , 'PLAY' , toTimestamp(now()),100 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user2' , 'STOP' , toTimestamp(now()),200 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user6' , 'PLAY' , toTimestamp(now()),0 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user6' , 'PAUSE' , toTimestamp(now()),23 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user6' , 'PLAY' , toTimestamp(now()),23 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user6' , 'PAUSE' , toTimestamp(now()),456 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user8' , 'PLAY' , toTimestamp(now()),0 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user9' , 'PLAY' , toTimestamp(now()),0 );
INSERT INTO Event(video_id,author,type,event_timestamp,moment) VALUES (1,'user9' , 'STOP' , toTimestamp(now()),0 );
```

```sql
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (1,1,2);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (2,1,1);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (3,2,1);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (4,2,4);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (5,2,3);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (6,2,2);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (7,3,2);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (8,3,5);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (9,3,3);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (10,4,2);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (11,4,3);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (12,4,1);
INSERT INTO Rating(rating_id,video_id,rating_value) VALUES (13,4,5);
```

```sql
INSERT INTO Video_Tags ( tag,video) VALUES('tag1' ,{1,8,9});
INSERT INTO Video_Tags ( tag,video) VALUES('tag2' ,{1,0});
INSERT INTO Video_Tags ( tag,video) VALUES('tag3' ,{2,6});
INSERT INTO Video_Tags ( tag,video) VALUES('tag4' ,{2,0});
INSERT INTO Video_Tags ( tag,video) VALUES('tag5' ,{3,8});
INSERT INTO Video_Tags ( tag,video) VALUES('tag6', {3,7});
INSERT INTO Video_Tags ( tag,video) VALUES('tag7', {4,6});
INSERT INTO Video_Tags ( tag,video) VALUES('tag8', {4});
INSERT INTO Video_Tags ( tag,video) VALUES('tag9', {5,7});
INSERT INTO Video_Tags ( tag,video) VALUES('tag0', {5});
```

```sql
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user0',1,['lalalala']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user1',1,['lalalala']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user2',1,['lalalala']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user3',2,['wrgvbrthg']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user4',2,['wrgvbrthg']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user5',2,['wrgvbrthg']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user1',3,['erbrtevcsas']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user5',3,['erbrtevcsas']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user1',3,['Sem ideias']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user5',3,['Sem ideias']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user7',3,['Sem ideias']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user5',3,['Sem ideias']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user3',4,['Porque?']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user4',4,['Porque?']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user7',4,['Porque?']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user2',5,['Rick Roll']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user8',5,['Rick Roll']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user0',5,['Rick Roll']); 
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user2',6,['Depression']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user9',6,['Depression']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user2',7,['Depression']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user6',7,['Depression']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user3',8,['Eu cresci numa zona']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user8',9,['Onde estou à tona']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user4',9,['Onde estou à tona']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user9',10,['Mimimimimi']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user0',10,['Finally']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user7',10,['Keep on']);
INSERT INTO CVF ( follower,video_id, comments) VALUES ('user2',10,['Almost']);
```

---

**JSON**

---

**USER**

```json
cqlsh:videoshared> select json * from user;

 [json]
------------------------------------------------------------------------------------------------------------------
 {"username": "user8", "email": "user8@ua.pt", "name": "user8", "timestamp_register": "2022-11-26 18:37:10.626Z"}
 {"username": "user2", "email": "user2@ua.pt", "name": "user2", "timestamp_register": "2022-11-26 18:36:12.632Z"}
 {"username": "user9", "email": "user9@ua.pt", "name": "user9", "timestamp_register": "2022-11-26 18:37:19.194Z"}
 {"username": "user4", "email": "user4@ua.pt", "name": "user4", "timestamp_register": "2022-11-26 18:36:31.058Z"}
 {"username": "user0", "email": "user0@ua.pt", "name": "user0", "timestamp_register": "2022-11-26 18:37:34.153Z"}
 {"username": "user1", "email": "user1@ua.pt", "name": "user1", "timestamp_register": "2022-11-26 18:36:02.505Z"}
 {"username": "user7", "email": "user7@ua.pt", "name": "user7", "timestamp_register": "2022-11-26 18:37:02.102Z"}
 {"username": "user3", "email": "user3@ua.pt", "name": "user3", "timestamp_register": "2022-11-26 18:36:21.943Z"}
 {"username": "user6", "email": "user6@ua.pt", "name": "user6", "timestamp_register": "2022-11-26 18:36:51.989Z"}
 {"username": "user5", "email": "user5@ua.pt", "name": "user5", "timestamp_register": "2022-11-26 18:36:43.636Z"}

(10 rows)
```

**VIDEO**

```json
cqlsh:videoshared> select json * from video;

 [json]
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
{"video_id": 5, "author": "user5", "timestamp_upload": "2022-11-26 18:47:55.030Z", "description": "Description5", "followers": ["user7", "user8"], "tags": ["tag0", "tag9"], "video_name": "Video5"}
{"video_id": 1, "author": "user1", "timestamp_upload": "2022-11-26 18:45:27.489Z", "description": "Description1", "followers": ["user2", "user3", "user4"], "tags": ["tag1", "tag2"], "video_name": "Video1"}
{"video_id": 8, "author": "user8", "timestamp_upload": "2022-11-26 19:00:37.731Z", "description": "Description8", "followers": ["user0", "user3"], "tags": ["tag1", "tag5"], "video_name": "Video8"}
{"video_id": 0, "author": "user0", "timestamp_upload": "2022-11-26 19:02:29.162Z", "description": "Description0", "followers": ["user1"], "tags": ["tag2", "tag4", "tag9"], "video_name": "Video0"}
{"video_id": 2, "author": "user2", "timestamp_upload": "2022-11-26 18:46:06.413Z", "description": "Description2", "followers": ["user3", "user4", "user5"], "tags": ["tag3", "tag4"], "video_name": "Video2"}
{"video_id": 4, "author": "user4", "timestamp_upload": "2022-11-26 18:47:08.476Z", "description": "Description4", "followers": ["user5", "user6", "user7"], "tags": ["tag7", "tag8"], "video_name": "Video4"}
{"video_id": 7, "author": "user7", "timestamp_upload": "2022-11-26 18:48:46.343Z", "description": "Description7", "followers": ["user2", "user4"], "tags": ["tag6", "tag9"], "video_name": "Video7"}
{"video_id": 6, "author": "user6", "timestamp_upload": "2022-11-26 18:48:21.003Z", "description": "Description6", "followers": ["user0", "user9"], "tags": ["tag3", "tag7"], "video_name": "Video6"}
{"video_id": 9, "author": "user9", "timestamp_upload": "2022-11-26 19:01:11.798Z", "description": "Description9", "followers": ["user0", "user1", "user4", "user6"], "tags": ["tag1", "tag5"], "video_name": "Video9"}
{"video_id": 3, "author": "user3", "timestamp_upload": "2022-11-26 18:46:38.282Z", "description": "Description3", "followers": ["user4", "user5", "user6"], "tags": ["tag5", "tag6"], "video_name": "Video3"}

(10 rows)
```

**COMMENT_VIDEO**

```JSON
cqlsh:videoshared> select json * from comment_video;

 [json]
----------------------------------------------------------------------------------------------------------------------------
{"video_id": 5, "timestamp_comment": "2022-11-26 19:12:06.340Z", "author": "user6", "comment_text": "Porque?"}
{"video_id": 10, "timestamp_comment": "2022-11-26 19:13:36.805Z", "author": "user1", "comment_text": "Mimimimimi"}
{"video_id": 13, "timestamp_comment": "2022-11-26 19:14:35.560Z", "author": "user9", "comment_text": "Finally"}
{"video_id": 11, "timestamp_comment": "2022-11-26 19:13:53.935Z", "author": "user3", "comment_text": "Keep on"}
{"video_id": 1, "timestamp_comment": "2022-11-26 19:24:01.202Z", "author": "user0", "comment_text": "lalalala"}
{"video_id": 8, "timestamp_comment": "2022-11-26 19:12:58.243Z", "author": "user3", "comment_text": "Eu cresci numa zona"}
{"video_id": 2, "timestamp_comment": "2022-11-26 19:11:26.796Z", "author": "user9", "comment_text": "wrgvbrthg"}
{"video_id": 4, "timestamp_comment": "2022-11-26 19:24:05.454Z", "author": "user7", "comment_text": "Sem ideias"}
{"video_id": 7, "timestamp_comment": "2022-11-26 19:12:38.165Z", "author": "user4", "comment_text": "Depression"}
{"video_id": 6, "timestamp_comment": "2022-11-26 19:12:21.901Z", "author": "user5", "comment_text": "Rick Roll"}
{"video_id": 9, "timestamp_comment": "2022-11-26 19:13:21.480Z", "author": "user2", "comment_text": "Onde estou à tona"}
{"video_id": 12, "timestamp_comment": "2022-11-26 19:14:18.018Z", "author": "user6", "comment_text": "Almost"}
{"video_id": 3, "timestamp_comment": "2022-11-26 19:23:56.715Z", "author": "user8", "comment_text": "erbrtevcsas"}

(13 rows)
```

**COMMENT_USER**

```json
cqlsh:videoshared> select json * from comment_user;

 [json]
----------------------------------------------------------------------------------------------------------------------------
{"author": "user8", "timestamp_comment": "2022-11-27 14:35:48.240Z", "comment_text": "erbrtevcsas", "video_id": 3}
{"author": "user2", "timestamp_comment": "2022-11-27 14:35:48.254Z", "comment_text": "Onde estou à tona", "video_id": 9}
{"author": "user9", "timestamp_comment": "2022-11-27 14:40:07.398Z", "comment_text": "wrgvbrthg", "video_id": 2}
{"author": "user9", "timestamp_comment": "2022-11-27 14:39:44.915Z", "comment_text": "Finally", "video_id": 13}
{"author": "user4", "timestamp_comment": "2022-11-27 14:35:48.248Z", "comment_text": "Depression", "video_id": 7}
{"author": "user1", "timestamp_comment": "2022-11-27 14:35:48.257Z", "comment_text": "Mimimimimi", "video_id": 10}
{"author": "user7", "timestamp_comment": "2022-11-27 14:35:48.242Z", "comment_text": "Sem ideias", "video_id": 4}
{"author": "user3", "timestamp_comment": "2022-11-27 14:35:48.259Z", "comment_text": "Keep on", "video_id": 11}
{"author": "user3", "timestamp_comment": "2022-11-27 14:35:48.251Z", "comment_text": "Eu cresci numa zona", "video_id": 8}
{"author": "user6", "timestamp_comment": "2022-11-27 14:37:23.213Z", "comment_text": "Almost", "video_id": 12}
{"author": "user6", "timestamp_comment": "2022-11-27 14:35:48.244Z", "comment_text": "Porque?", "video_id": 5}
{"author": "user5", "timestamp_comment": "2022-11-27 14:35:48.246Z", "comment_text": "Rick Roll", "video_id": 6}

(12 rows)
```

**EVENT**

```json
cqlsh:videoshared> select json * from event;

 [json]
-------------------------------------------------------------------------------------------------------------------
{"video_id": 1, "author": "user2", "moment": 0, "event_timestamp": "2022-11-26 19:49:44.856Z", "type": "PLAY"}
{"video_id": 1, "author": "user2", "moment": 100, "event_timestamp": "2022-11-26 19:49:44.860Z", "type": "PAUSE"}
{"video_id": 1, "author": "user2", "moment": 100, "event_timestamp": "2022-11-26 19:49:44.862Z", "type": "PLAY"}
{"video_id": 1, "author": "user2", "moment": 200, "event_timestamp": "2022-11-26 19:49:44.864Z", "type": "STOP"}
{"video_id": 1, "author": "user6", "moment": 0, "event_timestamp": "2022-11-26 19:49:44.867Z", "type": "PLAY"}
{"video_id": 1, "author": "user6", "moment": 23, "event_timestamp": "2022-11-26 19:49:44.870Z", "type": "PAUSE"}
{"video_id": 1, "author": "user6", "moment": 23, "event_timestamp": "2022-11-26 19:49:44.873Z", "type": "PLAY"}
{"video_id": 1, "author": "user6", "moment": 456, "event_timestamp": "2022-11-26 19:49:44.876Z", "type": "PAUSE"}
{"video_id": 1, "author": "user8", "moment": 0, "event_timestamp": "2022-11-26 19:49:44.878Z", "type": "PLAY"}
{"video_id": 1, "author": "user9", "moment": 0, "event_timestamp": "2022-11-26 19:49:44.900Z", "type": "PLAY"}
{"video_id": 1, "author": "user9", "moment": 0, "event_timestamp": "2022-11-26 19:49:44.902Z", "type": "STOP"}

(11 rows)
```

**RATING**

```json
cqlsh:videoshared> select json * from RATING;

 [json]
-----------------------------------------------------
{"video_id": 1, "rating_id": 1, "rating_value": 2}
{"video_id": 1, "rating_id": 2, "rating_value": 1}
{"video_id": 2, "rating_id": 3, "rating_value": 1}
{"video_id": 2, "rating_id": 4, "rating_value": 4}
{"video_id": 2, "rating_id": 5, "rating_value": 3}
{"video_id": 2, "rating_id": 6, "rating_value": 2}
{"video_id": 4, "rating_id": 10, "rating_value": 2}
{"video_id": 4, "rating_id": 11, "rating_value": 3}
{"video_id": 4, "rating_id": 12, "rating_value": 1}
{"video_id": 4, "rating_id": 13, "rating_value": 5}
{"video_id": 3, "rating_id": 7, "rating_value": 2}
{"video_id": 3, "rating_id": 8, "rating_value": 5}
{"video_id": 3, "rating_id": 9, "rating_value": 3}

(13 rows)
```

**VIDEO_TAGS**

```json
cqlsh:videoshared> select json * from video_tags;

 [json]
-------------------------------------
{"tag": "tag0", "video": [5]}
{"tag": "tag6", "video": [3, 7]}
{"tag": "tag9", "video": [5, 7]}
{"tag": "tag3", "video": [2, 6]}
{"tag": "tag5", "video": [3, 8]}
{"tag": "tag2", "video": [0, 1]}
{"tag": "tag1", "video": [1, 8, 9]}
{"tag": "tag4", "video": [0, 2]}
{"tag": "tag7", "video": [4, 6]}
{"tag": "tag8", "video": [4]}

(10 rows)
```

**CVF**

```JSON
cqlsh:videoshared> select json * from CVF;

 [json]
---------------------------------------------------------------------------
{"follower": "user8", "video_id": 5, "comments": ["Rick Roll"]}
{"follower": "user8", "video_id": 9, "comments": ["Onde estou à tona"]}
{"follower": "user2", "video_id": 1, "comments": ["lalalala"]}
{"follower": "user2", "video_id": 5, "comments": ["Rick Roll"]}
{"follower": "user2", "video_id": 6, "comments": ["Depression"]}
{"follower": "user2", "video_id": 7, "comments": ["Depression"]}
{"follower": "user2", "video_id": 10, "comments": ["Almost"]}
{"follower": "user9", "video_id": 6, "comments": ["Depression"]}
{"follower": "user9", "video_id": 10, "comments": ["Mimimimimi"]}
{"follower": "user4", "video_id": 2, "comments": ["wrgvbrthg"]}
{"follower": "user4", "video_id": 4, "comments": ["Porque?"]}
{"follower": "user4", "video_id": 9, "comments": ["Onde estou à tona"]}
{"follower": "user0", "video_id": 1, "comments": ["lalalala"]}
{"follower": "user0", "video_id": 5, "comments": ["Rick Roll"]}
{"follower": "user0", "video_id": 10, "comments": ["Finally"]}
{"follower": "user1", "video_id": 1, "comments": ["lalalala"]}
{"follower": "user1", "video_id": 3, "comments": ["Sem ideias"]}
{"follower": "user7", "video_id": 3, "comments": ["Sem ideias"]}
{"follower": "user7", "video_id": 4, "comments": ["Porque?"]}
{"follower": "user7", "video_id": 10, "comments": ["Keep on"]}
{"follower": "user3", "video_id": 2, "comments": ["wrgvbrthg"]}
{"follower": "user3", "video_id": 4, "comments": ["Porque?"]}
{"follower": "user3", "video_id": 8, "comments": ["Eu cresci numa zona"]}
{"follower": "user6", "video_id": 7, "comments": ["Depression"]}
{"follower": "user5", "video_id": 2, "comments": ["wrgvbrthg"]}
{"follower": "user5", "video_id": 3, "comments": ["Sem ideias"]}

(26 rows)
```

---

**QUERIES**

---

**Os últimos 3 comentários introduzidos para um vídeo;**
    (Ao fazer os inserts criei apenas um comentário para cada video, logo só aparece um comentário, mas no caso de haver mais esta querie devolve os ultimos 3)*

```sql
cqlsh:videoshared> SELECT  * from comment_video where video_id=5 LIMIT 3;

 video_id | timestamp_comment               | author | comment_text
----------+---------------------------------+--------+--------------
        5 | 2022-11-26 19:12:06.340000+0000 |  user6 |      Porque?

(1 rows)
```

**Lista das tags de determinado vídeo;**

```sql
cqlsh:videoshared> select tags from video where video_id=0;

 tags
--------------------------
 {'tag2', 'tag4', 'tag9'}

(1 rows)
```

**Todos os vídeos com a tag Aveiro;**
    (Não vi que era preciso colocar a tag "Aveiro" então se pesquisar por esta não vai retornar resultados, irei usar "tag1" como substituto)*

```sql
cqlsh:videoshared> select video from video_tags where tag='tag1';

 video
-----------
 {1, 8, 9}

(1 rows)
```

**Os últimos 5 eventos de determinado vídeo realizados por um utilizador;**

* (Por acidente coloquei todos os eventos no video com id 1 )*

```sql
cqlsh:videoshared> select * from event where video_id = 1 and author= 'user2' LIMIT 5;

 video_id | author | moment | event_timestamp                 | type
----------+--------+--------+---------------------------------+-------
        1 |  user2 |      0 | 2022-11-26 19:49:44.856000+0000 |  PLAY
        1 |  user2 |    100 | 2022-11-26 19:49:44.860000+0000 | PAUSE
        1 |  user2 |    100 | 2022-11-26 19:49:44.862000+0000 |  PLAY
        1 |  user2 |    200 | 2022-11-26 19:49:44.864000+0000 |  STOP

(4 rows)
```

**Vídeos partilhados por determinado utilizador (maria1987, por exemplo) num determinado período de tempo (Agosto de 2017, por exemplo);**

* (Em Cassandra não existe um operador 'LIKE' como em SQL Server, o mais parecido é '<='. A utilização de '<=' necessita da utilização de ALLOW FILTERING)*

```sql
cqlsh:videoshared> select * from video where author='user0' and timestamp_upload<='2022-11-27' ALLOW FILTERING;

 video_id | author | timestamp_upload                | description  | followers | tags                     | video_name
----------+--------+---------------------------------+--------------+-----------+--------------------------+------------
        0 |  user0 | 2022-11-26 19:02:29.162000+0000 | Description0 | {'user1'} | {'tag2', 'tag4', 'tag9'} |     Video0

(1 rows)
```

**Os últimos 10 vídeos, ordenado inversamente pela data da partilhada;**

* Não é possivel realizar esta query pois necessitamos de especificar o autor do video para ordenar os videos, além disso seria necessário usar o ALLOW FILTERING porque a timestamp é uma clustering key.

**Todos os seguidores (followers) de determinado vídeo;**

```sql
cqlsh:videoshared> select followers from video where video_id=9;

 followers
--------------------------------------
 {'user0', 'user1', 'user4', 'user6'}

(1 rows)
```

**Todos os comentários (dos vídeos) que determinado utilizador está a seguir (following);**

```sql
cqlsh:videoshared> select * from CVF  where follower='user0';

 follower | video_id | comments
----------+----------+---------------
    user0 |        1 |  ['lalalala']
    user0 |        5 | ['Rick Roll']
    user0 |       10 |   ['Finally']

(3 rows)
```

**Os 5 vídeos com maior rating;**

* Para esta querie "ser possivel" era necessário colocar o rating como clustering key para ordenar em ordem a este, mas como também teriamos de incluir a partition key esta query deixa de ser viável.

**Uma query que retorne todos os vídeos e que mostre claramente a forma pela qual estão ordenados;**

* Tentei realizar a query de algumas maneiras mas não consegui, acabei por apenas assumir que esta não é possivel.

**Lista com as Tags existentes e o número de vídeos catalogados com cada uma delas;**

* Era preciso colocar na tabela ``Video_tags`` que contasse quantos videos possuem a tag que pedirmos, visto que em cassandra não existe nenhum metodo para contar os elementos num conjunto, que neste caso era a coluna video.

(**My Questions**)

**Uma query que retorne o rating médio de um video;**

```sql
cqlsh:videoshared> select avg(rating_value) from rating where video_id=2;

 system.avg(rating_value)
--------------------------
 2

(1 rows)
```

**Pesquisa de comentários por utilizador, ordenado inversamente pela data**

```sql
cqlsh:videoshared> select * from comment_user where author='user5';

 author | timestamp_comment               | comment_text | video_id
--------+---------------------------------+--------------+----------
  user5 | 2022-11-27 14:35:48.246000+0000 |    Rick Roll |        6

(1 rows)
```

---
