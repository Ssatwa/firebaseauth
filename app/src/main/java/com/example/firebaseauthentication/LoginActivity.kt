package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var myemail1:EditText
    private lateinit var mypassword1:EditText
    lateinit var login1:Button
    private lateinit var auth:FirebaseAuth
    lateinit var signup:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        myemail1=findViewById(R.id.email2)
        mypassword1=findViewById(R.id.pass3)
        login1=findViewById(R.id.login)
        signup=findViewById(R.id.up)
        auth= FirebaseAuth.getInstance()

        login1.setOnClickListener {
            login()
        }

        signup.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {
        val email = myemail1.text.toString()
        val pass = mypassword1.text.toString()

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else
                Toast.makeText(this, "Log in failed", Toast.LENGTH_LONG).show()
        }

    }

}