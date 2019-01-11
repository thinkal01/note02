package com.note.common.mongodb;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.net.UnknownHostException;

/**
 * 使用java 代码来调用mongodb
 */
public class MongoDBDao {

    @Test
    public void testAdd() {
        // Mongo mongo = new Mongo("localhost", 5678);
        // DB db = mongo.getDB("test");
        MongoClient mongo = new MongoClient("localhost", 5678);
        MongoDatabase db = mongo.getDatabase("test");
        MongoCollection<Document> dbCollection = db.getCollection("person");

        Document document = new Document();
        document.put("name", "李东浩");
        document.put("desc", "天生丽质小清新");

        dbCollection.insertOne(document);
        mongo.close();
    }

    @Test
    public void testfind() {
        MongoClient mongoClient = new MongoClient("localhost", 5678);
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("person");
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            Document document = iterator.next();
            System.out.println(document.toString());
            System.out.println(document.get("name"));
        }
    }

    @Test
    public void testfindOld() {
        Mongo mongo = new Mongo("localhost", 5678);
        DB db = mongo.getDB("test");
        DBCollection dbCollection = db.getCollection("person");
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            System.out.println(dbObject.toString());
            System.out.println(dbObject.get("name"));
        }
    }

    @Test
    public void testAddOld() throws UnknownHostException {
        Mongo mongo = new Mongo("localhost", 5678);
        DB db = mongo.getDB("test");
        DBCollection dbCollection = db.getCollection("person");

        // {}
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("name", "李东浩");
        dbObject.put("desc", "天生丽质小清新");
        dbCollection.insert(dbObject);
        mongo.close();
    }
}
