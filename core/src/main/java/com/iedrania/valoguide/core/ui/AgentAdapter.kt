package com.iedrania.valoguide.core.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iedrania.valoguide.core.R
import com.iedrania.valoguide.core.domain.model.Agent
import com.iedrania.valoguide.core.databinding.ItemAgentBinding

class AgentAdapter : RecyclerView.Adapter<AgentAdapter.ListViewHolder>() {

    private var listData = ArrayList<Agent>()
    var onItemClick: ((Agent) -> Unit)? = null

    fun setData(newListData: List<Agent>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_agent, parent, false)
    )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAgentBinding.bind(itemView)
        fun bind(data: Agent) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.fullPortrait)
                    .into(ivItemPhoto)
                tvItemName.text = data.displayName
                tvItemRole.text = data.role
                cardView.setCardBackgroundColor(Color.parseColor("#BB${data.backgroundGradientColors}"))
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}