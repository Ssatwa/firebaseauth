package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var myemail:EditText
    lateinit var mypassword:EditText
    lateinit var myconfpassword:EditText
    lateinit var signup:Button
    lateinit var login:TextView
    lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myemail = findViewById(R.id.email1)
        mypassword = findViewById(R.id.pass1)
        myconfpassword = findViewById(R.id.pass2)
        signup = findViewById(R.id.signup)
        login = findViewById(R.id.auth)
        auth = Firebase.auth


        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        signup.setOnClickListener {
            SignUpUser()
        }
    }

    private fun SignUpUser(){
        val email=myemail.text.toString()
        val pass=mypassword.text.toString()
        val confirmpass=myconfpassword.text.toString()
        if(email.isBlank()|| pass.isBlank() || confirmpass.isBlank()){
            Toast.makeText(this, "Please Email and password cannot be blank", Toast.LENGTH_LONG).show()
            return
        }
        else if (pass != confirmpass){
            Toast.makeText(this, "Password do not match", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Signed up successfully", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "failed to create", Toast.LENGTH_LONG).show()
            }
        }
    }







}
