package io.millesabords.bootique.mongo.example;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.bootique.command.Command;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import io.millesabords.bootique.mongo.MongoClientFactory;

public abstract class MongoCommand extends CommandWithMetadata {

    protected Provider<MongoClientFactory> dataSource;

    public MongoCommand(Provider<MongoClientFactory> dataSource, String desc, Class<? extends Command> cls) {
        super(CommandMetadata.builder(cls).description(desc).build());
        this.dataSource = dataSource;
    }

    protected MongoCollection getCollection() {
        MongoClient mongoClient = dataSource.get().get("example");
        MongoDatabase db = mongoClient.getDatabase("testdb");
        MongoCollection collection = db.getCollection("test");

        return collection;
    }
}

