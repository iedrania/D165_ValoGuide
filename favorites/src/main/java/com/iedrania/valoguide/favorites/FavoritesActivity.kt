package com.iedrania.valoguide.favorites

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.iedrania.valoguide.core.ui.AgentAdapter
import com.iedrania.valoguide.detail.DetailActivity
import com.iedrania.valoguide.favorites.databinding.ActivityFavoritesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoritesModule)

        val agentAdapter = AgentAdapter()
        agentAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        val favoritesViewModel: FavoritesViewModel by viewModel()
        favoritesViewModel.favoriteAgent.observe(this) {
            agentAdapter.setData(it)
            binding.tvEmpty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvFavorites) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = agentAdapter
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favorite Agents"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}