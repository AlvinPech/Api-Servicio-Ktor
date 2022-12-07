package com.example.data.profesor

data class Profesor (
    val idProfesor: String,
    val correo:String,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val contrasenia: String,
    val esSuperUser: Boolean
)