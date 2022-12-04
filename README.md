# Api-Servicio-Ktor

Para instarlar esta aplicación es necesario tener instalado un IDE que pueda correr código de Kotlin. Tal como `IntelliJ`.

Para configurar la base de datos es necesario ir a la clase DatabaseFactory.kt y reemplazar nuestra ruta a la base de datos. Ejemplo:

`config.jdbcUrl = "jdbc:postgresql:{nombredelabd}?user={nombreDeUsuario}&password={contraseña}"`
