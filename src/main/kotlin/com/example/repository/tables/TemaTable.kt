package com.example.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object TemaTable : Table() {
    val idTema:Column<String> = text("idtema")
    val nombreTema:Column<String> = text("nombre")
    val descTema: Column<String> = text("descripcion")
    val idUnidad: Column<String> = text("idunidad")

    override val primaryKey: PrimaryKey = PrimaryKey(idTema)
}