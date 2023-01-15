package com.example.ktln2.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ktln2.R
import com.example.ktln2.data.UserData

class UserAdapter(val c:Context, val userList:ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    inner class  UserViewHolder(val v: View):RecyclerView.ViewHolder(v){
        var name: TextView
        var mMenus: ImageView
        init {
            name = v.findViewById<TextView>(R.id.mTitle)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener{ editMenus(it)
            }

        }

        private fun editMenus(v: View) {
            val position = userList[adapterPosition]
            val editMenus = PopupMenu(c.applicationContext, v)
            editMenus.inflate(R.menu.show_menu)
            editMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_item, null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("ok") {
                                    dialog, which ->
                                position.userName = name.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c, "Edit Text", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    else-> true
                }
            }
            editMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(editMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item, parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
    }

    override fun getItemCount(): Int {
     return userList.size
    }
}