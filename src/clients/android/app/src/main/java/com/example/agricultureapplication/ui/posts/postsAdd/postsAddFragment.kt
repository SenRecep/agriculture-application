package com.example.agricultureapplication.ui.posts.postsAdd

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.agricultureapplication.MainActivity
import com.example.agricultureapplication.R
import com.example.agricultureapplication.models.webapi.dto.PostCreateDto
import kotlinx.android.synthetic.main.posts_add_fragment.view.*

class postsAddFragment : Fragment() {
    private lateinit var viewModel: PostsAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostsAddViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.posts_add_fragment, container, false)
        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)


        fragmentView.btn_fragment_post_add.setOnClickListener() {
            var postCreateDto = PostCreateDto(
                Name = fragmentView.txt_post_add_title.editText?.text.toString(),
                Content = fragmentView.txt_post_add_content.editText?.text.toString(),
                Fertilizer = fragmentView.txt_post_add_content.editText?.text.toString(),
                Irrigation = fragmentView.txt_post_add_content.editText?.text.toString(),
                Planting = fragmentView.txt_post_add_content.editText?.text.toString(),
                Harvest = fragmentView.txt_post_add_content.editText?.text.toString(),
            )

            viewModel.createPost(postCreateDto).observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(activity, "Post created", Toast.LENGTH_LONG).show()
                    fragmentView.findNavController().navigate(R.id.postsListFragmentNav)
                }
            }
        }

        return fragmentView
    }
}