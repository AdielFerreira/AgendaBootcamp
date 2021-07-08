package com.example.agendabootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        button_entrar.setOnClickListener{
            login(edit_text_email.text.toString(),edit_text_password.text.toString())
        }
    }

    private fun login(email:String, senha:String){

        auth?.signInWithEmailAndPassword(email,senha)?.addOnCompleteListener(this){ task->

            if(task.isSuccessful){
                Toast.makeText(this,"Login Bem Sucedido!",Toast.LENGTH_LONG).show()
                this.finish()
            }else{
                Toast.makeText(this,"Login ou senha incorreta!",Toast.LENGTH_LONG).show()
            }
        }
    }
}