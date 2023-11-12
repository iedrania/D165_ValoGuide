package com.iedrania.valoguide.detail

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.iedrania.valoguide.R
import com.iedrania.valoguide.core.domain.model.Agent
import com.iedrania.valoguide.core.ui.AbilityAdapter
import com.iedrania.valoguide.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModel<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val detailAgent = intent.getParcelableExtra<Agent>(EXTRA_DATA)
        if (detailAgent != null) {
            showAgentDetail(detailAgent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val abilityAdapter = AbilityAdapter()
        abilityAdapter.setData(detailAgent?.abilities)
        with(binding.rvAbilities) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = abilityAdapter
        }
    }

    private fun showAgentDetail(agent: Agent) {
        binding.relativeLayout.setBackgroundColor(Color.parseColor("#${agent.backgroundGradientColors}"))
        binding.tvDetailName.text = agent.displayName
        binding.tvDetailDescription.text = agent.role
        Glide.with(this)
            .load(agent.fullPortrait)
            .into(binding.ivDetailPhoto)

        var favorite = agent.isFavorite
        setFavorite(favorite)
        binding.fab.setOnClickListener {
            favorite = !favorite
            detailViewModel.setFavoriteAgent(agent, favorite)
            if (favorite) showToast(getString(R.string.added)) else showToast(getString(R.string.removed))
            setFavorite(favorite)
        }
    }

    private fun setFavorite(favorite: Boolean) {
        if (favorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.baseline_favorite_24
                )
            )
            binding.fab.contentDescription = R.string.remove.toString()
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.baseline_favorite_border_24
                )
            )
            binding.fab.contentDescription = R.string.add.toString()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}