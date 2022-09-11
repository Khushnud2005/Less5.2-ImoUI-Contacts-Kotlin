package uz.exemple.less52_imoui_contacts_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.exemple.less52_imoui_contacts_kotlin.R
import uz.exemple.less52_imoui_contacts_kotlin.model.ContactTopModel

class ContactTopAdapter(var context: Context,val arrayList: ArrayList<ContactTopModel>):RecyclerView.Adapter<ContactTopAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contacts_top, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayList[position]
        holder.icon.setImageResource(item.icon)
        holder.title.setText(item.title)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var title: TextView
        lateinit var icon: ImageView

        init {
            title = itemView.findViewById(R.id.tv_title_CIT)
            icon = itemView.findViewById(R.id.iv_icon_TIC)
        }
    }


}