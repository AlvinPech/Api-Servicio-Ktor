package com.example.repository.relationTables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object ReactivosdeexamenTable : Table() {
    val idreactivo:Column<String> = text("idreactivo")
    val idrespuesta:Column<String> = text("idrespuesta")
    val orden: Column<Int> = integer("orden")

    override val primaryKey: PrimaryKey = PrimaryKey(idreactivo)
    //override val primaryKey2: PrimaryKey = PrimaryKey(idrespuesta)

}