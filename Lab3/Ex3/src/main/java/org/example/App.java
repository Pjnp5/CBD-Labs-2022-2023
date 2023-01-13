package org.example;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        try {
            Session session = cluster.connect("videoshared");
            System.out.println("Consegui fazer connect");
            SelectAll(session, "User");
            System.out.println("Inserting User: ");
            InsertUser(session, "PP_Segundo", "Paulo", "paulo@ua.pt");
            System.out.println("Updating User: ");
            UpdateUser(session, "PP_Segundo", "Pinto");
            System.out.println("Deleting User: ");
            DeleteUser(session, "PP_Segundo");
            System.out.println("Selecting User: ");
            SelectUser(session, "user1");
            System.out.println("Selecting Video: ");
            SelectVideo(session, 1);
            System.out.println("Selecting Comment_Video: ");
            SelectComment_Video(session, 2);
            System.out.println("Selecting Comment_User: ");
            SelectComment_User(session, "user3");
            System.out.println("Selecting CVF: ");
            SelectCVF(session, "user4");
            System.out.println("Selecting Event: ");
            SelectEvent(session, "user6", 1);
            System.out.println("Selecting Rating: ");
            SelectRating(session,3);
            System.out.println("Selecting Video_Tags: ");
            SelectVideo_Tags(session,"tag7");

            System.out.println("---------------------------------------------------------------");

            System.out.println("Pergunta 2: Lista das tags de determinado vídeo");
            ResultSet ex2 = session.execute("select tags from video where video_id=0;");
            for (Row row : ex2){
                System.out.println(row);
            }
            System.out.println("Pergunta 4: Os últimos 5 eventos de determinado vídeo realizados por um utilizador");
            ResultSet ex4 = session.execute("select * from event where video_id = 1 and author= 'user2' LIMIT 5;");
            for (Row row : ex4){
                System.out.println(row);
            }
            System.out.println("Pergunta 7: Todos os seguidores (followers) de determinado vídeo");
            ResultSet ex7 = session.execute("select followers from video where video_id=9;");
            for (Row row : ex7){
                System.out.println(row);
            }
            System.out.println("Pergunta 8: ");
            ResultSet ex8 = session.execute("select tags from video where video_id=0;");
            for (Row row : ex8){
                System.out.println(row);
            }


        } catch (Exception e){
            System.out.println("Exception: " + e);
            System.exit(0);
        }
        System.exit(0);
    }

    public static void SelectAll(Session session, String table){
    ResultSet objescts = session.execute("select * from " + table);
    for (Row row : objescts){
        System.out.println(row);
        }
    }

    private static void InsertUser(Session session, String username, String name, String email){
        session.execute("INSERT INTO User(username,name,email,timestamp_register) VALUES('"+ username + "','" +  name + "','"  + email + "','" + Timestamp.from(Instant.now()) +"')");
        System.out.println("Inserted User: " + username);
        }

    private static void UpdateUser(Session session, String username, String name) {
        session.execute("UPDATE User SET name='" + name + "' WHERE username='" + username + "';");
        System.out.println("Updated user: " + username);

    }

    private static void DeleteUser(Session session, String username) {
        session.execute("Delete from User where username='" + username + "';");
        System.out.println("Deleted user: " + username);
    }

    private static void SelectUser(Session session, String username) {
        ResultSet user = session.execute("select * from User where username='" + username + "'");
        for (Row row : user){
            System.out.println(row);
        }
    }
    private static void SelectVideo(Session session, Integer id) {
        ResultSet user = session.execute("select * from Video where video_id=" + id);
        for (Row row : user){
            System.out.println(row);
        }
    }
    private static void SelectComment_Video(Session session, int id) {
        ResultSet user = session.execute("select * from comment_video where video_id=" + id);
        System.out.println("Aqui");
        for (Row row : user){
            System.out.println(row);
        }
    }
    private static void SelectComment_User(Session session, String username) {
        ResultSet user = session.execute("select * from comment_user where author='" + username + "'");
        for (Row row : user){
            System.out.println(row);
        }
    }
    private static void SelectCVF(Session session,  String username) {
        ResultSet user = session.execute("select * from CVF where follower='" + username + "'");
        for (Row row : user){
            System.out.println(row);
        }
    }
    private static void SelectEvent(Session session,  String username,  int id) {
        ResultSet user = session.execute("select * from Event where author='" + username + "' and video_id=" + id);
        for (Row row : user){
            System.out.println(row);
        }
    }
    private static void SelectRating(Session session,int id) {
        ResultSet user = session.execute("select * from Rating where video_id=" + id);
        for (Row row : user){
            System.out.println(row);
        }
    }
    private static void SelectVideo_Tags(Session session, String tag) {
        ResultSet user = session.execute("select * from Video_Tags where tag='" + tag + "'");
        for (Row row : user){
            System.out.println(row);
        }
    }
}

