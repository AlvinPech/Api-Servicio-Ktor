package com.example.repository.relationTables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object ReactivosdeexamenTable : Table() {
    val idexamen:Column<String> = text("idexamen")
    val idreactivo:Column<String> = text("idreactivo")
    val orden: Column<Int> = integer("orden")

    override val primaryKey: PrimaryKey = PrimaryKey(idexamen)
    //override val primaryKey2: PrimaryKey = PrimaryKey(idrespuesta)

}