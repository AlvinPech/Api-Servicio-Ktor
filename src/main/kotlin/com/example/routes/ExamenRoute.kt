package com.example.routes

import com.example.data.asignatura.AsignaturaRepository
import com.example.data.examen.ExamenRepository
import com.example.data.reactivo.ReactivoRepository

import io.ktor.http.*

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get


fun Route.examen(
    db: ExamenRepository
){
    route("v1/examen"){
        post{
            val parameter = call.receive<Parameters>()

            val idExamen = parameter["idExamen"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val nombre = parameter["nombre"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val descripcion = parameter["descripcion"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val idAsignatura = parameter["idAsignatura"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val tiempo = parameter["tiempo"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )


            try {
                val asig = db.insert(idExamen, nombre, descripcion, idAsignatura, tiempo.toInt())

                asig?.idExamen?.let {
                    call.respond(status = HttpStatusCode.OK,asig)
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }

        }


        //Get All REactivos
        get {
            try {
                val asigList = db.getAllExamenes()
                call.respond(status = HttpStatusCode.OK, asigList)
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        //Get Respuestas by Reactivos ID
        get("/{idExamen}/reactivos") {

            val idExamen = db.getReactivosByExamenId(call.parameters["idExamen"]!!)
            if (idExamen == null) {
                call.respond(HttpStatusCode.NotFound)

            } else {
                call.respond(idExamen)
            }
        }

        //Get reactivos by id
        get("/{idExamen}") {

            val idExamen = db.getExamenesById(call.parameters["idExamen"]!!)
            if (idExamen == null) {
                call.respond(HttpStatusCode.NotFound)

            } else {
                call.respond(idExamen)
            }
        }


        //Detele reactivos by id
        delete("/{idExamen}"){
            val idExamen = call.parameters["idExamen"] ?: return@delete call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val result = db.deleteById(idExamen)
            try {
                if (result == 1){
                    call.respondText("$idExamen deleted sucessfully...")
                }else{
                    call.respondText("$idExamen not found...")
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }


        route("/asigReactivo"){

            post{
                val parameter = call.receive<Parameters>()

                val idExamen = parameter["idExamen"] ?: return@post call.respondText(
                    "MISSING FIELD",
                    status = HttpStatusCode.Unauthorized
                )
                val idReactivo = parameter["idReactivo"] ?: return@post call.respondText(
                    "MISSING FIELD",
                    status = HttpStatusCode.Unauthorized
                )

                val orden = parameter["orden"] ?: return@post call.respondText(
                    "MISSING FIELD",
                    status = HttpStatusCode.Unauthorized
                )

                try {
                    val asig = db.addReactivoToExam(idExamen, idReactivo, orden.toInt())

                    asig?.idExamen?.let {
                        call.respond(status = HttpStatusCode.OK,asig)
                    }
                }catch (e:Throwable){
                    call.respondText("${e.message}")
                }

            }


        }



    }
}