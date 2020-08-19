package br.svbgabriel.tables

import br.svbgabriel.tables.models.TableDefinition
import br.svbgabriel.tables.repositories.TableDefinitionRepository
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.put
import io.javalin.apibuilder.ApiBuilder.delete

fun main() {
    val tableDefinitionRepository = TableDefinitionRepository()

    val app = Javalin.create().start(7000)
    app.routes {
        get("/tables") { ctx -> ctx.json(tableDefinitionRepository.find()) }
        get("/tables/:id") { ctx ->
            val id = ctx.pathParam("id")
            ctx.json(tableDefinitionRepository.find(id)!!)
        }
        post("/tables") { ctx ->
            val tableDefinition = ctx.body<TableDefinition>()
            val response = tableDefinitionRepository.insert(tableDefinition)
            ctx.status(201)
            ctx.json(response)
        }
        put("/tables/:id") { ctx ->
            val id = ctx.pathParam("id")
            val tableDefinition = ctx.body<TableDefinition>()
            val response = tableDefinitionRepository.update(id, tableDefinition)
            ctx.status(200)
            ctx.json(response!!)
        }
        delete("/tables/:id") { ctx ->
            val id = ctx.pathParam("id")
            tableDefinitionRepository.delete(id)
            ctx.status(204)
        }
    }
}