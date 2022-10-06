package com.example.data.tema

import com.example.dao.ResponseDao
import com.example.repository.DatabaseFactory
import com.example.repository.tables.RespuestaTable
import com.example.repository.tables.TemaTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class responseRepository : ResponseDao {
    override suspend fun insert(idPK: String, nombreReponse: String, descResponse: String, idFK: String): Tema? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = TemaTable.insert { tema ->
                tema[TemaTable.idTema] = idPK
                tema[TemaTable.nombreTema] = nombreReponse
                tema[TemaTable.descTema] = descResponse
                tema[TemaTable.idUnidad] = idFK

                //insert rest data
            }
        }

        return rowToAsig(statement?.resultedValues?.get(0))
    }

    override suspend fun getAllReponse(): List<Tema> =
        DatabaseFactory.dbQuery {
            TemaTable.selectAll().mapNotNull {
                rowToAsig(it)
            }
        }

    override suspend fun getResponseById(idPK: String): Tema? =
        DatabaseFactory.dbQuery {
            TemaTable.select { TemaTable.idTema.eq(idPK) }
                .map {
                    //println(UnidadTable.idUnidad)
                    //println("${it[UnidadTable.idUnidad]}")
                    rowToAsig(it)
                }.singleOrNull()
        }

    override suspend fun getResponseByForeingId(idFK: String): List<Tema> =
        DatabaseFactory.dbQuery {
            TemaTable.select { TemaTable.idUnidad.eq(idFK) }
                .mapNotNull {
                    rowToAsig(it)
                }
        }

    override suspend fun deleteById(idPK: String): Int =
        DatabaseFactory.dbQuery {
            TemaTable.deleteWhere { TemaTable.idUnidad.eq(idPK) }
        }

    override suspend fun update(idPK: String, nombreReponse: String, descResponse: String, idFK: String): Int =
        DatabaseFactory.dbQuery {
            TemaTable.update({ TemaTable.idTema.eq(idPK) }) { tema ->
                tema[TemaTable.nombreTema] = nombreReponse
                tema[TemaTable.descTema] = descResponse
                tema[TemaTable.idUnidad] = idFK
            }
        }
}



    private fun rowToAsig(row: ResultRow?) : Tema? {
        if(row == null){
            return null
        }
        return Tema(
            idTema = row[TemaTable.idTema],
            nombreTema = row[TemaTable.nombreTema],
            descTema = row[TemaTable.descTema],
            idUnidad = row[TemaTable.idUnidad]

        )

}