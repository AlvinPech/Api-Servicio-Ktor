package com.example.routes

import com.example.data.tema.responseRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import io.ktor.server.routing.*
import kotlin.text.get

const val CREATE_TEMA = "v1/tema"


fun Route.tema(
    db: responseRepository
){
    route("v1/tema"){
        post{
            val parameter = call.receive<Parameters>()

            val idTema = parameter["idTema"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val nombreTema = parameter["nombreTema"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val descTema = parameter["descTema"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val idUnidad = parameter["idUnidad"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )


            /*Rest of data fields*/

            try {
                val asig = db.insert(idTema, nombreTema, descTema, idUnidad)

                asig?.idTema?.let {
                    call.respond(status = HttpStatusCode.OK,asig)
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }

        }

        //Get All Units from databe
        get {
            try {
                val asigList = db.getAllReponse()
                call.respond(status = HttpStatusCode.OK, asigList)
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        //Get unidades by unidades

        get("/{idTema}") {

            val idUnidad = db.getResponseById(call.parameters["idTema"]!!)
            if (idUnidad == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(idUnidad)
            }
        }

        //Get unidades by asignaturaid
        get("/unidad/{idUnidad}") {

            val idAsignatura = db.getResponseByForeingId(call.parameters["idUnidad"]!!)
            if (idAsignatura == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(idAsignatura)
            }
        }


        //Detele units by id
        delete("/{idTema}"){
            val idUnidad = call.parameters["idTema"] ?: return@delete call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val result = db.deleteById(idUnidad)
            try {
                if (result == 1){
                    call.respondText("$idUnidad deleted sucessfully...")
                }else{
                    call.respondText("$idUnidad not found...")
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }
    }
}