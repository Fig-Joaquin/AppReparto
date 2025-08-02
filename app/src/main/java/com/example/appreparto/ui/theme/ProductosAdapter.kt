package com.example.appreparto.ui.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appreparto.Productos
import com.example.appreparto.databinding.ItemProductoBinding

class ProductosAdapter (
    private val onDelete: (Productos) -> Unit,
    private val onEdit: (Productos) -> Unit
) : RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder>() {

    private val items = mutableListOf<Productos>()

    fun submit(newList: List<Productos>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ProductosViewHolder(val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductoBinding.inflate(inflater, parent, false)
        return ProductosViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        val p = items[position]
        holder.binding.tvName.text = p.nombre
        holder.binding.tvDesc.text = p.descripcion
        holder.binding.root.setOnClickListener { onEdit(p) }
        holder.binding.btnDelete.setOnClickListener { onDelete(p) }
    }
}