package com.example.maybank.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maybank.databinding.PostCardBinding
import com.example.maybank.models.Post

class PostAdapter (
    private val posts: ArrayList<Post>,
    private val listener: OnPressPostListener,
): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    interface OnPressPostListener {
        fun onClick(post: Post)
    }
    class ViewHolder(val binding: PostCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        PostCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts.getOrNull(position)

        if (post == null) return

        holder.binding.tvTitle.text = post.title
        holder.binding.tvBody.text = post.body
        holder.itemView.setOnClickListener {
            listener.onClick(post)
        }
    }

    override fun getItemCount() = posts.size

    @SuppressLint()
    fun add(data: List<Post>) {
        posts.clear()
        posts.addAll(data)
        notifyDataSetChanged()
    }
}