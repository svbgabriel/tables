package br.svbgabriel.tables.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

data class TableDefinition @BsonCreator constructor(
    @JsonIgnore @BsonId var id: ObjectId?,
    @param:BsonProperty("name") val name: String,
    @param:BsonProperty("fields") val fields: List<Field>
) {
    val _id: String
        get() = id.toString()
}