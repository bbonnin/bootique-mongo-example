package io.millesabords.bootique.mongo.example;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.client.MongoCollection;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.millesabords.bootique.mongo.MongoClientFactory;
import org.bson.Document;

public class InsertCommand extends MongoCommand {

    @Inject
    public InsertCommand(Provider<MongoClientFactory> dataSource) {
        super(dataSource, "Command to insert data", InsertCommand.class);
    }

    @Override
    public CommandOutcome run(Cli cli) {
        MongoCollection collection = getCollection();
        collection.insertOne(new Document("hello", "world"));

        return CommandOutcome.succeeded();
    }
}
