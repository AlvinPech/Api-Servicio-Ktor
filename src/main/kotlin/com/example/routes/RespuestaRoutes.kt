package com.example.routes

import com.example.data.asignatura.AsignaturaRepository
import com.example.data.respuesta.RespuestaRepository

import io.ktor.http.*

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get


fun Route.respuesta(
    db: RespuestaRepository
){
    route("v1/respuesta"){
        post{
            val parameter = call.receive<Parameters>()


            val idReactivo = parameter["idReactivo"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val orden = parameter["orden"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val idRespuesta = parameter["idRespuesta"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val respuestaString = parameter["respuestaString"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val esCorrecto = parameter["esCorrecto"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val idsigreactivo = parameter["idsigreactivo"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )


            /*Rest of data fields*/

            try {
                val asig = db.insert(idReactivo, orden.toInt(), idRespuesta, respuestaString, esCorrecto.toBoolean(), idsigreactivo)

                asig?.idrespuesta?.let {
                    call.respond(status = HttpStatusCode.OK,asig)
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }

        }

        //Get All Respuestas from databe
        get {
            try {
                val asigList = db.getAllRespuestas()
                call.respond(status = HttpStatusCode.OK, asigList)
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        //Get Respuestas by Respuestas

        get("/{idRespuesta}") {

            val idRespuesta = db.getRespuestasById(call.parameters["idRespuesta"]!!)
            if (idRespuesta == null) {
                call.respond(HttpStatusCode.NotFound)

            } else {
                call.respond(idRespuesta)
            }
        }


        //Detele asignaturas by id
        delete("/{idRespuesta}"){
            val idRespuesta = call.parameters["idRespuesta"] ?: return@delete call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val result = db.deleteById(idRespuesta)
            try {
                if (result == 1){
                    call.respondText("$idRespuesta deleted sucessfully...")
                }else{
                    call.respondText("$idRespuesta not found...")
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        put("/{idRespuesta}"){
            val parameter = call.receive<Parameters>()

            val idRespuesta = call.parameters["idRespuesta"] ?: return@put call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val respuestaString = parameter["respuestaString"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val esCorrecto = parameter["esCorrecto"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val idsigreactivo = parameter["idsigreactivo"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            try {
                val result = db.update(idRespuesta, respuestaString, esCorrecto.toBoolean(), idsigreactivo)
                if (result == 1){
                    call.respondText("$idRespuesta updated sucessfully...")
                }else{
                    call.respondText("$idRespuesta not found...")
                }

            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

    }
}