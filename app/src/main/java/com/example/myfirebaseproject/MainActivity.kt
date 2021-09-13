package com.example.myfirebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        sign_in.setOnClickListener {
            var e_mail = username.text.toString()
            var pass = password.text.toString()
            signIn(e_mail,pass)
        }

        register.setOnClickListener {
            var e_mail = username.text.toString()
            var pass = password.text.toString()
            createAccount(e_mail,pass)

        }



    }
    public override fun onStart() {
        super.onStart()


    }

    private fun createAccount(email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password).
        addOnCompleteListener(this){ task->
            if(task.isSuccessful){
                Log.d(TAG,"createUserWithEmail:success")
                val user = auth.currentUser
            }
            else{
                Log.w(TAG,"createUserWithEmail:failure",task.exception)
                Toast.makeText(baseContext,"Authentication failed",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn(email: String,password: String){
        auth.signInWithEmailAndPassword(email,password).
        addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
            } else {
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private val TAG = "EmailPassword"

}