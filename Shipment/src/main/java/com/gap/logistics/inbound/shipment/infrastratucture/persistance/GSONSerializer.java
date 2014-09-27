package com.gap.logistics.inbound.shipment.infrastratucture.persistance;

import com.gap.logistics.inbound.shipment.domain.Shipment;
import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class GSONSerializer {
	
	public static DBObject serialize(Object anObject) { 
		Gson gson = new Gson();
		String jsonString = gson.toJson(anObject);
		DBObject dbObject = (DBObject)JSON.parse(jsonString);
		return dbObject; 
	} 

	public static Shipment deserialize(DBObject anObject) {
		Gson gson = new Gson();
		Shipment shipment = gson.fromJson(anObject.toString(), Shipment.class);
		return shipment;
	} 
	
}
