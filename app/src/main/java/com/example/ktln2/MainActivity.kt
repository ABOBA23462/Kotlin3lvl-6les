package com.example.ktln2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktln2.adapter.UserAdapter
import com.example.ktln2.data.UserData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var addsBtn : FloatingActionButton
    private lateinit var recv : RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter : UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = ArrayList()

        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)

        userAdapter = UserAdapter(this, userList)

        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter

        addsBtn.setOnClickListener{
            addInfo()
        }
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("yes") { dialog, which ->
            val names = userName.text.toString()
            userList.add(UserData("$names"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding", Toast.LENGTH_SHORT).show()
           dialog.dismiss()
        }

        addDialog.create()
        addDialog.show()
    }

}