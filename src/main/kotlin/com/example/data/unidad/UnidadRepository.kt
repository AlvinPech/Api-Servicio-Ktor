package com.example.data.unidad

import com.example.dao.UnitDao
import com.example.repository.DatabaseFactory
import com.example.repository.tables.TemaTable
import com.example.repository.tables.UnidadTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class UnidadRepository : UnitDao {
    override suspend fun insert(idUnidad: String, nombreUnidad:String, descUnidad: String, idAsignatura: String): Unidad? {
        var statement:InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = UnidadTable.insert { unidad ->
                unidad[UnidadTable.idUnidad] = idUnidad
                unidad[UnidadTable.nombreUnidad] = nombreUnidad
                unidad[UnidadTable.descUnidad] = descUnidad
                unidad[UnidadTable.idAsignatura] = idAsignatura

                //insert rest data
            }
        }

        return rowToAsig(statement?.resultedValues?.get(0))
    }

    override suspend fun getAllUnits(): List<Unidad> =
        DatabaseFactory.dbQuery {
            UnidadTable.selectAll().mapNotNull {
                rowToAsig(it)
            }
        }

    override suspend fun getUnitsById(idUnidades: String): Unidad? =
        DatabaseFactory.dbQuery {
            UnidadTable.select { UnidadTable.idUnidad.eq(idUnidades) }
                .map {
                    //println(UnidadTable.idUnidad)
                    println("${it[UnidadTable.idUnidad]}")
                    rowToAsig(it)
                }.singleOrNull()
        }

    override suspend fun getUnitsByAsignaturaId(idAsignatura: String): List<Unidad> =
        DatabaseFactory.dbQuery {
            UnidadTable.select { UnidadTable.idAsignatura.eq(idAsignatura) }
                .mapNotNull {
                    rowToAsig(it)
                }
        }


    override suspend fun deleteById(idUnidad: String): Int =
        DatabaseFactory.dbQuery {
            UnidadTable.deleteWhere { UnidadTable.idUnidad.eq(idUnidad) }
        }

    override suspend fun update(idUnidad: String, nombreUnidad: String, descUnidad: String, idAsignatura: String): Int =
        DatabaseFactory.dbQuery {
            UnidadTable.update({ UnidadTable.idUnidad.eq(idUnidad) }) { unidad ->
                unidad[UnidadTable.nombreUnidad] = nombreUnidad
                unidad[UnidadTable.descUnidad] = descUnidad
                unidad[UnidadTable.idAsignatura] = idAsignatura
            }
        }


    private fun rowToAsig(row:ResultRow?) : Unidad? {
        if(row == null){
            return null
        }
        return Unidad(
            idUnidades = row[UnidadTable.idUnidad],
            nombreUnidad = row[UnidadTable.nombreUnidad],
            descUnidad = row[UnidadTable.descUnidad],
            idAsignatura = row[UnidadTable.idAsignatura]

        )
    }
}