package com.example.plugins

import com.example.data.asignatura.AsignaturaRepository
import com.example.data.profesor.ProfesorRepository
import com.example.data.reactivo.ReactivoRepository
import com.example.data.respuesta.RespuestaRepository
import com.example.data.tema.responseRepository
import com.example.data.unidad.UnidadRepository
import com.example.repository.DatabaseFactory
import com.example.routes.*

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    DatabaseFactory.init()
    val db = AsignaturaRepository()
    val unidadRepository = UnidadRepository()
    val temaRepository = responseRepository()
    val profesorRepository = ProfesorRepository()
    val reactivoRepository = ReactivoRepository()
    val respuestaRepository = RespuestaRepository()

    routing {
        asignatura(db)
        unidad(unidadRepository)
        tema(temaRepository)
        profesor(profesorRepository)
        reactivo(reactivoRepository)
        respuesta(respuestaRepository)

    }

    //const val API_VERSION = "v1/"


}
