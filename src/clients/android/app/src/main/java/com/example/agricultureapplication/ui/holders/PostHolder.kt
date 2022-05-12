package com.example.agricultureapplication.ui.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item.view.*

class PostHolder(view: View) : RecyclerView.ViewHolder(view) {
    var txtName=view.txt_name;
    var txtContent=view.txt_content;
}