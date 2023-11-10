package com.iedrania.valoguide.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.iedrania.valoguide.core.ui.AgentAdapter
import com.iedrania.valoguide.databinding.ActivityFavoriteBinding
import com.iedrania.valoguide.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agentAdapter = AgentAdapter()
        agentAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        val favoriteViewModel: FavoriteViewModel by viewModel()
        favoriteViewModel.favoriteAgent.observe(this) {
            agentAdapter.setData(it)
            binding.tvEmpty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = agentAdapter
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}