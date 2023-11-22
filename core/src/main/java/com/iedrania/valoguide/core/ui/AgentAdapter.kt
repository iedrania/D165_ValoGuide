package com.iedrania.valoguide.core.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iedrania.valoguide.core.databinding.ItemAgentBinding
import com.iedrania.valoguide.core.domain.model.Agent

class AgentAdapter(private val listAgent: List<Agent>) :
    RecyclerView.Adapter<AgentAdapter.ViewHolder>() {
    var onItemClick: ((Agent) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agent = listAgent[position]
        with(holder.binding) {
            Glide.with(ivItemPhoto).load(agent.fullPortrait).into(ivItemPhoto)
            tvItemName.text = agent.displayName
            tvItemRole.text = agent.role
            cardView.setCardBackgroundColor(Color.parseColor("#BB${agent.backgroundGradientColors}"))
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(agent)
        }
    }

    override fun getItemCount() = listAgent.size

    inner class ViewHolder(var binding: ItemAgentBinding) : RecyclerView.ViewHolder(binding.root)
}