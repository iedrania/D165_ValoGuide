package com.iedrania.valoguide.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.iedrania.valoguide.R
import com.iedrania.valoguide.core.domain.model.Agent
import com.iedrania.valoguide.core.ui.ViewModelFactory
import com.iedrania.valoguide.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this.application)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        @Suppress("DEPRECATION")
        val detailAgent = intent.getParcelableExtra<Agent>(EXTRA_DATA)
        if (detailAgent != null) {
            showAgentDetail(detailAgent)
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun showAgentDetail(agent: Agent) {
        binding.tvDetailName.text = agent.displayName
        Glide.with(this)
            .load(agent.displayIcon)
            .into(binding.imgDetailPhoto)
        binding.imgDetailPhoto.contentDescription = "${agent.displayName}'s full portrait"

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
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
            binding.fab.contentDescription = R.string.remove.toString()
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
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