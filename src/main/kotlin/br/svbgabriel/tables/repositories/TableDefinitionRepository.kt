package br.svbgabriel.tables.repositories

import br.svbgabriel.tables.config.Database
import br.svbgabriel.tables.config.UtilConfig
import br.svbgabriel.tables.models.TableDefinition
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import io.javalin.plugin.json.JavalinJson
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.types.ObjectId

class TableDefinitionRepository {

    private val database = JavalinJson.fromJson(UtilConfig.readConfig("database.json"), Database::class.java)

    private val mongoClient = MongoClient(MongoClientURI(database.mongoUrl))

    fun find(): List<TableDefinition> {
        return getCollection().find().toList()
    }

    fun find(_id: String): TableDefinition? {
        return getCollection().find(eq("_id", ObjectId(_id))).first()
    }

    fun insert(tableDefinition: TableDefinition): TableDefinition {
        tableDefinition.id = ObjectId()
        getCollection().insertOne(tableDefinition)
        return tableDefinition
    }

    fun update(_id: String, tableDefinition: TableDefinition): TableDefinition? {
        tableDefinition.id = ObjectId(_id)
        return getCollection().findOneAndReplace(eq("_id", ObjectId(_id)), tableDefinition)
    }

    fun delete(_id: String) {
        getCollection().deleteOne(eq("_id", ObjectId(_id)))
    }

    private fun getCollection(): MongoCollection<TableDefinition> {
        val codecRegistry = fromRegistries(
            MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )
        return mongoClient
            .getDatabase("tables")
            .withCodecRegistry(codecRegistry)
            .getCollection("tableDefinitions", TableDefinition::class.java)
    }
}
