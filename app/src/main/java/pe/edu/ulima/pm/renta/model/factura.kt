package pe.edu.ulima.pm.renta.model

import java.io.Serializable

data class factura(
    val id: String,
    val fecha_pago: String,
    val monto: Float
) : Serializable