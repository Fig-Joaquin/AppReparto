// ui/material/MaterialAdapter.kt
package com.example.appreparto.ui.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appreparto.databinding.ItemMaterialBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.appreparto.Material

class MaterialAdapter(
    private val onDelete: (Material) -> Unit,
    private val onEdit: (Material) -> Unit
) : ListAdapter<Material, MaterialAdapter.VH>(Diff) {

    companion object {
        private val Diff = object : DiffUtil.ItemCallback<Material>() {
            override fun areItemsTheSame(o: Material, n: Material) = o.id == n.id
            override fun areContentsTheSame(o: Material, n: Material) = o == n
        }
    }

    inner class VH(val bind: ItemMaterialBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(p: ViewGroup, v: Int): VH =
        VH(ItemMaterialBinding.inflate(LayoutInflater.from(p.context), p, false))

    override fun onBindViewHolder(h: VH, pos: Int) = with(h.bind) {
        val m = getItem(pos)
        tvName.text = m.nombre
        tvDesc.text = m.descripcion
        root.setOnClickListener   { onEdit(m) }
        btnDelete.setOnClickListener { onDelete(m) }
    }
}

