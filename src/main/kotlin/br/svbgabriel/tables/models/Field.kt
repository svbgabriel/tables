package br.svbgabriel.tables.models

import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty

data class Field @BsonCreator constructor(
    @param:BsonProperty("name") val name: String,
    @param:BsonProperty("type") val type: String,
    @param:BsonProperty("required") val required: Boolean
)