package com.example.appreparto.ui.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appreparto.Productos
import com.example.appreparto.databinding.ItemProductoBinding

class ProductosAdapter (
    private val onDelete: (Productos) -> Unit,
    private val onEdit: (Productos) -> Unit
) : ListAdapter<Productos, ProductosAdapter.ProductosViewHolder>(Diff) {


    companion object {
        private val Diff = object : DiffUtil.ItemCallback<Productos>() {
            override fun areItemsTheSame(o: Productos, n: Productos) = o.id == n.id
            override fun areContentsTheSame(o: Productos, n: Productos) = o == n
        }
    }

    inner class ProductosViewHolder(val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder =
        ProductosViewHolder(ItemProductoBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) = with(holder.binding) {
        val p = getItem(position)
        tvName.text = p.nombre
        tvDesc.text = p.descripcion
        root.setOnClickListener { onEdit(p) }
        btnDelete.setOnClickListener { onDelete(p) }
    }
}