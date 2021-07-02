package com.example.agendabootcamp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events.*
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSetEvent = findViewById(R.id.set_event) as Button
        val btnContactList = findViewById(R.id.contact_list_button) as Button

        btnSetEvent.setOnClickListener{
            //Toast.makeText(this@MainActivity,"gomo corno",Toast.LENGTH_LONG).show()

            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CONTENT_URI)
                .putExtra(TITLE, "Teste Agenda")
                .putExtra(EVENT_LOCATION,"on line")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,System.currentTimeMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis() + (60*60*1000))
            startActivity(intent)
        }

        btnContactList.setOnClickListener{
            //Toast.makeText(this@MainActivity,"gomo corno",Toast.LENGTH_LONG).show()
            val intent = Intent(this,ContactListActivity::class.java)
            startActivity(intent)

        }
    }
}