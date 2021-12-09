package pe.edu.ulima.pm.renta.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InmuebleManager() {
    private val dbFirebase = Firebase.firestore

    fun getInmueblesByUser(
        idUser: String?,
        callbackOK: (MutableList<Inmueble>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("inmueble")
            .whereEqualTo("idUsuario", idUser)
            .get()
            .addOnSuccessListener { res ->
                val favoritos = arrayListOf<Inmueble>()
                for (document in res) {
                    val inm = Inmueble(
                        document.id,
                        document.data["direccion"]!! as String,
                        document.data["idUsuario"]!! as String,
                        document.data["titulo"]!! as String,
                        document.data["url"]!! as String
                    )
                    favoritos.add(inm)
                }
                callbackOK(favoritos)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }

    fun getHistorialByInmueble(
        idInmueble: String?,
        callbackOK: (MutableList<Historial>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("historial")
            .whereEqualTo("idInmueble", idInmueble)
            .get()
            .addOnSuccessListener { res ->
                val historial = arrayListOf<Historial>()
                for (document in res) {
                    val his = Historial(
                        document.id,
                        document.data["arrendatario"]!! as String,
                        document.data["monto"]!! as String,
                        document.data["fecha_pago"]!! as String,
                        document.data["idInmueble"]!! as String,
                        document.data["url_factura"]!! as String
                    )
                    historial.add(his)
                }
                callbackOK(historial)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }
    fun getArrendatarioActivo(
        idInmueble: String,
        callbackOK: (Arrendatario) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("arrendatario")
            .whereEqualTo("idInmueble", idInmueble)
            .whereEqualTo("activo", true)
            .get()
            .addOnSuccessListener { res ->
                val arrendatarios = arrayListOf<Arrendatario>()
                for (document in res) {
                    val pk = Arrendatario(
                        document.id,
                        document.data["activo"]!! as Boolean,
                        document.data["apellidos"]!! as String,
                        document.data["email"]!! as String,
                        document.data["fecha_pago"]!! as String,
                        document.data["idInmueble"]!! as String,
                        document.data["monto"]!! as Float,
                        document.data["nombre"]!! as String,
                        document.data["telefono"]!! as String,
                    )
                    arrendatarios.add(pk)
                }
                callbackOK(arrendatarios.first())
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }


}