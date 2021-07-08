package com.example.agendabootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_entrar.setOnClickListener{
            login()
        }
    }

    private fun login(){
        if(edit_text_email.text.toString() == "adiel" && edit_text_password.text.toString() == "cidoso"){
            Toast.makeText(this,"Login Bem Sucedido!",Toast.LENGTH_LONG).show()
            this.finish()
        }
    }
}