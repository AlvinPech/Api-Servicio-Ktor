package com.example.routes

import com.example.data.unidad.UnidadRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import io.ktor.server.routing.*
import kotlin.text.get



fun Route.unidad(
    db: UnidadRepository
){
    route("v1/unidad"){
        post{
            val parameter = call.receive<Parameters>()

            val idUnidad = parameter["idUnidad"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val nombreUnidad = parameter["nombreUnidad"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val descUnidad = parameter["descUnidad"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val idAsignatura = parameter["idAsignatura"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )


            /*Rest of data fields*/

            try {
                val asig = db.insert(idUnidad, nombreUnidad, descUnidad, idAsignatura)

                asig?.idUnidades?.let {
                    call.respond(status = HttpStatusCode.OK,asig)
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }

        }

        //Get All Units from databe
        get{
            try {
                val asigList = db.getAllUnits()
                call.respond(status = HttpStatusCode.OK, asigList)
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        //Get unidades by unidades

        get("/{idUnidades}") {

            val idUnidad = db.getUnitsById(call.parameters["idUnidades"]!!)
            if (idUnidad == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(idUnidad)
            }
        }

        //Get unidades by asignaturaid
        get("/asignatura/{idAsignatura}") {

            val idAsignatura = db.getUnitsByAsignaturaId(call.parameters["idAsignatura"]!!)
            if (idAsignatura == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(idAsignatura)
            }
        }


        //Detele units by id
        delete("/{idUnidad}"){
            val idUnidad = call.parameters["idUnidad"] ?: return@delete call.respondText(
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