package `in`.surelocal.mynewsapp.login

import `in`.surelocal.mynewsapp.*
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.content.SharedPreferences
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*

class LogInFragment : Fragment() {

    companion object {
        fun newInstance() = LogInFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mauth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        login.setOnClickListener() {
            mauth = FirebaseAuth.getInstance()

            val email = et_username.text.toString().trim()
            val password = et_password.text.toString().trim()
            if (email.isEmpty()) {
                et_userlayout.error = "user name empty"
                et_userlayout.requestFocus()
            } else if (password.isEmpty()) {
                et_1passwordlayout.error = "password empty"
                et_1passwordlayout.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                et_userlayout.error = "Invailid UserName"
                et_userlayout.requestFocus()
            } else {
                mauth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            val intent =
                                Intent(requireContext(), NavigationDrawerActivity::class.java)
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            activity?.finish()
                            val email = et_username.text.toString().trim()
                            val password = et_username.text.toString().trim()
                            val sp = requireContext().getSharedPreferences(
                                "DataInfo",
                                Context.MODE_PRIVATE
                            )
                            val editor = sp.edit()
                            editor.putString("email", email)
                            editor.putString("password", password)
                            editor.apply()
                            Toast.makeText(context, "Log in Successful", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Email id not match", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        txt_create.setOnClickListener() {
            val intent = Intent(requireContext(), SignUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun onStart() {
        super.onStart()

    }
}

