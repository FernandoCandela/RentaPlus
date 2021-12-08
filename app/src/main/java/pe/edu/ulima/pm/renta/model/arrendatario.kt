package pe.edu.ulima.pm.renta.model

import java.io.Serializable

data class arrendatario(
    val id: String,
    val apellidos: String,
    val email: String,
    val fecha_pago: String,
    val idInmueble: String,
    val monto: Float,
    val nombre: String,
    val telefono: String
) : Serializable