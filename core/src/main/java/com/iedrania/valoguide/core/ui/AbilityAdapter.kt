package com.iedrania.valoguide.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iedrania.valoguide.core.databinding.ItemAbilityBinding
import com.iedrania.valoguide.core.domain.model.Ability

class AbilityAdapter(private val listAbility: List<Ability>) :
    RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ability = listAbility[position]
        with(holder.binding) {
            Glide.with(ivItemIcon).load(ability.displayIcon).into(ivItemIcon)
            tvItemName.text = ability.displayName
            tvItemDescription.text = ability.description
        }
    }

    override fun getItemCount() = listAbility.size

    class ViewHolder(var binding: ItemAbilityBinding) : RecyclerView.ViewHolder(binding.root)
}