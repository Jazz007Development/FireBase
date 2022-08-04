package com.jazz.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database =Firebase.database.reference
        val listener=object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val data=snapshot.getValue(String::class.java)
                findViewById<TextView>(R.id.tvw_data).text="Firebase remote: $data"
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        val dataRef=database.child("Firebase").child("data")

        dataRef.addValueEventListener(listener)

        findViewById<MaterialButton>(R.id.btnSend).setOnClickListener {
            val data=findViewById<TextInputEditText>(R.id.etData).text.toString()
            dataRef.setValue(data)
        }

    }
}