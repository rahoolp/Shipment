package com.gap.logistics.inbound.shipment.infrastratucture.persistance;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.gap.logistics.inbound.shipment.domain.Shipment;
import com.gap.logistics.inbound.shipment.domain.ShipmentRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;

public class MongoShipmentRepository implements ShipmentRepository {
	
	private DBCollection collection = null;
	
    
    private String host;
    private int port;
    private String dbName;
    private String collectionName;
    
    public MongoShipmentRepository(String host, int port, String dbName, String collectionName) throws Exception {
    	this.host = host;
    	this.port = port;
    	this.dbName = dbName;
    	this.collectionName = collectionName;
    	
		MongoClient mongo = new MongoClient(host, port);
		DB db = mongo.getDB(dbName);
		collection = db.getCollection(collectionName);
	}
	
	public Collection<Shipment> allShipments() {
		
		return null;
	}

	public void save(Shipment shipment) {
		DBObject dbObject = GSONSerializer.serialize(shipment);
		dbObject.put("_id", shipment.getShipmentId());
		collection.save(dbObject);	
	}

	public Shipment shipmentOfId(String id) {
		Shipment shipment = null;
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id", id);
		  
		DBCursor cursor = collection.find( whereQuery); 

		if (cursor.hasNext()) { 
			shipment = GSONSerializer.deserialize( cursor.next()); 
		} 
		
		return shipment;
	}

	public String nextIdentity() {
		return new ObjectId().toStringMongod();
	}
	
}
