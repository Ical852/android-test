package com.example.maybank.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maybank.adapter.PostAdapter
import com.example.maybank.databinding.ActivityMainBinding
import com.example.maybank.models.Post
import com.example.maybank.ui.detail.DetailActivity
import org.koin.dsl.module
import org.koin.androidx.viewmodel.ext.android.viewModel

val mainModule = module {
    factory { MainActivity() }
}

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        postListInit()
        loadingInit()
        searchInit()
    }

    private val postListAdapter by lazy {
        PostAdapter(arrayListOf(), object : PostAdapter.OnPressPostListener{
            override fun onClick(post: Post) {
                startActivity(
                    Intent(this@MainActivity, DetailActivity::class.java)
                        .putExtra("detail", post)
                )
            }
        })
    }

    private fun postListInit() {
        binding.rvPostList.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvPostList.adapter = postListAdapter
        viewModel.data.observe(this, { it ->
            postListAdapter.add(it)
        })
    }

    private fun loadingInit() {
        viewModel.loading.observe(this, { it ->
            if (it) {
                binding.loading.visibility = View.VISIBLE
                binding.rvPostList.visibility = View.GONE
            } else {
                binding.loading.visibility = View.GONE
                binding.rvPostList.visibility = View.VISIBLE
            }
        })
    }

    private fun searchInit() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.search.postValue(p0.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        viewModel.search.observe(this, { search ->
            val filtered = viewModel.data.value?.filter { it.title.contains(search, ignoreCase = true) }
            filtered?.let { postListAdapter.add(it) }
        })
    }
}