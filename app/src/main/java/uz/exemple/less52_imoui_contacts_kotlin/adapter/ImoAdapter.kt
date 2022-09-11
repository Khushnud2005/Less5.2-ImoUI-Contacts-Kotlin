package uz.exemple.less52_imoui_contacts_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.exemple.less52_imoui_contacts_kotlin.R
import uz.exemple.less52_imoui_contacts_kotlin.model.ContactModel
import uz.exemple.less52_imoui_contacts_kotlin.model.ImoModel

class ImoAdapter(val context: Context,val imoModels: ArrayList<ImoModel<*>>):RecyclerView.Adapter<ImoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_imo, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imomodel: ImoModel<*> = imoModels[position]

        val contactAdapter: ContactAdapter
        contactAdapter = ContactAdapter(context,
            imoModels[position].arrayList as ArrayList<ContactModel>
        )
        holder.rv_contacts.layoutManager = GridLayoutManager(context, 1)
        holder.rv_contacts.adapter = contactAdapter
    }

    override fun getItemCount(): Int {
        return imoModels.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var rv_contacts: RecyclerView
        init {
            rv_contacts = itemView.findViewById(R.id.rv_contacts)
        }
    }
}