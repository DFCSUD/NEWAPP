package com.example.appnew

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appnew.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")

        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
        binding.emailTextView.text = email
        binding.providerTextView.text = provider

        binding.logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressedDispatcher.onBackPressed()
        }

        binding.saveBtn.setOnClickListener{
            db.collection("users").document(email).set(
                hashMapOf("provider" to provider,
                            "address" to binding.address.text.toString(),
                            "phone" to binding.phoneTxtView.text.toString(),
                            "nameUser" to binding.nameUser.text.toString())
            )

        }
        binding.consultarBtn.setOnClickListener{
            db.collection("users").document(email).get().addOnSuccessListener {
                binding.nameUser.setText(it.get("nameUser") as String?)
                binding.address.setText(it.get("address") as String?)
                binding.phoneTxtView.setText(it.get("phone") as String?)
            }

        }


    }

}