package uz.exemple.less52_imoui_contacts_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.exemple.less52_imoui_contacts_kotlin.R
import uz.exemple.less52_imoui_contacts_kotlin.model.ContactModel
import uz.exemple.less52_imoui_contacts_kotlin.model.ContactTopModel

class ContactAdapter(var context: Context,var contactModels: ArrayList<ContactModel>):RecyclerView.Adapter<ContactAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contacts, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactModels[position]
        val contactTopModels = getItemsList()
        holder.profile.setImageResource(contact.profile)
        holder.fullname.setText(contact.fullName)
        holder.icon_phone.setImageResource(contact.icon)
        if (contact.quality!=null){
            holder.quality.setText(contact.quality)
            holder.quality.visibility = View.VISIBLE
        }

        if (position == 0) {
            val contactTopAdapter = ContactTopAdapter(context,contactTopModels)
            holder.rv_contactsTop.layoutManager = GridLayoutManager(context, 1)
            holder.rv_contactsTop.adapter = contactTopAdapter
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 16)
            holder.rv_contactsTop.layoutParams = layoutParams
            holder.yellow_marker.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return contactModels.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var profile: ImageView
        lateinit var fullname: TextView
        lateinit var yellow_marker: TextView
        lateinit var icon_phone: ImageView
        lateinit var rv_contactsTop: RecyclerView
        lateinit var quality: TextView

        init {
            rv_contactsTop = itemView.findViewById(R.id.rv_contacts_top)
            profile = itemView.findViewById(R.id.iv_profile_contactItem)
            fullname = itemView.findViewById(R.id.tv_fullName_contactItem)
            quality = itemView.findViewById(R.id.tv_quality)
            yellow_marker = itemView.findViewById(R.id.tv_marker_yellow)
            icon_phone = itemView.findViewById(R.id.iv_iconPhone_contactItem)
        }
    }

    fun getItemsList(): ArrayList<ContactTopModel> {
        val items: java.util.ArrayList<ContactTopModel> = java.util.ArrayList<ContactTopModel>()
        items.add(ContactTopModel(R.drawable.ic_add_contact_24, "Новыие контакты"))
        items.add(ContactTopModel(R.drawable.ic_list_24, "Журнал звонков"))
        items.add(ContactTopModel(R.drawable.ic_groups_24, "Новый группавой чат"))
        return items
    }




}