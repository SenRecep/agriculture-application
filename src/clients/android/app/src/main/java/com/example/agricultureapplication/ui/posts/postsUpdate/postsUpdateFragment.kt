package com.example.agricultureapplication.ui.posts.postsUpdate

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
import com.example.agricultureapplication.models.webapi.dto.PostUpdateDto
import kotlinx.android.synthetic.main.posts_update_fragment.view.*

class postsUpdateFragment : Fragment() {

    private val args: postsUpdateFragmentArgs by navArgs()

    private lateinit var viewModel: PostsUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostsUpdateViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.posts_update_fragment, container, false)
        MainActivity.setLoadingStatus(viewModel, viewLifecycleOwner)
        MainActivity.setErrorStatus(viewModel, viewLifecycleOwner)

        fragmentView.txt_post_update_name.editText?.setText(args.post.Name)
        fragmentView.txt_post_update_content.editText?.setText(args.post.Content)

        fragmentView.btn_fragment_post_update.setOnClickListener() {
            var postUpdateDto = PostUpdateDto(
                Name  = fragmentView.txt_post_update_name.editText?.text.toString(),
                Content = fragmentView.txt_post_update_content.editText?.text.toString(),
                Fertilizer = fragmentView.text_post_update_fertilizer.editText?.text.toString(),
                Harvest = fragmentView.txt_post_update_harvest.editText?.text.toString(),
                Irrigation = fragmentView.txt_post_update_irrigation.editText?.text.toString(),
                Planting = fragmentView.txt_post_update_planting.editText?.text.toString(),
            )

            viewModel.updatePost(args.post.Id.toInt(), postUpdateDto).observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(activity, "Plant Updated", Toast.LENGTH_LONG).show()
                    fragmentView.findNavController().navigate(R.id.postsListFragmentNav)
                }
            }
        }
        return fragmentView
    }
}