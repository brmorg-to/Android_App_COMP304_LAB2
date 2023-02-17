package com.hfad.brunomorgado_comp304sec002_lab02_exercise1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.brunomorgado_comp304sec002_lab02_exercise1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enterButton.setOnClickListener{
            val intent = Intent(this, HomeTypes::class.java)
            startActivity(intent)
        }
    }
}