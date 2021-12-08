package pe.edu.ulima.pm.renta.model

import java.io.Serializable

data class Historial(
    val id: String,
    val idArrendatario: String,
    val idFactura: String,
    val idInmueble: String
) : Serializable