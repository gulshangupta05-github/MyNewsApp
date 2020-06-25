package `in`.surelocal.mynewsapp.adapter

import `in`.surelocal.mynewsapp.GalleryActivity
import `in`.surelocal.mynewsapp.R
import `in`.surelocal.mynewsapp.userinfo.UserInfo
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    val mData: ArrayList<UserInfo>,
    val context: Context
):
    RecyclerView.Adapter<MyViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rowlayout,parent,false))
    }

    override fun getItemCount(): Int {
      return mData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvtitle.text=mData[position].title
        holder.tvdescription.text=mData[position].description
        holder.row_container.setOnClickListener(){

            context.startActivity(Intent(context, GalleryActivity::class.java)
                .putExtra("title",mData[position].title)
                .putExtra("description",mData[position].description)
//                .putExtra("currenttime",mData[position].currenttime)
            )
        }
    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//    }

}

//(context: Context):RecyclerView.Adapter<MyViewHolder>()