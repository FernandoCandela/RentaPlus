package pe.edu.ulima.pm.renta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.ulima.pm.renta.R
import pe.edu.ulima.pm.renta.model.Inmueble

class InmuebleListAdapter (
    private val inmuebleList : List<Inmueble>,
    private val fragment : Fragment,
    private val listener : (Inmueble) -> Unit) :
    RecyclerView.Adapter<InmuebleListAdapter.ViewHolder>(){

        class ViewHolder(
            view : View, val listener : (Inmueble) -> Unit,
            val productsList : List<Inmueble>) : RecyclerView.ViewHolder(view), View.OnClickListener {

            val iviInmueble : ImageView
            val tviInmuebleTitulo : TextView
            val tviInmuebleDireccion : TextView

            init {
                iviInmueble = view.findViewById(R.id.iviInmueble)
                tviInmuebleTitulo = view.findViewById(R.id.tviInmuebleTitulo)
                tviInmuebleDireccion = view.findViewById(R.id.tviDireccion)
                view.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                listener(productsList[adapterPosition])
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_inmueble, parent, false)

            val viewHolder = ViewHolder(view, listener, inmuebleList)
            return viewHolder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tviInmuebleTitulo.text = inmuebleList[position].titulo
            holder.tviInmuebleDireccion.text = inmuebleList[position].direccion
            Glide.with(fragment)
                .load(inmuebleList[position].url)
                .fitCenter()
                .placeholder(R.drawable.ic_user)
                .into(holder.iviInmueble)
        }

        override fun getItemCount(): Int {
            return inmuebleList.size
        }
    }