package com.example.data.reactivo

import com.example.dao.AsignaturaDao
import com.example.dao.ReactivoDao
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

class ReactivoRepository : ReactivoDao {



    override suspend fun insertInReactivo(idTema:String, idUnidad:String, idReactivo: String, pregunta: String, dificultad: Int, requiereProcedimiento: Boolean): Reactivo? {

        var statement:InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = ReactivoTable.insert { reactivo ->
                reactivo[ReactivoTable.idreactivo] = idReactivo
                reactivo[ReactivoTable.pregunta] = pregunta
                reactivo[ReactivoTable.dificultad] = dificultad
                reactivo[ReactivoTable.requiereProcedimiento] = requiereProcedimiento

                //insert rest data
            }
        }

        var statement2:InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement2 = TemasdereactivoTable.insert { temasreactivo ->
                temasreactivo[TemasdereactivoTable.idreactivo] = idReactivo
                temasreactivo[TemasdereactivoTable.idtema] = idTema
                temasreactivo[TemasdereactivoTable.idunidad] = idUnidad
            }
        }

        return rowToAsig(statement?.resultedValues?.get(0))
    }


    override suspend fun getAllReactivos(): List<ReactivoResponse> =
        DatabaseFactory.dbQuery {
            TemasdereactivoTable.join(ReactivoTable, JoinType.INNER, additionalConstraint =
            {TemasdereactivoTable.idreactivo eq ReactivoTable.idreactivo})
                .join(UnidadTable, JoinType.INNER, additionalConstraint =
                {TemasdereactivoTable.idunidad eq UnidadTable.idUnidad})
                .join(TemaTable, JoinType.INNER, additionalConstraint =
                {TemasdereactivoTable.idtema eq TemaTable.idTema})

                .selectAll().mapNotNull { reactivoRow ->
                    runBlocking {
                        rowToResponse(reactivoRow)
                    }

                }
        }

    override suspend fun getReactivosById(idReactivo: String): ReactivoResponse? =
        DatabaseFactory.dbQuery {
            TemasdereactivoTable.join(ReactivoTable, JoinType.INNER, additionalConstraint =
            {TemasdereactivoTable.idreactivo eq ReactivoTable.idreactivo})
                .join(UnidadTable, JoinType.INNER, additionalConstraint =
                {TemasdereactivoTable.idunidad eq UnidadTable.idUnidad})
                .join(TemaTable, JoinType.INNER, additionalConstraint =
                {TemasdereactivoTable.idtema eq TemaTable.idTema})
                .select { ReactivoTable.idreactivo.eq(idReactivo) }
                .map {
                    runBlocking {
                        rowToResponse(it)
                    }
                }.singleOrNull()
        }

    override suspend fun deleteById(idReactivo: String): Int =
        DatabaseFactory.dbQuery {
            ReactivoTable.deleteWhere { ReactivoTable.idreactivo.eq(idReactivo) }
        }

    override suspend fun getRespuestasByReactivoId(idReactivo: String): List<Respuesta> =
        DatabaseFactory.dbQuery {
            RespuestasdereactivoTable.join(RespuestaTable, JoinType.INNER, additionalConstraint =
            {RespuestasdereactivoTable.idrespuesta eq RespuestaTable.idrespuesta})

            .select { RespuestasdereactivoTable.idreactivo.eq(idReactivo) }
            .mapNotNull {
                    rowToAnswer(it)
            }
        }

    private fun rowToAsig(row:ResultRow?) : Reactivo? {
        if(row == null){ return null }
        return Reactivo(
            idreactivo = row[ReactivoTable.idreactivo],
            pregunta = row[ReactivoTable.pregunta],
            dificultad = row[ReactivoTable.dificultad],
            requiereProcedimiento = row[ReactivoTable.requiereProcedimiento]
        )
    }

    private suspend fun rowToResponse(row:ResultRow?, ) : ReactivoResponse? {
        if(row == null){ return null }

        return ReactivoResponse(
            idunidad = row[UnidadTable.idUnidad],
            unidad = row[UnidadTable.nombreUnidad],
            idtema = row[TemaTable.idTema],
            tema = row[TemaTable.nombreTema],
            idreactivo = row[ReactivoTable.idreactivo],
            pregunta = row[ReactivoTable.pregunta],
            dificultad = row[ReactivoTable.dificultad],
            requiereProcedimiento = row[ReactivoTable.requiereProcedimiento],
            listOfRespuestas = getRespuestasByReactivoId(row[ReactivoTable.idreactivo])
        )
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
