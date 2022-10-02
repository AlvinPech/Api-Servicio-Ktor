package com.example.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ProfesorTable : Table() {
    val idProfesor:Column<String> = text("idprofesor")
    val correo:Column<String> = text("correo")
    val nombre: Column<String> = text("nombre")
    val apellidoPaterno:Column<String> = text("apellidopaterno")
    val apellidoMaterno:Column<String> = text("apellidomaterno")
    val contrasenia:Column<String> = text("contrasenia")

    override val primaryKey: PrimaryKey = PrimaryKey(idProfesor)
}