package com.example.data.asignatura

import com.example.dao.AsignaturaDao
import com.example.repository.DatabaseFactory
import com.example.repository.tables.AsignaturaTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement

class AsignaturaRepository : AsignaturaDao {
    override suspend fun insert(idAsignatura: String, nombreAsignatura:String, descAsignatura: String, idProfesor: String): Asignatura? {
        var statement:InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = AsignaturaTable.insert { asignatura ->
                asignatura[AsignaturaTable.idAsignatura] = idAsignatura
                asignatura[AsignaturaTable.nombreAsignatura] = nombreAsignatura
                asignatura[AsignaturaTable.descAsignatura] = descAsignatura
                asignatura[AsignaturaTable.idProfesor] = idProfesor

                //insert rest data
            }
        }

        return rowToAsig(statement?.resultedValues?.get(0))
    }

    override suspend fun getAllAsignaturas(): List<Asignatura> =
        DatabaseFactory.dbQuery {
            AsignaturaTable.selectAll().mapNotNull {
                rowToAsig(it)
            }
        }

    override suspend fun getAsignaturasById(idAsignatura: String): Asignatura? =
        DatabaseFactory.dbQuery {
            AsignaturaTable.select { AsignaturaTable.idAsignatura.eq(idAsignatura) }
                .map {
                    rowToAsig(it)
                }.singleOrNull()
        }

    override suspend fun getAsignaturasByProfesorId(idProfesor: String): List<Asignatura> =
        DatabaseFactory.dbQuery {
            AsignaturaTable.select { AsignaturaTable.idProfesor.eq(idProfesor) }
                .mapNotNull {
                    rowToAsig(it)
                }
        }

    override suspend fun deleteById(idAsignatura: String): Int =
        DatabaseFactory.dbQuery {
            AsignaturaTable.deleteWhere { AsignaturaTable.idAsignatura.eq(idAsignatura) }
        }

    override suspend fun update(idAsignatura: String, nombreAsignatura:String, descAsignatura: String, idProfesor: String): Int =
        DatabaseFactory.dbQuery {
            AsignaturaTable.update({ AsignaturaTable.idAsignatura.eq(idAsignatura) }) { asignatura ->
                asignatura[AsignaturaTable.nombreAsignatura] = nombreAsignatura
                asignatura[AsignaturaTable.descAsignatura] = descAsignatura
                asignatura[AsignaturaTable.idProfesor] = idProfesor
                //Other data from columns

            }
        }

    private fun rowToAsig(row:ResultRow?) : Asignatura? {
        if(row == null){
            return null
        }
        return Asignatura(
            idAsignatura = row[AsignaturaTable.idAsignatura],
            nombreAsignatura = row[AsignaturaTable.nombreAsignatura],
            descAsignatura = row[AsignaturaTable.descAsignatura],
            idProfesor = row[AsignaturaTable.idProfesor]

        )
    }
}