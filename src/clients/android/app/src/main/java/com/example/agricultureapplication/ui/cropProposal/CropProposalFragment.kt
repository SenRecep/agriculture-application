package com.example.agricultureapplication.ui.cropProposal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.agricultureapplication.R

class CropProposalFragment : Fragment() {
    private lateinit var viewModel: CropProposalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CropProposalViewModel::class.java)
        var fragmentView= inflater.inflate(R.layout.crop_proposal_fragment, container, false)
        return  fragmentView
    }
}