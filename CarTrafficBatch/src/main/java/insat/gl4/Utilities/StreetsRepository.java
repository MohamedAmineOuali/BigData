package insat.gl4.Utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import insat.gl4.model.Street;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json4s.JsonAST;

import java.util.ArrayList;
import java.util.Map;

public class StreetsRepository {


    String cnxStr = "mongodb://root:root@ds147589.mlab.com:47589/traffic-db";
    MongoClientURI connectionString;
    MongoClient mongoClient;
    String databaseName="traffic-db";
    String collectionName="batch-test-0";
    MongoDatabase database;
    MongoCollection<Document> collection;


    public StreetsRepository()
    {
        connectionString = new MongoClientURI(cnxStr);
        mongoClient = new MongoClient(connectionString);
        this.connect();

    }

    public StreetsRepository(String cnxStr)
    {
        this.cnxStr = cnxStr;
        connectionString = new MongoClientURI(cnxStr);
        mongoClient = new MongoClient(connectionString);
        this.connect();

    }

    public StreetsRepository(String cnxStr, String db, String collection)
    {
        this.cnxStr = cnxStr;
        connectionString = new MongoClientURI(cnxStr);
        mongoClient = new MongoClient(connectionString);
        this.databaseName = db;
        this.collectionName = collection;
        this.connect();
    }


    public void connect()
    {
        database = mongoClient.getDatabase(this.databaseName);
        collection = database.getCollection(this.collectionName);
    }

    public boolean insertStreets(Map<String,Street> streets) {


        try {

            String jsonString = JsonUtilities.convertStreetsMapToJSON(streets);

            JsonParser jsonParser = new JsonParser();
            JsonArray arrayFromString = jsonParser.parse(jsonString).getAsJsonArray();

//        collection.insertMany(arrayFromString);

            ArrayList<String> listdata = new ArrayList<String>();
            if (arrayFromString != null) {
                for (int i = 0; i < arrayFromString.size(); i++) {
                    System.out.println("*****" + listdata.add(arrayFromString.get(i).toString()));
                }
            }


            for (int i = 0; i < arrayFromString.size(); i++) {
                Document d =Document.parse(listdata.get(i));
                this.collection.insertOne(d);
            }

        }
        catch(Exception exp)
        {
            System.out.println("Il ya eu une exception pour MongoDB");
            exp.printStackTrace();
        }



//        collection.insertMany(arrayFromString);

        return true;
    }


}
