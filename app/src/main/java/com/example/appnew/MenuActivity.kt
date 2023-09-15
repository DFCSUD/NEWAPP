package com.example.appnew

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appnew.databinding.ActivityMenuBinding
import com.google.firebase.firestore.FirebaseFirestore


class MenuActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email: String? = bundle?.getString("email")
        setup(email ?: "")
    }

    private fun setup(email: String) {


        binding.updateDataBtn.setOnClickListener {

            val homeIntent = Intent(this, HomeActivity::class.java).apply {
                putExtra("email", email)
            }
            startActivity(homeIntent)


        }

    }

}