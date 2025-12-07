package com.bignerbranch.android.zd_3_2_demin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TV_Sing_In : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tv_sing_in)
        email=findViewById(R.id.Email)
        password=findViewById(R.id.Password)

    }

    fun Login(view: View){
        if(email.text.toString().isNotEmpty()&&password.text.isNotEmpty()){
            val intent = Intent(this, TV_5::class.java)
            startActivity(intent)
        }
        else{
            val alert = AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("У вас есть пустые поля")
                .setPositiveButton("Ok",null)
                .create()
                .show()
        }
    }
}