package com.iedrania.valoguide.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.iedrania.valoguide.R
import com.iedrania.valoguide.core.data.Resource
import com.iedrania.valoguide.core.ui.AgentAdapter
import com.iedrania.valoguide.databinding.ActivityMainBinding
import com.iedrania.valoguide.detail.DetailActivity
import com.iedrania.valoguide.favorite.FavoriteActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agentAdapter = AgentAdapter()
        agentAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        val mainViewModel: MainViewModel by viewModel()
        mainViewModel.agent.observe(this) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvError.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                        agentAdapter.setData(it.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
        }

        with (binding.rvAgents) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = agentAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorites -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}