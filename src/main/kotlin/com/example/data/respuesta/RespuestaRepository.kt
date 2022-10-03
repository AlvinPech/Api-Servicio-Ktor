package com.example.data.respuesta

import com.example.dao.AsignaturaDao
import com.example.dao.ReactivoDao
import com.example.dao.RespuestaDao
import com.example.data.respuesta.Respuesta
import com.example.repository.DatabaseFactory
import com.example.repository.relationTables.RespuestasdereactivoTable
import com.example.repository.relationTables.TemasdereactivoTable
import com.example.repository.tables.*
import org.jetbrains.exposed.sql.*
import com.example.repository.tables.RespuestaTable
import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class RespuestaRepository : RespuestaDao {

    override suspend fun insert(idReactivo: String, orden: Int, idRespuesta: String, respuestaString: String, esCorrecto: Boolean, idsigreactivo: String): Respuesta? {
        var statement:InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = RespuestaTable.insert { respuesta ->
                respuesta[RespuestaTable.idrespuesta] = idRespuesta
                respuesta[RespuestaTable.respuesta] = respuestaString
                respuesta[RespuestaTable.esCorrecto] = esCorrecto
                respuesta[RespuestaTable.idsigreactivo] = idsigreactivo
            }
        }

        var statement2:InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement2 = RespuestasdereactivoTable.insert { respReactivo ->
                respReactivo[RespuestasdereactivoTable.idreactivo] = idReactivo
                respReactivo[RespuestasdereactivoTable.idrespuesta] = idRespuesta
                respReactivo[RespuestasdereactivoTable.orden] = orden
            }
        }

        return rowToAnswer(statement?.resultedValues?.get(0))
    }

    override suspend fun getAllRespuestas(): List<Respuesta> =
        DatabaseFactory.dbQuery {
            RespuestaTable.selectAll().mapNotNull {
                rowToAnswer(it)
            }
        }

    override suspend fun getRespuestasById(idRespuesta: String): Respuesta? =
        DatabaseFactory.dbQuery {
            RespuestaTable.select { RespuestaTable.idrespuesta.eq(idRespuesta) }
                .map {
                    rowToAnswer(it)
                }.singleOrNull()
        }

    override suspend fun deleteById(idRespuesta: String): Int =
        DatabaseFactory.dbQuery {
            RespuestaTable.deleteWhere { RespuestaTable.idrespuesta.eq(idRespuesta) }
        }

    override suspend fun update(idRespuesta: String, respuesta: String, esCorrecto: Boolean, idsigreactivo: String): Int {
        TODO("Not yet implemented")
    }


    private fun rowToAnswer(row:ResultRow?) : Respuesta? {
        if(row == null){ return null }
        return Respuesta(
            idrespuesta = row[RespuestaTable.idrespuesta],
            respuesta = row[RespuestaTable.respuesta],
            esCorrecto = row[RespuestaTable.esCorrecto],
            idsigreactivo = row[RespuestaTable.idsigreactivo]
        )
    }


}
