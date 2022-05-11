package com.example.agricultureapplication.ui.launch

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.lifecycle.ViewModelProvider
import com.example.agricultureapplication.MainActivity
import com.example.agricultureapplication.R
import com.example.agricultureapplication.ui.auth.AuthActivity
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity() {
    lateinit var viewModel: LaunchActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        viewModel = ViewModelProvider(this).get(LaunchActivityViewModel::class.java)

        var scaleX= PropertyValuesHolder.ofFloat(View.SCALE_X,1.2f)
        var scaleY= PropertyValuesHolder.ofFloat(View.SCALE_Y,1.2f)
        var animator= ObjectAnimator.ofPropertyValuesHolder(img_launch_icon,scaleX,scaleY)
        animator.repeatMode= ObjectAnimator.REVERSE
        animator.repeatCount= Animation.INFINITE
        animator.duration=1000

        viewModel.loadingState.observe(this){
            when(it){
                LoadingState.Loaded->animator.cancel()
                LoadingState.Loading->animator.start()
            }
        }


        viewModel.tokenCheck().observe(this) {
            var intent: Intent? = when (it) {
                true ->
                    Intent(this@LaunchActivity, MainActivity::class.java)
                false ->
                    Intent(this@LaunchActivity, AuthActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }
}