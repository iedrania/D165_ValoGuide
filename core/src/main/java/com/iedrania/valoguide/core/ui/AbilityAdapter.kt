package com.iedrania.valoguide.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iedrania.valoguide.core.R
import com.iedrania.valoguide.core.databinding.ItemAbilityBinding
import com.iedrania.valoguide.core.domain.model.Ability

class AbilityAdapter : RecyclerView.Adapter<AbilityAdapter.ListViewHolder>() {

    private var listData = ArrayList<Ability>()
    var onItemClick: ((Ability) -> Unit)? = null

    fun setData(newListData: List<Ability>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_ability, parent, false)
    )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAbilityBinding.bind(itemView)
        fun bind(data: Ability) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.displayIcon)
                    .into(ivItemIcon)
                tvItemName.text = data.displayName
                tvItemDescription.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}