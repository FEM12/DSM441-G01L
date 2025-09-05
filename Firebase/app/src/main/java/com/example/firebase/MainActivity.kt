package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var tvUser: TextView
    private lateinit var btnLogout: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvUser = findViewById(R.id.tvUser)
        btnLogout = findViewById(R.id.btnLogout)
        auth = FirebaseAuth.getInstance()

        val currentUser: FirebaseUser? = auth.currentUser

        if (currentUser != null) {

            val userName = currentUser.displayName ?: currentUser.email
            tvUser.text = "Welcome"

        }

        btnLogout.setOnClickListener {

            auth.signOut()
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

}