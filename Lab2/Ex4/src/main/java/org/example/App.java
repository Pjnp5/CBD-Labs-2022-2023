package org.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.MongoServerException;
import com.mongodb.client.FindIterable;

import static java.util.Arrays.asList;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App 
{
    // function to insert a restaurante
    public static int insert_restaurant( MongoCollection<Document> database) {
        String building,  rua,zipcode,  localidade,  gastronomia,  nome;
        double longitude, latitude ;
        String id;
        Scanner sc = new Scanner(System.in);

        System.out.println("Building:");
        building=sc.nextLine().trim();

        System.out.println("Rua:");
        rua=sc.nextLine().trim();

        System.out.println("Zipcode:");
        zipcode=sc.nextLine().trim();

        System.out.println("Localidade:");
        localidade = sc.nextLine().trim();

        System.out.println("Gastronomia:");
        gastronomia=sc.nextLine().trim();

        System.out.println("Nome:");
        nome=sc.nextLine().trim();

        System.out.println("Latitude:");
        latitude=Double.parseDouble(sc.nextLine());

        System.out.println("Longitude:");
        longitude=Double.parseDouble(sc.nextLine());

        System.out.println("Restaurant ID:");
        id=(sc.nextLine().trim());


        Document restaurant = new Document("address", new Document("building", building)
                .append("coord", asList(latitude, longitude))
                .append("rua", rua)
                .append("zipcode", zipcode))
                .append("localidade", localidade)
                .append("gastronomia", gastronomia)
                .append("nome", nome)
                .append("restaurant_id", id);

        Document grades= new Document();
        boolean ctr = false;
        String has_grade;

        while (true){
            System.out.println("Do they have a grade? Y or N ");
            has_grade = sc.nextLine().toUpperCase();
            if (has_grade.equals("Y")){
                System.out.println("Grade:");
                String grade = sc.nextLine().trim();
                System.out.println("Date:");
                String date = sc.nextLine().trim();
                System.out.println("Score:");
                String score = sc.nextLine().trim();
                grades.append("grade", grade);
                grades.append("date", date);
                grades.append("score", score);
                restaurant.append("grades", grades);
                ctr = true;

            }
            else{
                if (!ctr){
                    restaurant.append("grades", Collections.emptyList());
                }
                break;
            }
        }
        System.out.println(restaurant.toJson());
        try{
            InsertOneResult restaurant_info = database.insertOne(restaurant);
            System.out.println("Success! New restaurant inserted, ID: " + restaurant_info.getInsertedId());
        }
        catch (MongoException error) {
            System.err.println("Unable to insert due to an error: " + error);
        }
        sc.close();
        return 1;
    }

    public static FindIterable<Document> find( MongoCollection<Document> collection,Bson bson ){
        return collection.find(bson);

    }

    public static int update_value(MongoCollection<Document> collection, Bson old_value, Bson new_value){
        UpdateResult update = collection.updateMany(old_value, new_value);
        System.out.println("Modified document count: " + update.getModifiedCount());
        return (int) update.getModifiedCount();

    }


    public static int countLocalidades(MongoCollection<Document> restaurants){
        AtomicInteger count = new AtomicInteger();
        restaurants.aggregate(Collections.singletonList(Aggregates.group("$localidade"))).forEach(res -> count.getAndIncrement());
        return count.get();
    }

    public static Map<String, Integer> countRestByLocalidade(MongoCollection<Document> restaurants) {
        Map<String, Integer> my_map = new HashMap<>();
        restaurants.aggregate(Collections.singletonList(Aggregates.group("$localidade", Accumulators.sum("Número de restaurantes", 1)))).forEach(rest -> my_map.put(""+rest.get("_id"),Integer.parseInt(""+rest.get("Número de restaurantes"))));

        return my_map;
    }

    public static  List<String> getRestWithNameCloserTo(MongoCollection<Document> restaurants,String name){
        List<String> my_list = new ArrayList<>();
        restaurants.find(Filters.text(name)).projection(Projections.fields(Projections.include("nome"), Projections.exclude("_id"))).forEach(a -> my_list.add((String) a.get("nome")));
        return my_list;
    }


    public static void main( String[] args )
    {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("cbd");

            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println(commandResult);
                System.out.println("Connected successfully to server.");
                // getting the restaurants data to "collection"
                MongoCollection<Document> restaurants = database.getCollection("restaurants");
//                // a) Desenvolva um programa simples que permita testar inserção, edição e pesquisa de registos sobre a coleção.
//
//                // Add a new restaurant
//                //insert_restaurant(restaurants);
//
//                //Find on the database
//                FindIterable<Document> procura_res = find(restaurants,eq("nome", "Restaurante do magro")); // started as "Restaurante do gordo" the changed the name to see the rest of the attributes
//                for (Document document : procura_res){
//                    System.out.println(document.toJson());
//                }
//
//                // Update a document in database
//
//                Bson old_value = eq("nome", "Restaurante do gordo");
//                Bson new_value = Updates.combine(Updates.set("nome", "Restaurante do magro"));
//                update_value(restaurants, old_value, new_value);


                // b) Crie índices: um para localidade; outro para gastronomia; e um de texto para o nome.
                // Use pesquisas para testar o funcionamento e o verifique o desempenho (como são poucos documento os resultados poderão não melhorar).


//                long no_index_start = System.nanoTime();
//
//                find(restaurants, eq("nome", "Restaurante do magro"));
//                find(restaurants, eq("gastronomia", "Portuguesa"));
//                find(restaurants, eq("localidade", "Bronx"));
//
//                long no_index_time = System.nanoTime() - no_index_start;
//
//                // create indexes
//
//                restaurants.createIndex(Indexes.ascending("localidade"));
//                restaurants.createIndex(Indexes.ascending("gastronomia"));
//                restaurants.createIndex(Indexes.text("nome"));
//
//                long index_start = System.nanoTime();
//
//                find(restaurants, eq("nome", "Restaurante do magro"));
//                find(restaurants, eq("gastronomia", "Portuguesa"));
//                find(restaurants, eq("localidade", "Bronx"));
//
//                long index_time = System.nanoTime() - index_start;
//
//                System.out.println("No index time: " + no_index_time);
//                System.out.println("Index time: " + index_time);
//                if (index_time < no_index_time) {System.out.println("Index is quicker");}
//                else{System.out.println("Index is slower");}


                // c) Selecione 5 perguntas/comandos do exercício 2.2 e reimplemente-os em Java.

                //6. Liste todos os restaurantes que tenham pelo menos um score superior a 85.

                //db.restaurants.find({'grades.score': {$gt: 85}})

//                Bson filters = Filters.gt("grades.score", 85);
//                for (Document document : restaurants.find(filters).projection(Projections.fields(Projections.include("nome", "grades.score"), Projections.exclude("_id")))) {
//                    System.out.println(document);
//                }


                //13. Liste o nome, a localidade, o score e gastronomia dos restaurantes que alcançaram sempre pontuações inferiores ou igual a 3.

                //db.restaurants.find({'grades.score': {$lte: 3}}, {nome: 1, localidade: 1, score: 1, gastronomia: 1})
//                Bson filters2= Filters.lte("grades.score", 3);
//                for (Document document : restaurants.find(filters2).projection(Projections.fields(Projections.include("nome", "localidade", "grades.score", "gastronomia"), Projections.exclude("_id")))) {
//                    System.out.println(document);
//                }

                //16. Liste o restaurant_id, o nome, o endereço (address) e as coordenadas geográficas (coord) dos restaurantes onde o 2o elemento da matriz de coordenadas tem um valor superior a 42 e inferior ou igual a 52.

                //db.restaurants.find({$and: [{'address.coord.1': {$gt : 42}}, {'address.coord.0': {$lte : 52}}]}, {restaurant_id: 1, nome: 1, 'address': 1})

//                Bson filters3 = Filters.and(Filters.gt("address.coord.1", 42),
//                        Filters.lt("grades.score",52));
//                MongoCursor<Document> cursor;
//                cursor= restaurants.find(filters3).projection(Projections.fields(Projections.include("restaurant_id","nome","address.rua","address.coord"),Projections.exclude("_id"))).iterator();
//                while(cursor.hasNext()){
//                    Document c= cursor.next();
//                    System.out.println(c);
//                }

                //17. Liste nome, gastronomia e localidade de todos os restaurantes ordenando por ordem crescente da gastronomia e, em segundo, por ordem decrescente de localidade.
                //
                //db.restaurants.find({} , {nome: 1, gastronomia: 1, localidade: 1}).sort({gastronomia: 1, localidade: -1})

//                Bson filters4 = Sorts.orderBy(Sorts.ascending("gastronomia"), Sorts.descending("localidade"));
//                for (Document document : restaurants.find().projection(Projections.fields(Projections.include("nome", "localidade", "gastronomia"), Projections.exclude("_id"))).sort(filters4)) {
//                    System.out.println(document);
//                }

                //26. Quantos restaurantes possuem 'Pizza' no nome?

                //db.restaurants.aggregate([{$match: {nome: {$regex: 'Restaurant*'}}},{$count: 'no_restaurants'}])

//                restaurants.aggregate(asList(Aggregates.match(Filters.regex("nome", "Pizza")), Aggregates.group("Pizza_Count", Accumulators.sum("count", 1)))).forEach(res -> System.out.println(res.toJson()));

                // Alinea D

                // countLocalidades
                System.out.println("\ncountLocalidades: ");
                System.out.println("Número de localidades distintas: " + countLocalidades(restaurants));

                //countRestByLocalidade
                System.out.println("\ncountRestByLocalidade: ");
                System.out.println(countRestByLocalidade(restaurants));

                //getRestWithNameCloserTo

                System.out.println("\ngetRestWithNameCloserTo: ");
                getRestWithNameCloserTo(restaurants, "Park").forEach(System.out::println);

            }catch (MongoServerException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }

        }catch (MongoServerException me) {
            System.err.println("Impossivel ligar à base de dados");
        }


    }
}
