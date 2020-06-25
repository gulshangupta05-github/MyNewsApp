package `in`.surelocal.mynewsapp.ui.trial

import `in`.surelocal.mynewsapp.BlankActivity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in` .surelocal.mynewsapp.R
import `in`.surelocal.mynewsapp.adapter.MyAdapter
import `in`.surelocal.mynewsapp.userinfo.UserInfo
import android.content.ContentValues.TAG
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.blank_fragment.*

private const val TAG = "BlankFragment"
class BlankFragment : Fragment() {



    private lateinit var viewModel: BlankViewModel
    private lateinit var firestore: FirebaseFirestore
     var mData = ArrayList<UserInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BlankViewModel::class.java)
        firestore= FirebaseFirestore.getInstance()
        firestore.collection("users").get()
            .addOnSuccessListener {
                it.forEach {
                    val document_id=it.id
                    val data=it.toObject(UserInfo::class.java)
                    mData.add(
                        UserInfo(
                            document_id=document_id,
                            title = data.title,
                            description = data.description,
                            currenttime =data.currenttime
                        )
                    )
                }
                storedata()
                Log.d(TAG, "onCreate:")

            }.addOnFailureListener(){
                Log.d(TAG, "Error getting documents: ")

            }

    }

    private fun storedata() {
        recyclerview.layoutManager=StaggeredGridLayoutManager(1,RecyclerView.VERTICAL)
//    recyclerview.adapter=MyAdapter(context = requireContext(),mData = mData)
        recyclerview.adapter=MyAdapter(mData,requireContext())//jo adapter me pHLE RAHEGA WO WHA PAHAL AAYEGA
    }

}