package pe.edu.ulima.pm.renta.model

import java.io.Serializable

data class inmueble(
    val id: String,
    val direccion: String,
    val idUsuario: String,
    val titulo: String
) : Serializable