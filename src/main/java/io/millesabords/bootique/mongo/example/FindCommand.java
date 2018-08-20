package io.millesabords.bootique.mongo.example;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.millesabords.bootique.mongo.MongoClientFactory;
import org.bson.Document;

public class FindCommand extends MongoCommand {

    @Inject
    public FindCommand(Provider<MongoClientFactory> dataSource) {
        super(dataSource, "Command to find data", FindCommand.class);
    }

    @Override
    public CommandOutcome run(Cli cli) {
        MongoCollection collection = getCollection();

        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };

        collection.find(new Document("hello", "world")).forEach((Block<Document>) System.out::println);

        return CommandOutcome.succeeded();
    }
}

