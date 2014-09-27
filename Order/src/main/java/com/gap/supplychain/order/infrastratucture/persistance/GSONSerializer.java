package com.gap.supplychain.order.infrastratucture.persistance;

import com.gap.supplychain.order.domain.Order;
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

	public static Order deserialize(DBObject anObject) {
		Gson gson = new Gson();
		Order order = gson.fromJson(anObject.toString(), Order.class);
		return order;
	} 
	
}
	