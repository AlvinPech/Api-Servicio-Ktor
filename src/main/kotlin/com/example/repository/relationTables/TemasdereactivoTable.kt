package com.example.repository.relationTables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object TemasdereactivoTable : Table() {
    val idreactivo:Column<String> = text("idreactivo")
    val idtema:Column<String> = text("idtema")
    val idunidad:Column<String> = text("idunidad")
   

    override val primaryKey: PrimaryKey = PrimaryKey(idreactivo)
}