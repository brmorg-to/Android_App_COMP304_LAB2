package com.hfad.brunomorgado_comp304sec002_lab02_exercise1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import com.hfad.brunomorgado_comp304sec002_lab02_exercise1.databinding.ActivityPaymentOptionsBinding
import preferences.PreferencesManager

class PaymentOptions : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentOptionsBinding
    private lateinit var selectedPayment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroup2.setOnCheckedChangeListener { group, checkedID ->
            val radioButton = group.findViewById<RadioButton>(checkedID)
            selectedPayment = radioButton.text.toString()
        }

        binding.paymentButton.setOnClickListener{
            val preferencesManager = PreferencesManager(this)
            preferencesManager.addSelectedOption("Selected Payment", selectedPayment)
            val preferencesString = preferencesManager.toString()
            Log.d("PAYMENT OPTIONS", "Selected Preferences @ Payment Options: $preferencesString")

            val intent = Formulary.newIntent(this, selectedPayment )
            startActivity(intent)
        }
    }
}