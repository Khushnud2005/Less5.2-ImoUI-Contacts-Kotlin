package uz.exemple.less52_imoui_contacts_kotlin

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import uz.exemple.less52_imoui_contacts_kotlin.adapter.ImoAdapter
import uz.exemple.less52_imoui_contacts_kotlin.model.*

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var recyclerView: RecyclerView
    lateinit var bn_view: BottomNavigationView
    lateinit var  imoModels: ArrayList<ImoModel<*>>

    lateinit var linearLayoutManager: LinearLayoutManager
    var context: Context = this

    private var isUserScrolling = false
    private val isListGoingUp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    fun initViews(){
        tabLayout = findViewById(R.id.tab_layout)
        recyclerView = findViewById(R.id.recyclerView)
        bn_view = findViewById(R.id.bottom_navigation_view)

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)



        imoModels = getImoLists()
        for (i in 0..3) {
            tabLayout.addTab(tabLayout.newTab())

            val imageView = ImageView(context)
            imageView.setImageResource(imoModels[i].icon)
            tabLayout.getTabAt(i)!!.customView = imageView
        }

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                isUserScrolling = false
                val position = tab.position
                if (position == 0) {
                    recyclerView.smoothScrollToPosition(0)
                    bn_viewOff()
                } else if (position == 1) {
                    recyclerView.smoothScrollToPosition(1)
                    bn_viewOff()
                } else if (position == 2) {
                    recyclerView.smoothScrollToPosition(2)
                    bn_viewOff()
                } else if (position == 3) {
                    recyclerView.smoothScrollToPosition(3)
                    bn_viewOn()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isUserScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val itemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (isUserScrolling) {
                    if (itemPosition == 0) { //  item position of uses
                        val tab = tabLayout.getTabAt(0)
                        tab!!.select()
                        bn_viewOff()
                    } else if (itemPosition == 1) { //  item position of side effects
                        val tab = tabLayout.getTabAt(1)
                        tab!!.select()
                        bn_viewOff()
                    } else if (itemPosition == 2) { //  item position of how it works
                        val tab = tabLayout.getTabAt(2)
                        tab!!.select()
                        bn_viewOff()
                    } else if (itemPosition == 3) { //  item position of how it works
                        val tab = tabLayout.getTabAt(3)
                        tab!!.select()
                        bn_viewOn()
                    }
                }
            }
        })
        refreshAdapter(imoModels)
    }

    private fun refreshAdapter(imoModels: ArrayList<ImoModel<*>>) {
        val adapter = ImoAdapter(context, imoModels)
        recyclerView.adapter = adapter
    }

    fun getImoLists(): ArrayList<ImoModel<*>> {
        val imoModels: ArrayList<ImoModel<*>> = ArrayList()
        val profileModels: ArrayList<ProfileModel> = ArrayList()
        val chatModels: ArrayList<ChatModel> = ArrayList()
        val groupModels: ArrayList<GroupModel> = ArrayList()
        val contactModels: ArrayList<ContactModel> = getContactLists()
        imoModels.add(ImoModel("profile", R.drawable.profile, profileModels))
        imoModels.add(ImoModel("chat", R.drawable.chat, chatModels))
        imoModels.add(ImoModel("group", R.drawable.group, groupModels))
        imoModels.add(ImoModel("contacts", R.drawable.contacts, contactModels))
        return imoModels
    }

    fun getContactLists(): ArrayList<ContactModel> {
        val contactModels: ArrayList<ContactModel> = ArrayList()
        contactModels.add(ContactModel(R.drawable.mark_chanel, "Каналы Kotlin", R.drawable.ic_right_24,null))
        contactModels.add(ContactModel(R.drawable.photo3,"Kamolaxon Nematjonova",R.drawable.ic_phone_24,null))
        contactModels.add(ContactModel(R.drawable.photo2,"Barnoxon Kabirova",R.drawable.ic_phone_24,null))
        contactModels.add(ContactModel(R.drawable.photo4,"Abdullatif Nematjonov",R.drawable.ic_phone_24,"[HD Quality]" ))
        contactModels.add(ContactModel(R.drawable.photo1,"Xushnud Boymurodov",R.drawable.ic_phone_24,null))
        contactModels.add(ContactModel(R.drawable.photo3,"Kamolaxon Nematjonova",R.drawable.ic_phone_24,null))
        contactModels.add(ContactModel(R.drawable.photo2,"Barnoxon Kabirova",R.drawable.ic_phone_24,null))
        contactModels.add(ContactModel(R.drawable.photo4,"Abdullatif Nematjonov", R.drawable.ic_phone_24,null))
        contactModels.add(ContactModel(R.drawable.photo1,"Xushnud Boymurodov", R.drawable.ic_phone_24,"[HD Quality]"))
        contactModels.add(ContactModel(R.drawable.photo3,"Kamolaxon Nematjonova", R.drawable.ic_phone_24,null))
        contactModels.add(ContactModel(R.drawable.photo2,"Barnoxon Kabirova", R.drawable.ic_phone_24,"[HD Quality]" ))
        return contactModels
    }

    fun bn_viewOn() {
        bn_view.visibility = View.VISIBLE
    }

    fun bn_viewOff() {
        bn_view.visibility = View.GONE
    }

}