package pe.edu.ulima.pm.renta.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InmuebleManager() {
    private val dbFirebase = Firebase.firestore

    fun getInmueblesByUserFirebase(
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
}