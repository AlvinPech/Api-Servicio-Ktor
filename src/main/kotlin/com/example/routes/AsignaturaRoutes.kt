package com.example.routes

import com.example.data.asignatura.AsignaturaRepository

import io.ktor.http.*

import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get


fun Route.asignatura(
    db: AsignaturaRepository
){
    route("v1/asignatura"){
        post{
            val parameter = call.receive<Parameters>()


            val idAsignatura = parameter["idAsignatura"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val nombreAsignatura = parameter["nombreAsignatura"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val descAsignatura = parameter["descAsignatura"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val idProfesor = parameter["idProfesor"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )


            /*Rest of data fields*/

            try {
                val asig = db.insert(idAsignatura, nombreAsignatura, descAsignatura, idProfesor)

                asig?.idAsignatura?.let {
                    call.respond(status = HttpStatusCode.OK,asig)
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }

        }

        //Get All Units from databe
        get {
            try {
                val asigList = db.getAllAsignaturas()
                call.respond(status = HttpStatusCode.OK, asigList)
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        //Get Asignaturas by AsignaturaId

        get("/{idAsignatura}") {

            val idAsignatura = db.getAsignaturasById(call.parameters["idAsignatura"]!!)
            if (idAsignatura == null) {
                call.respond(HttpStatusCode.NotFound)

            } else {
                call.respond(idAsignatura)
            }
        }

        //Get Asignaturas by ProfesorId
        get("/profesor/{idProfesor}") {

            val idProfesor = db.getAsignaturasByProfesorId(call.parameters["idProfesor"]!!)
            if (idProfesor == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(idProfesor)
            }
        }


        //Detele asignaturas by id
        delete("/{idAsignatura}"){
            val idAsignatura = call.parameters["idAsignatura"] ?: return@delete call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val result = db.deleteById(idAsignatura)
            try {
                if (result == 1){
                    call.respondText("$idAsignatura deleted sucessfully...")
                }else{
                    call.respondText("$idAsignatura not found...")
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        put("/{idAsignatura}"){
            val parameter = call.receive<Parameters>()

            val idAsignatura = call.parameters["idAsignatura"] ?: return@put call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val nombreAsignatura = parameter["nombreAsignatura"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val descAsignatura = parameter["descAsignatura"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val idProfesor = parameter["idProfesor"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            try {
                val result = db.update(idAsignatura, nombreAsignatura, descAsignatura, idProfesor)
                if (result == 1){
                    call.respondText("$idAsignatura updated sucessfully...")
                }else{
                    call.respondText("$idAsignatura not found...")
                }

            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

    }
}