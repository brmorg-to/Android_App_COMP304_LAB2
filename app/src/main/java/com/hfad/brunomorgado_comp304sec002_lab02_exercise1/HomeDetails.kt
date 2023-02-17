package com.hfad.brunomorgado_comp304sec002_lab02_exercise1

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import com.hfad.brunomorgado_comp304sec002_lab02_exercise1.databinding.ActivityHomeDetailsBinding
import preferences.PreferencesManager

private const val HOME_DETAILS = "com.hfad.brunomorgado_comp304sec002_lab02_exercise1.homeDetails"

class HomeDetails : AppCompatActivity() {

    private lateinit var binding: ActivityHomeDetailsBinding
    private lateinit var selectedProperty: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeTypes = intent.getStringArrayListExtra(HOME_DETAILS)

        if (homeTypes != null) {
            binding.textView6.text = homeTypes[0].toString()

            if(homeTypes.count() > 1 && homeTypes[1].isNotEmpty()){
                val radioButton = RadioButton(this)
                radioButton.text = homeTypes[1]
                radioButton.buttonTintList = ColorStateList.valueOf(Color.BLACK)
                binding.radioGroup.addView(radioButton)
            }
            if(homeTypes.count() > 2 && homeTypes[2].isNotEmpty()){
                val radioButton = RadioButton(this)
                radioButton.text = homeTypes[2]
                radioButton.buttonTintList = ColorStateList.valueOf(Color.BLACK)
                binding.radioGroup.addView(radioButton)
            }
            if(homeTypes.count() > 3 && homeTypes[3].isNotEmpty()){
                val radioButton = RadioButton(this)
                radioButton.text = homeTypes[3]
                radioButton.buttonTintList = ColorStateList.valueOf(Color.BLACK)
                binding.radioGroup.addView(radioButton)
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedID ->
            val radioButton = group.findViewById<RadioButton>(checkedID)
            selectedProperty = radioButton.text.toString()
        }

        binding.nextButton.setOnClickListener{
            val preferencesManager = PreferencesManager(this)
            preferencesManager.addSelectedOption("Chosen Property", selectedProperty)
            val selectedPreferencesString = preferencesManager.toString()
            Log.d("Home Details", "Selected Preferences @ Home Details: $selectedPreferencesString")

            val intent = Intent(this, PaymentOptions::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newIntent(packageContext: Context, homeDetails: ArrayList<String>): Intent {
            return Intent(packageContext, HomeDetails::class.java).apply {
                putExtra(HOME_DETAILS, homeDetails)
            }
        }
    }
}