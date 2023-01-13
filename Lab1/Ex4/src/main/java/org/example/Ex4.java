package org.example;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import redis.clients.jedis.Jedis;

import java.util.Scanner;

public class Ex4 {

    private Jedis jedis;

    public Ex4() {
        this.jedis = new Jedis();
    }

    public List<String> getNamesA() {
        return jedis.lrange("NAMES", 0, -1);
    }

    private static Map<String, String> ordenarHashmap(Map<String, String> mapa)
    {
        List<Map.Entry<String,String>> list = new LinkedList<Map.Entry<String,String>>(mapa.entrySet());
        Collections.sort(list, new Comparator<Entry<String, String>>()
        {
            public int compare(Entry<String, String> str1, Entry<String, String> str2) {
                Integer num1 = Integer.parseInt(str1.getValue());
                Integer num2 = Integer.parseInt(str2.getValue());
                return num2.compareTo(num1);
            }
        });

        Map<String, String> ordenado = new LinkedHashMap<String, String>();
        for (Entry<String, String> entry : list)
        {
            ordenado.put(entry.getKey(), entry.getValue());
        }
        return ordenado;
    }

    public Map<String, String> getNamesB() {
        return jedis.hgetAll("NAME_POPULARITY");
    }

    public static void main(String[] args) throws IOException {

        // a)
        System.out.println("Starting a)");
        Ex4 autocomplete = new Ex4();

        try {
            Scanner sc = new Scanner(new File("./names.txt"));
            while (sc.hasNextLine()) {
                String name = sc.nextLine();
                autocomplete.jedis.rpush("NAMES",name);
            }
            sc.close();

            System.out.print("Enter name: ");
            sc = new Scanner(System.in);
            String name = sc.next().toLowerCase();

            if(name.length() == 0)
                System.exit(1);

            ArrayList<String> autocomplete_array = new ArrayList<>();

            for (String n : autocomplete.getNamesA())  {
                if (n.startsWith(name)) {
                    autocomplete_array.add(n);
                }
            }

            for (String result : autocomplete_array) {
                System.out.println(result);
            }
            System.out.println();

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("Staring b)");

        Ex4 popularity = new Ex4();

        try {
            HashMap<String,String> mapa = new HashMap<>();
            Scanner sc = new Scanner(new File("./nomes-pt-2021.csv"));
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(";");
                String name = line[0];
                String counter = line[1];
                mapa.put(name,counter);
            }

            popularity.jedis.hmset("NAME_POPULARITY", mapa);
            sc.close();

            System.out.print("Enter a name: ");
            sc = new Scanner(System.in);
            final String input = sc.next().toLowerCase();

            Map<String,String> map = popularity.getNamesB();
            Map<String, String> hashOrdenado = ordenarHashmap(map);

            for (Map.Entry<String,String> entrada : hashOrdenado.entrySet()){
                String nome = entrada.getKey().toLowerCase();
                if (nome.startsWith(input)) {
                    System.out.println(entrada.getKey());
                }
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }



    }
}