package com.example.routes

import com.example.data.asignatura.AsignaturaRepository
import com.example.data.reactivo.ReactivoRepository

import io.ktor.http.*

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get


fun Route.reactivo(
    db: ReactivoRepository
){
    route("v1/reactivo"){
        post{
            val parameter = call.receive<Parameters>()

            val idTema = parameter["idTema"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val idUnidad = parameter["idUnidad"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val idReactivo = parameter["idReactivo"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val pregunta = parameter["pregunta"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val dificultad = parameter["dificultad"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val requiereProcedimiento = parameter["requiereProcedimiento"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val correcto = parameter["correcto"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )


            /*Rest of data fields*/

            try {
                val asig = db.insertInReactivo(idTema, idUnidad, idReactivo, pregunta, dificultad.toInt(), requiereProcedimiento.toBoolean(), correcto.toInt())

                asig?.idreactivo?.let {
                    call.respond(status = HttpStatusCode.OK,asig)
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }

        }


        //Get All REactivos
        get {
            try {
                val asigList = db.getAllReactivos()
                call.respond(status = HttpStatusCode.OK, asigList)
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        //Get Respuestas by Reactivos ID
        get("/{idReactivo}/respuesta") {

            val idAsignatura = db.getRespuestasByReactivoId(call.parameters["idReactivo"]!!)
            if (idAsignatura == null) {
                call.respond(HttpStatusCode.NotFound)

            } else {
                call.respond(idAsignatura)
            }
        }

        //Get reactivos by id
        get("/{idReactivo}") {

            val idReactivos = db.getReactivosById(call.parameters["idReactivo"]!!)
            if (idReactivos == null) {
                call.respond(HttpStatusCode.NotFound)

            } else {
                call.respond(idReactivos)
            }
        }

        get("/unidad/{idUnidad}/tema/{idTema}") {

            val idReactivos = db.getReactivosByUnidadTemaId(call.parameters["idUnidad"]!!,call.parameters["idTema"]!! )
            if (idReactivos == null) {
                call.respond(HttpStatusCode.NotFound)

            } else {
                call.respond(idReactivos)
            }
        }


        //Detele reactivos by id
        delete("/{idReactivo}"){
            val idReactivo = call.parameters["idReactivo"] ?: return@delete call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val result = db.deleteById(idReactivo)
            try {
                if (result == 1){
                    call.respondText("$idReactivo deleted sucessfully...")
                }else{
                    call.respondText("$idReactivo not found...")
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }


        //Detele reactivos from Examen Asociation
        delete("/{idReactivo}/examen/{idExamen}"){
            val idReactivo = call.parameters["idReactivo"] ?: return@delete call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val idExamen = call.parameters["idExamen"] ?: return@delete call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val result = db.deleteReactivoFromExamen(idReactivo, idExamen)
            try {
                if (result == 1){
                    call.respondText("$idReactivo is not part of the exam $idExamen anymore...")
                }else{
                    call.respondText("$idReactivo or $idExamen not found...")
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        put("/{idReactivo}"){
            val parameter = call.receive<Parameters>()

            val idReactivo = call.parameters["idReactivo"] ?: return@put call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val pregunta = parameter["pregunta"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val dificultad = parameter["dificultad"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val requiereProcedimiento = parameter["requiereProcedimiento"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val correcto = parameter["correcto"] ?: return@put call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            try {
                val result = db.update(idReactivo, pregunta, dificultad.toInt(), requiereProcedimiento.toBoolean(), correcto.toInt())
                if (result == 1){
                    call.respondText("$idReactivo updated sucessfully...")
                }else{
                    call.respondText("$idReactivo not found...")
                }

            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }



    }
}