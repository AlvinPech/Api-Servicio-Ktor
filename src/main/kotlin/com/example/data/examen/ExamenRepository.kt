package com.example.data.examen

import com.example.dao.AsignaturaDao
import com.example.dao.ExamenDao
import com.example.data.profesor.Profesor
import com.example.data.reactivo.Reactivo
import com.example.data.respuesta.Respuesta
import com.example.repository.DatabaseFactory
import com.example.repository.relationTables.ReactivosdeexamenTable
import com.example.repository.relationTables.RespuestasdereactivoTable
import com.example.repository.relationTables.TemasdereactivoTable
import com.example.repository.tables.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class ExamenRepository : ExamenDao {
    override suspend fun insert(idExamen: String, nombre: String, descripcion: String, idAsignatura: String, tiempo: Int): Examen? {
        var statement:InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = ExamenTable.insert { exam ->
                exam[ExamenTable.idexamen] = idExamen
                exam[ExamenTable.nombre] = nombre
                exam[ExamenTable.descripcion] = descripcion
                exam[ExamenTable.idasignatura] = idAsignatura
                exam[ExamenTable.tiempo] = tiempo
            }
        }

        return rowToAsig(statement?.resultedValues?.get(0))
    }

    override suspend fun addReactivoToExam(idExamen: String, idReactivo: String, orden: Int): ReactivoExamen? {
        var statement:InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = ReactivosdeexamenTable.insert { Reac_exam ->
                Reac_exam[ReactivosdeexamenTable.idexamen] = idExamen
                Reac_exam[ReactivosdeexamenTable.idreactivo] = idReactivo
                Reac_exam[ReactivosdeexamenTable.orden] = orden
            }
        }

        return rowToReac(statement?.resultedValues?.get(0))
    }

    override suspend fun getAllExamenes(): List<ExamenResponse> =
        DatabaseFactory.dbQuery {
            ExamenTable.selectAll().mapNotNull {
                runBlocking {
                    rowToResponse(it)
                }
            }
        }

    override suspend fun getExamenesById(idExamen: String): ExamenResponse? =
            DatabaseFactory.dbQuery {
                ExamenTable.select { ExamenTable.idexamen.eq(idExamen) }
                    .map {
                        runBlocking {
                            rowToResponse(it)
                        }
                    }.singleOrNull()
            }

    override suspend fun getExamenesByAsignaturaId(idAsignatura: String): List<ExamenResponse> =
        DatabaseFactory.dbQuery {
            ExamenTable.select { ExamenTable.idasignatura.eq(idAsignatura) }
                .mapNotNull{
                    runBlocking {
                        rowToResponse(it)
                    }
                }
        }

    override suspend fun getReactivosByExamenId(idExamen: String): List<Reactivo> =
        DatabaseFactory.dbQuery {
            ReactivosdeexamenTable.join(ReactivoTable, JoinType.INNER, additionalConstraint =
            { ReactivosdeexamenTable.idreactivo eq ReactivoTable.idreactivo})

                .select { ReactivosdeexamenTable.idexamen.eq(idExamen) }
                .mapNotNull {
                    rowToReactivo(it)
                }
        }

    override suspend fun deleteById(idExamen: String): Int =
        DatabaseFactory.dbQuery {
            ExamenTable.deleteWhere { ExamenTable.idexamen.eq(idExamen) }
        }

    override suspend fun update(idExamen: String, nombre: String, descripcion: String, idAsignatura: String, tiempo: Int): Int =
        DatabaseFactory.dbQuery {
            ExamenTable.update({ ExamenTable.idexamen.eq(idExamen) }) { exam ->
                exam[ExamenTable.nombre] = nombre
                exam[ExamenTable.descripcion] = descripcion
                exam[ExamenTable.idasignatura] = idAsignatura
                exam[ExamenTable.tiempo] = tiempo
            }
    }


    private fun rowToAsig(row:ResultRow?) : Examen? {
        if(row == null){ return null }
        return Examen(
            idExamen = row[ExamenTable.idexamen],
            nombre = row[ExamenTable.nombre],
            descripcion = row[ExamenTable.descripcion],
            idAsignatura = row[ExamenTable.idasignatura],
            tiempo = row[ExamenTable.tiempo]
        )
    }

    private fun rowToReac(row:ResultRow?) : ReactivoExamen? {
        if(row == null){ return null }
        return ReactivoExamen(
            idExamen = row[ReactivosdeexamenTable.idexamen],
            idReactivo = row[ReactivosdeexamenTable.idreactivo],
            orden = row[ReactivosdeexamenTable.orden]
        )
    }

    private suspend fun rowToResponse(row:ResultRow?) : ExamenResponse? {
        if(row == null){ return null }
        return ExamenResponse(
            idExamen = row[ExamenTable.idexamen],
            nombre = row[ExamenTable.nombre],
            descripcion = row[ExamenTable.descripcion],
            idAsignatura = row[ExamenTable.idasignatura],
            tiempo = row[ExamenTable.tiempo],
            listOfReactivos = getReactivosByExamenId(row[ExamenTable.idexamen])
        )
    }


    private fun rowToReactivo(row:ResultRow?) : Reactivo? {
        if(row == null){ return null }
        return Reactivo(
            idreactivo = row[ReactivoTable.idreactivo],
            pregunta = row[ReactivoTable.pregunta],
            dificultad = row[ReactivoTable.dificultad],
            requiereProcedimiento = row[ReactivoTable.requiereProcedimiento],
            correcto = row[ReactivoTable.correcto]
        )
    }
}