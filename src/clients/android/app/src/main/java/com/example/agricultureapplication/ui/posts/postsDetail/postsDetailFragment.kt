package com.example.agricultureapplication.ui.posts.postsDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.agricultureapplication.MainActivity
import com.example.agricultureapplication.R
import kotlinx.android.synthetic.main.posts_detail_fragment.*
import kotlinx.android.synthetic.main.posts_detail_fragment.view.*

class postsDetailFragment : Fragment() {

    val args: postsDetailFragmentArgs by navArgs()

    private lateinit var viewModel: PostsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[PostsDetailViewModel::class.java]
        var fragmentView = inflater.inflate(R.layout.posts_detail_fragment, container, false)

        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)

        fragmentView.btn_detail_delete.visibility = View.GONE
        fragmentView.btn_detail_update.visibility = View.GONE

        viewModel.isAdmin()

        viewModel.isOwner.observe(viewLifecycleOwner) {
            if (it) {
                fragmentView.btn_detail_delete.visibility = View.VISIBLE
                fragmentView.btn_detail_update.visibility = View.VISIBLE
            }
            else{
                fragmentView.btn_detail_delete.visibility = View.GONE
                fragmentView.btn_detail_update.visibility = View.GONE
            }
        }

        fragmentView.txt_detail_name.text = args.post.Name
        fragmentView.txt_detail_content.text = args.post.Content
        fragmentView.txt_detail_fertilizer.text = args.post.Fertilizer
        fragmentView.txt_detail_harvest.text = args.post.Harvest
        fragmentView.txt_detail_irrigation.text = args.post.Irrigation
        fragmentView.txt_detail_planting.text = args.post.Planting

        fragmentView.btn_detail_delete.setOnClickListener() {
            viewModel.deletePost(args.post.Id.toInt()).observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(context, "Post deleted", Toast.LENGTH_SHORT).show()
                    fragmentView.findNavController().navigate(R.id.postsListFragmentNav)
                }
            }
        }

        fragmentView.btn_detail_update.setOnClickListener() {
            var action =
                postsDetailFragmentDirections.actionPostsDetailFragmentToPostsUpdateFragment(
                    args.post
                )
            fragmentView.findNavController().navigate(action)
        }

        return fragmentView
    }
}