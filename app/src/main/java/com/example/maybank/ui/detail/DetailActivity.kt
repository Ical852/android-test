package com.example.maybank.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.maybank.R
import com.example.maybank.databinding.ActivityDetailBinding
import com.example.maybank.models.Post

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val post = intent.getSerializableExtra("detail") as? Post

        if (post != null) {
            binding.detailTitle.text = post.title
            binding.detailBody.text = post.body
            binding.detailId.text = post.id.toString()
            binding.detailUserId.text = post.userId.toString()
        }
    }
}