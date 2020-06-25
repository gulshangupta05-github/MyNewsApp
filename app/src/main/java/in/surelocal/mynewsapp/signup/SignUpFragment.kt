package `in`.surelocal.mynewsapp.signup

import `in`.surelocal.mynewsapp.NavigationDrawerActivity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.surelocal.mynewsapp.R
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signup_fragment.*

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.signup_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        auth= FirebaseAuth.getInstance()
        signup.setOnClickListener(){

            val name = et_name.text.toString().trim()
            val mobile = et_mobile.text.toString().trim()
            val email = et_emailsignup.text.toString().trim()
            val password = et_signuppassword.text.toString().trim()
            val confirm = et_confirmPassword.text.toString().trim()

            if (name.isEmpty()) {
                et_1userlayout.error = "Name Empty"
                et_1userlayout.requestFocus()
            } else if (mobile.isEmpty()) {
                et_mobilelayout.error = "Mobile no Empty"
                et_mobilelayout.requestFocus()
            } else if (email.isEmpty()) {
                et_emaillayout.error = "Email Empty"
                et_emaillayout.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                et_emaillayout.error = "invailid email"
                et_emaillayout.requestFocus()
            } else if (password.isEmpty()) {
                et_passwordlayout.error = "Password Empty"
                et_passwordlayout.requestFocus()
            } else if (confirm.isEmpty()) {
                et_confirmlayout.error = "Confirm Password Empty"
                et_confirmlayout.requestFocus()
            } else if (password != confirm) {
                Toast.makeText(context, "password not match", Toast.LENGTH_SHORT).show()
            } else{
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(){task->
                        if (task.isSuccessful){
                            val intent= Intent(requireContext(), NavigationDrawerActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            activity?.finish()
                            Log.d(ContentValues.TAG, "onActivityCreated:pass")

                            Toast.makeText(context, "Registration Successfully", Toast.LENGTH_SHORT).show()
                        }else{
                            Log.d(ContentValues.TAG, "onActivityCreated:  failed ")
                            Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener(){
                        Log.d(ContentValues.TAG, "onActivityCreated: ${it.message}")
//                        Toast.makeText(context, "Not All Match", Toast.LENGTH_SHORT).show()

                    }
            }
        }
    }

}