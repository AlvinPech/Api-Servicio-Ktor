package com.example.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object ExamenTable : Table() {
    val idexamen:Column<String> = text("idexamen")
    val nombre:Column<String> = text("nombre")
    val descripcion: Column<String> = text("descripcion")
    val idasignatura: Column<String> = text("idasignatura")
    val tiempo: Column<Int> = integer("tiempo")

    override val primaryKey: PrimaryKey = PrimaryKey(idexamen)
}