import com.mongodb.*;
import com.mongodb.connection.*;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.Arrays;

public class MongoDBExample {
	public static void main(String[] args) {
		// connect to database
		MongoCredential credential = MongoCredential.createCredential(
				"root", "admin", "r8wp56zg".toCharArray());


		MongoClientSettings settings = MongoClientSettings.builder()
			.credential(credential)
			.applyToClusterSettings( builder -> {
				builder.hosts(Arrays.asList(
							new ServerAddress("172.31.19.121", 31826),
							new ServerAddress("172.31.36.59", 31826),
							new ServerAddress("172.31.45.238", 31826)))
					.mode(ClusterConnectionMode.MULTIPLE);
			})
		.build();


		MongoClient mongoClient = MongoClients.create(settings);

		MongoDatabase database = mongoClient.getDatabase("mydb");
		MongoCollection<Document> collection = database.getCollection("mycollection");

		// insert a doc
		Document document = new Document("name", "John Doe");
		collection.insertOne(document);

		// get the doc 
		FindIterable<Document> iterable = collection.find();
		for (Document doc : iterable) {
			System.out.println(doc.toJson());
		}

		// update the doc
		collection.updateOne(Filters.eq("name", "John Doe"), Updates.set("age", 30));

		// delete the doc
		collection.deleteOne(Filters.eq("name", "John Doe"));

		// close the connection
		mongoClient.close();
	}
}

