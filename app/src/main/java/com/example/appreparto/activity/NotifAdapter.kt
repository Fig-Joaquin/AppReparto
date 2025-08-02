package com.example.appreparto.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appreparto.databinding.ItemNotificacionBinding
import com.example.appreparto.model.Notificacion
import java.text.SimpleDateFormat
import java.util.*

class NotifAdapter : ListAdapter<Notificacion, NotifAdapter.VH>(DIFF) {
    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Notificacion>() {
            override fun areItemsTheSame(a: Notificacion, b: Notificacion) = a.id == b.id
            override fun areContentsTheSame(a: Notificacion, b: Notificacion) = a == b
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemNotificacionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VH(private val b: ItemNotificacionBinding) : RecyclerView.ViewHolder(b.root) {
        private val fmt = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

        fun bind(n: Notificacion) {
            b.tvMensaje.text = n.mensaje
            b.tvFecha.text = fmt.format(n.fechaHora)
        }
    }
}
