package com.example.agricultureapplication.ui.auth.signin

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.agricultureapplication.MainActivity
import com.example.agricultureapplication.R
import com.example.agricultureapplication.models.user.UserSignIn
import com.example.agricultureapplication.utility.HelperService
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.android.synthetic.main.sign_in_fragment.view.*
import kotlinx.android.synthetic.main.sign_up_fragment.view.*

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        var fragmentView = inflater.inflate(R.layout.sign_in_fragment, container, false)

        viewModel.errorState.observe(viewLifecycleOwner) {
            HelperService.showErrorMessageByToast(it)
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                LoadingState.Loading -> fragmentView.signin_button_kayit.text = "LOADING"
                LoadingState.Loaded -> fragmentView.signin_button_kayit.text = "SIGN IN"
            }
        }

        fragmentView.signin_button_kayit.setOnClickListener {
            var userSignIn = UserSignIn(
                Email = fragmentView.signin_text_email.editText?.text.toString(),
                Password = fragmentView.signin_text_password.editText?.text.toString(),
            )
            viewModel.signIn(userSignIn).observe(viewLifecycleOwner) {
                if (it){
                    var intent = Intent(requireActivity(),MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }
        return fragmentView
    }


}