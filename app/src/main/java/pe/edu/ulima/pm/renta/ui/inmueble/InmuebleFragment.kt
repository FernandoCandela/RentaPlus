package pe.edu.ulima.pm.renta.ui.inmueble

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.renta.R
import pe.edu.ulima.pm.renta.model.Inmueble

class InmuebleFragment : Fragment() {
    interface OnInmuebleSelectedListener {
        fun onSelect(inmueble: Inmueble)
    }

    private var listener: OnInmuebleSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnInmuebleSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inmueble, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}