package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.databinding.ActivitySignupBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    //    step 1 set a binding
    private lateinit var binding: ActivitySignupBinding

    //    step 2 intiailize firebase
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

//        step 3 important set up a firebase auth mater
        binding.signupButton.setOnClickListener {
            //inputs
            val email = binding.SignupEmail.text.toString()
            val password = binding.signupPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Signup succesful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Signup Unsuccesful", Toast.LENGTH_SHORT).show()

                        }
                    }
            } else{
                Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_SHORT).show()

            }
        }
        binding.loginText.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}
