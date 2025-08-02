package com.example.appreparto.ui.evento

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appreparto.databinding.ItemEventoBinding
import com.example.appreparto.model.Evento

class EventoAdapter(
    private val onEdit: (Evento) -> Unit,
    private val onDelete: (Evento) -> Unit
) : RecyclerView.Adapter<EventoAdapter.VH>() {

    private val items = mutableListOf<Evento>()

    fun submit(newList: List<Evento>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    inner class VH(val binding: ItemEventoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemEventoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val evento = items[position]
        with(holder.binding) {
            tvTipoEvento.text = evento.tipo
            tvProducto.text = evento.producto
            tvCantidad.text = "${if (evento.cantidad >= 0) "+" else ""}${evento.cantidad} unidades"
            tvFecha.text = evento.fechaHora
            tvUsuario.text = evento.usuario
            tvStockFinal.text = "Stock después del evento: ${evento.stockFinal}"
            tvObservaciones.text = evento.observaciones

            btnEdit.setOnClickListener { onEdit(evento) }
            btnDelete.setOnClickListener { onDelete(evento) }
        }
    }
}
