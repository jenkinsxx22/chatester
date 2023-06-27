package com.example.demo2.services;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;

import com.example.demo2.models.User;

public class UserService {

	private  String dbName = "chatester";
	private  String collectionName = "users";
	private String mongConnection="mongodb+srv://yoda:T2haQgiuwLLuewxk@cluster0.3ekyzcw.mongodb.net/?retryWrites=true&w=majority";
	private MongoClientSettings settings;
	private  MongoClient mongoClient = null;
	
	public boolean Connect() {
		boolean chk=false;
		
		try {
		Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.WARNING);
	    ConnectionString mongoUri = new ConnectionString(mongConnection);
	    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
	            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	    settings = MongoClientSettings.builder()
	            .codecRegistry(pojoCodecRegistry)
	            .applyConnectionString(mongoUri).build();

	   
	    try {
	       mongoClient = MongoClients.create(settings);
	       chk=true;
	    } catch (MongoException me) {
	      System.err.println("Unable to connect to the MongoDB instance due to an error: " + me);
	      chk=false;
//	      System.exit(1);
	    }
	    
		}
	    catch(Exception e) {
	    	chk=false;
	    }
	    return chk;
	}
	public void SaveUser(User user) {
		MongoDatabase database = mongoClient.getDatabase(dbName);
		
		MongoCollection<User> collection = database.getCollection(collectionName, User.class);
	    try {
	        // recipes is a static variable defined above
	        InsertOneResult result = collection.insertOne(user);
	        System.out.println("Inserted " + result.getInsertedId() + " documents.\n");
	      } catch (MongoException me) {
	        System.err.println("Unable to insert any recipes into MongoDB due to an error: " + me);
	        System.exit(1);
	      }
	}
	public User findUserByEmail(String email) {
		User firstUser = new User();
		MongoDatabase database = mongoClient.getDatabase(dbName);		
		MongoCollection<User> collection = database.getCollection(collectionName, User.class);
		
	    Bson findUser = Filters.eq("email", email);
	    try {
	    	 firstUser = collection.find(findUser).first();
	      if (firstUser == null) {
	        System.out.println("Couldn't find any user in MongoDB.");
	      //  System.exit(1);
	      }
	    } catch (MongoException me) {
	      System.err.println("Unable to find a user to update in MongoDB due to an error: " + me);
	      System.exit(1);
	    }
	    
		return firstUser;
	}
 
	public void uploadProfilePic(User user) {
		User firstUser = new User();
		MongoDatabase database = mongoClient.getDatabase(dbName);		
		MongoCollection<User> collection = database.getCollection(collectionName, User.class);
		Bson updateFilter = Updates.set("profilepic",user.getProfilepic() );
		Bson findUser = Filters.eq("email", user.getEmail());
		    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
		    try {
		      User updatedDocument = collection.findOneAndUpdate(findUser, updateFilter, options);
		      if (updatedDocument == null) {
		        System.out.println("Couldn't update the recipe. Did someone (or something) delete it?");
		      } else {
		        System.out.println("\nUpdated the user to: " + updatedDocument);
		      }
		    } catch (MongoException me) {
		      System.err.println("Unable to update any user profile due to an error: " + me);
		    }		
	}
}
