// ui/material/MaterialAdapter.kt
package com.example.appreparto.ui.theme

import com.example.appreparto.model.Material
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appreparto.databinding.ItemMaterialBinding


class MaterialAdapter(
    private val onDelete: (Material) -> Unit,
    private val onEdit: (Material) -> Unit
) : RecyclerView.Adapter<MaterialAdapter.VH>() {

    private val items = mutableListOf<Material>()

    /** Actualiza la lista interna y refresca el RecyclerView */
    fun submit(newList: List<Material>) {
        items.clear()           // ← correción: sobre la instancia
        items.addAll(newList)
        notifyDataSetChanged()
    }

    inner class VH(val binding: ItemMaterialBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMaterialBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val m = items[position]
        holder.binding.tvName.text = m.nombre
        holder.binding.tvDesc.text = m.descripcion
        holder.binding.root.setOnClickListener { onEdit(m) }
        holder.binding.btnDelete.setOnClickListener { onDelete(m) }
    }
}
