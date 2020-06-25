package `in`.surelocal.mynewsapp.adapter

import `in`.surelocal.mynewsapp.R
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rowlayout.view.*

class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
val tvtitle=itemView.findViewById<TextView>(R.id.tvTitle)
    val tvdescription=itemView.findViewById<TextView>(R.id.tvDescription)
    val row_container=itemView.findViewById<ConstraintLayout>(R.id.row_container)

}