package com.iedrania.valoguide.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.iedrania.valoguide.R
import com.iedrania.valoguide.core.data.Resource.Error
import com.iedrania.valoguide.core.data.Resource.Loading
import com.iedrania.valoguide.core.data.Resource.Success
import com.iedrania.valoguide.core.ui.AgentAdapter
import com.iedrania.valoguide.databinding.ActivityMainBinding
import com.iedrania.valoguide.detail.DetailActivity
import com.iedrania.valoguide.settings.SettingsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val mainViewModel: MainViewModel by viewModel()
        mainViewModel.agent.observe(this) {
            if (it != null) {
                when (it) {
                    is Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvError.visibility = View.GONE
                    }

                    is Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                        val agentAdapter = AgentAdapter(it.data!!)
                        agentAdapter.onItemClick = { selectedData ->
                            val intent = Intent(this, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                            startActivity(intent)
                        }
                        with(binding.rvAgents) {
                            layoutManager = GridLayoutManager(context, 2)
                            setHasFixedSize(true)
                            adapter = agentAdapter
                        }
                    }

                    is Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
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
                val uri = Uri.parse("valoguide://favorites")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }

            R.id.action_settings -> {
//                val uri = Uri.parse("valoguide://settings")
//                startActivity(Intent(Intent.ACTION_VIEW, uri))
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}