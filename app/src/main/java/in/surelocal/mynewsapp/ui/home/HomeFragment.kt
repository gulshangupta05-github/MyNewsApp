package `in`.surelocal.mynewsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import `in`.surelocal.mynewsapp.R
import `in`.surelocal.mynewsapp.userinfo.UserInfo
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_main.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        save.setOnClickListener() {
            firestore = FirebaseFirestore.getInstance()
            val ntitle = et_title.text.toString().trim()
            val ndescription = et_description.text.toString().trim()
            val currenttimestamp=System.currentTimeMillis()
            if (ntitle.isEmpty() || ndescription.isEmpty()) {
                Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
            } else {
                val data = UserInfo(
                    title = ntitle,
                    description = ndescription,
                    currenttime =currenttimestamp
                )
                firestore.collection("users").document().set(data)
                    .addOnSuccessListener {
                        Toast.makeText(context, "data saved", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener(){
                        Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show()
                    }
            }
        }

    }
}