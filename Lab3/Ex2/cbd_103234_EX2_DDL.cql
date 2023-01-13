CREATE KEYSPACE IF NOT EXISTS cbd_103234_ex2 WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE cbd_103234_ex2;

CREATE TABLE User( username text, name text, email text, timestamp_register timestamp, PRIMARY KEY (username));
CREATE TABLE Video( video_id int, author text, video_name text, description text, tags set <text>, followers set<text>, timestamp_upload timestamp, PRIMARY KEY (video_id,author,timestamp_upload));
CREATE TABLE Comment_User ( author text, video_id int, comment_text text, timestamp_comment timestamp, PRIMARY KEY (author,timestamp_comment)) with CLUSTERING ORDER BY (timestamp_comment DESC);
CREATE TABLE Comment_video ( author text, video_id int , comment_text text, timestamp_comment timestamp, PRIMARY KEY ( video_id, timestamp_comment)) WITH CLUSTERING ORDER BY ( timestamp_comment DESC);
CREATE TABLE CVF(follower text, video_id int, comments list <text>, PRIMARY KEY(video_id));
CREATE TABLE Event ( video_id int, author text, type text, event_timestamp timestamp, moment int, PRIMARY KEY (video_id, author,moment,event_timestamp));
CREATE TABLE Rating ( rating_id int , video_id int , rating_value int, PRIMARY KEY (video_id,rating_id));
CREATE TABLE Video_Tags ( tag text, video set<int>, PRIMARY KEY (tag));
