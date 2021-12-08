package pe.edu.ulima.pm.renta.ui.inmueble

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import pe.edu.ulima.pm.renta.R
import pe.edu.ulima.pm.renta.adapter.InmuebleListAdapter
import pe.edu.ulima.pm.renta.model.Inmueble
import pe.edu.ulima.pm.renta.model.InmuebleManager

class InmuebleFragment : Fragment() {
    interface OnInmuebleSelectedListener {
        fun onSelect(inmueble: Inmueble)
    }

    private var listener: OnInmuebleSelectedListener? = null

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        listener = context as OnInmuebleSelectedListener
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inmueble, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idUser = FirebaseAuth.getInstance().currentUser!!.uid
        print(idUser)
        InmuebleManager().getInmueblesByUserFirebase(idUser,{ vgList : MutableList<Inmueble> ->
            val rviInmuebles = view.findViewById<RecyclerView>(R.id.rviInmuebles)
            rviInmuebles.adapter = InmuebleListAdapter(
                vgList,
                this
            ){
                    inm: Inmueble  ->
                listener?.onSelect(inm)
            }

        },{error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })
    }

}