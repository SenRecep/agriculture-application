package com.example.agricultureapplication.ui.auth.signup

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.agricultureapplication.R
import com.example.agricultureapplication.models.user.UserSignUp
import com.example.agricultureapplication.utility.HelperService
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.android.synthetic.main.sign_up_fragment.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.sign_up_fragment, container, false)
        var viewPager = requireActivity().findViewById<ViewPager2>(R.id.login_viewpager)


        viewModel.errorState.observe(viewLifecycleOwner) {
            HelperService.showErrorMessageByToast(it)
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState.Loading -> fragmentView.signup_button_kayit.text = "LOADING"
                LoadingState.Loaded -> fragmentView.signup_button_kayit.text = "SIGN UP"
            }
        }

        fragmentView.signup_button_kayit.setOnClickListener {
            var userSignUp = UserSignUp(
                FirstName = fragmentView.signup_text_first_name.editText?.text.toString(),
                LastName = fragmentView.signup_text_last_name.editText?.text.toString(),
                Email = fragmentView.signup_text_email.editText?.text.toString(),
                Password = fragmentView.signup_text_password.editText?.text.toString(),
            )
            viewModel.signUp(userSignUp).observe(viewLifecycleOwner) {
                viewPager.currentItem = 0
                if (it)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000)
                        onAlertDialog(fragmentView)
                    }

            }
        }
        return fragmentView
    }

    private fun onAlertDialog(view: View) {
        var builder = AlertDialog.Builder(view.context)
        builder.setMessage("User Registered!")
        builder.setPositiveButton("Ok") { _, _ -> }
        builder.show()
    }
}