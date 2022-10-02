package com.example.routes

import com.example.data.asignatura.AsignaturaRepository
import com.example.data.profesor.ProfesorRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get



fun Route.profesor(
    db: ProfesorRepository
){
    route("v1/profesor"){
        post{
            val parameter = call.receive<Parameters>()

            val idProfesor = parameter["idProfesor"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val correo = parameter["correo"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )
            val nombre = parameter["nombre"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val apellidoPaterno = parameter["apellidoPaterno"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val apellidoMaterno = parameter["apellidoMaterno"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )

            val contrasenia = parameter["contrasenia"] ?: return@post call.respondText(
                "MISSING FIELD",
                status = HttpStatusCode.Unauthorized
            )


            /*Rest of data fields*/

            try {
                val asig = db.insert(idProfesor, correo, nombre, apellidoPaterno, apellidoMaterno, contrasenia)

                asig?.idProfesor?.let {
                    call.respond(status = HttpStatusCode.OK,asig)
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }

        }

        //Get All Units from databe
        get{
            try {
                val asigList = db.getAllProfesores()
                call.respond(status = HttpStatusCode.OK, asigList)
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }

        //Get Asignaturas by AsignaturaId

        get("/{idProfesor}") {

            val idProfesor = db.getProfesoresById(call.parameters["idProfesor"]!!)
            if (idProfesor == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(idProfesor)
            }
        }


        //Detele units by id
        delete("/{idProfesor}"){
            val idProfesor = call.parameters["idProfesor"] ?: return@delete call.respondText(
                "NO ID",
                status = HttpStatusCode.Unauthorized
            )

            val result = db.deleteById(idProfesor)
            try {
                if (result == 1){
                    call.respondText("$idProfesor deleted sucessfully...")
                }else{
                    call.respondText("$idProfesor not found...")
                }
            }catch (e:Throwable){
                call.respondText("${e.message}")
            }
        }
    }
}