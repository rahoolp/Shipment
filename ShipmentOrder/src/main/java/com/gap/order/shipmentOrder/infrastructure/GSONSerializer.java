package com.gap.order.shipmentOrder.infrastructure;

import com.gap.order.shipmentOrder.domain.ShipmentDomainEvent;
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


	public static ShipmentDomainEvent deserialize(DBObject anObject) {
		return deserialize(anObject.toString());
	} 

	public static ShipmentDomainEvent deserialize(String json) {
		Gson gson = new Gson();
		ShipmentDomainEvent shipmentDE = gson.fromJson(json, ShipmentDomainEvent.class);
		return shipmentDE;
	} 

}
