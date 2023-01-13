package org.example;

import redis.clients.jedis.Jedis;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        users_set Set = new users_set();
        users_list List= new users_list();
        users_Hash Hash = new users_Hash();

        Map<String, String> users_hashmap = new HashMap<>();

        int nr_user = 1;

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome! \n" +
                    "1 - Create User \n" +
                    "2 - Send Message \n" +
                    "3 - Add User to friends \n" +
                    "4 - Check user Messages");
            int input = sc.nextInt();

            if (input > 5 || 0 > input) break;

            switch (input) {
                case 1:
                    Scanner sc_user = new Scanner(System.in);
                    System.out.println("Which name will you choose?");
                    String username = sc_user.nextLine();

                    if (!users_hashmap.containsKey(username)) {
                        users_hashmap.put("user" + nr_user, username);
                        Hash.Add_User_to_hash(users_hashmap);
                        nr_user++;
                    }
                    System.out.println(Hash.getUserHashMap());
                    break;
                case 2:
                    Scanner sc_which_user = new Scanner(System.in);
                    Scanner sc_user_message = new Scanner(System.in);

                    System.out.println(Hash.getUserHashMap());

                    System.out.println("Your user name:");
                    String user = sc_which_user.nextLine();

                    System.out.println("Message:");
                    String message = sc_user_message.nextLine();

                    List.Add_User_to_list(user, message);
                    System.out.println(List.getUserList(user));
                    break;
                case 3:

                    Scanner sc2 = new Scanner(System.in);

                    System.out.println(Hash.getUserHashMap());

                    System.out.println("Your username: ");
                    String current_user = sc2.nextLine();

                    System.out.println(Hash.getUserHashMap());

                    System.out.println("User to subscribe? ");
                    String user2subscribe = sc2.nextLine();

                    Set.Add_User_to_Set(current_user, user2subscribe);
                    break;

                case 4:
                    Scanner c_user = new Scanner(System.in);

                    System.out.println("Your username: ");
                    String current_user2 = c_user.nextLine();

                    Set<String> following = Set.getFollowerSet(current_user2);
                    if (following.size() == 0)
                        System.out.println("You don't follow anyone");
                    else {
                        for (String s_u: following) {
                            System.out.println("Message from " + s_u + ":");
                            List<String> messages = List.getUserList(s_u);
                            for(String messagee: messages)
                                System.out.println(messagee);
                        }

                    }
                    break;
            }
        }

    }
}

class users_set {
    private final Jedis jedis;

    public users_set() {
        this.jedis = new Jedis();
    }

    public void Add_User_to_Set(String username, String following_user) {
        jedis.sadd("following:" + username, following_user);
    }

    public Set<String> getFollowerSet(String username) {
        return jedis.smembers("following:" + username);
    }

    public Set<String> getAllKeys() {
        return jedis.keys("*");
    }
}

class users_Hash {
    private final Jedis jedis;
    public static String USERS = "users3";

    public users_Hash() {
        this.jedis = new Jedis();
    }

    public void Add_User_to_hash(Map<String, String> Users_map) { jedis.hmset(USERS, Users_map); }

    public Map<String, String> getUserHashMap() { return jedis.hgetAll(USERS); }

    public Set<String> ListKeys() { return jedis.keys("*"); }
}

class users_list {
    private final Jedis jedis;

    public users_list() {
        this.jedis = new Jedis();
    }

    public void Add_User_to_list(String user, String username) { jedis.lpush(user, username); }

    public List<String> getUserList(String user) { return jedis.lrange(user, 0, -1); }

    public Set<String> ListKeys() { return jedis.keys("*"); }
}