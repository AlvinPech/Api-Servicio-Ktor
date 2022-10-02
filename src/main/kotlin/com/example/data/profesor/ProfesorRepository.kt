package com.example.data.profesor

import com.example.dao.ProfesorDao
import com.example.data.asignatura.Asignatura
import com.example.repository.DatabaseFactory
import com.example.repository.tables.ProfesorTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class ProfesorRepository: ProfesorDao{
    override suspend fun insert( idProfesor: String, correo: String, nombre: String, apellidoPaterno: String, apellidoMaterno: String, contrasenia: String): Profesor? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = ProfesorTable.insert { profesor ->
                profesor[ProfesorTable.idProfesor] = idProfesor
                profesor[ProfesorTable.correo] = correo
                profesor[ProfesorTable.nombre] = nombre
                profesor[ProfesorTable.apellidoPaterno] = apellidoPaterno
                profesor[ProfesorTable.apellidoMaterno] = apellidoMaterno
                profesor[ProfesorTable.contrasenia] = contrasenia

                //insert rest data
            }
        }

        return rowToAsig(statement?.resultedValues?.get(0))
    }

    override suspend fun getAllProfesores(): List<Profesor> =
        DatabaseFactory.dbQuery {
            ProfesorTable.selectAll().mapNotNull {
                rowToAsig(it)
            }
        }

    override suspend fun getProfesoresById(idProfesor: String): Profesor? =
        DatabaseFactory.dbQuery {
            ProfesorTable.select { ProfesorTable.idProfesor.eq(idProfesor) }
                .map {
                    rowToAsig(it)
                }.singleOrNull()
        }

    override suspend fun deleteById(idProfesor: String): Int =
        DatabaseFactory.dbQuery {
            ProfesorTable.deleteWhere { ProfesorTable.idProfesor.eq(idProfesor) }
        }

    override suspend fun update(idProfesor: String, correo: String, nombre: String, apellidoPaterno: String, apellidoMaterno: String, contrasenia: String): Int {
        TODO("Not yet implemented")
    }

    private fun rowToAsig(row: ResultRow?) : Profesor? {
        if(row == null){
            return null
        }
        return Profesor(
            idProfesor = row[ProfesorTable.idProfesor],
            correo = row[ProfesorTable.correo],
            nombre = row[ProfesorTable.nombre],
            apellidoPaterno = row[ProfesorTable.apellidoPaterno],
            apellidoMaterno = row[ProfesorTable.apellidoMaterno],
            contrasenia = row[ProfesorTable.contrasenia]

        )
    }
}