package com.example.chatapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chatapplication.R.id
import com.example.chatapplication.R.id.*
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(edt_password)
        btnlogin = findViewById(btnLogin)
        btnsignup = findViewById(btnSighUp)

        btnsignup.setOnClickListener {

            val intent = Intent(this , SignUp::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email , password)
        }


    }

    private fun login(email: String ,password: String){
        //logic for login
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login , MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login , "user does not exist" , Toast.LENGTH_SHORT).show()
                }
            }
    }

}