package com.example.agricultureapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agricultureapplication.R
import com.example.agricultureapplication.models.webapi.Post
import com.example.agricultureapplication.ui.holders.LoadingHolder
import com.example.agricultureapplication.ui.holders.PostHolder

class PostListAdapter(
    var posts: ArrayList<Post>,
    private val itemClick: (Post) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1


    override fun getItemViewType(position: Int): Int {

        return if (posts[position].Id == 0) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_NORMAL
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_LOADING)
            return LoadingHolder(
                inflater.inflate(
                    R.layout.loading_item,
                    parent,
                    false
                )
            )
        return PostHolder(
            inflater.inflate(
                R.layout.post_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = posts[position]
        if (holder is PostHolder) {
            holder.txtName.text = post.Name
            holder.txtContent.text = post.Content
            holder.itemView.setOnClickListener {
                itemClick(post)
            }
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }


    fun addLoading() {
        var loadingPost = Post.createEmptyPost()
        posts.add(loadingPost)
        notifyDataSetChanged()


    }

    fun removeLoading() {
        var position = posts.size - 1
        posts.removeAt(position)
        notifyDataSetChanged()
    }


    fun addPosts(newProducts: ArrayList<Post>) {
        posts.addAll(newProducts)
        notifyDataSetChanged()
    }
}