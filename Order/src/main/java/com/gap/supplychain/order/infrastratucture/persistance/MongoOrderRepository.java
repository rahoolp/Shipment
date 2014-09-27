package com.gap.supplychain.order.infrastratucture.persistance;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.types.ObjectId;

import com.gap.supplychain.order.domain.Order;
import com.gap.supplychain.order.domain.OrderRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoOrderRepository implements OrderRepository {
	
	private DBCollection collection = null;
	
    private String host;
    private int port;
    private String dbName;
    private String collectionName;
    
    public MongoOrderRepository(String host, int port, String dbName, String collectionName) throws Exception {
    	this.host = host;
    	this.port = port;
    	this.dbName = dbName;
    	this.collectionName = collectionName;
    	
		MongoClient mongo = new MongoClient(host, port);
		DB db = mongo.getDB(dbName);
		collection = db.getCollection(collectionName);
	}
	
	public Collection<Order> allOrders() {
		Collection<Order> orders = new ArrayList<Order>();
		DBCursor cursor = collection.find();
		try {
		   while(cursor.hasNext()) {
			   Order order = GSONSerializer.deserialize( cursor.next()); 
			   orders.add(order);
		   }
		} finally {
		   cursor.close();
		}

		return orders.isEmpty() ? null : orders;
	}

	public void save(Order order) {
		DBObject dbObject = GSONSerializer.serialize(order);
		dbObject.put("_id", order.getOrderId());
		collection.save(dbObject);	
	}

	public Order orderOfId(String id) {
		Order order = null;
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id", id);
		  
		DBCursor cursor = collection.find( whereQuery); 

		if (cursor.hasNext()) { 
			order = GSONSerializer.deserialize( cursor.next()); 
		} 
		
		return order;
	}

	public String nextIdentity() {
		return new ObjectId().toStringMongod();
	}

}
