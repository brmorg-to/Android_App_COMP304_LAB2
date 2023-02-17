package com.hfad.brunomorgado_comp304sec002_lab02_exercise1

import android.content.Context
import android.content.Intent
import android.icu.text.UnicodeSet.EMPTY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.cardview.widget.CardView
import com.hfad.brunomorgado_comp304sec002_lab02_exercise1.databinding.ActivityFormularyBinding
import com.vicmikhailau.maskededittext.MaskedEditText
import preferences.PreferencesManager

private const val PAYMENT_METHOD = "com.hfad.brunomorgado_comp304sec002_lab02_exercise1.paymentMethod"

class Formulary : AppCompatActivity() {

    private lateinit var binding: ActivityFormularyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val paymentMethod = intent.getStringExtra(PAYMENT_METHOD).toString()
        val preferencesManager = PreferencesManager(this)
        val preferences = preferencesManager.getSelectedOptions()
        val chosenProperty = preferences["Chosen Property"]
        val cost = chosenProperty?.substringAfter("$")


        when(paymentMethod) {
            "Cash" -> {
                binding.paymentEntry.setText("Thank you for choosing to pay cash. Total: $$cost")
            }
            "Credit Card" -> {
                binding.paymentEntry.setMask("#### - #### - #### - ####")
                binding.paymentEntry.hint = "Please enter your Credit Card Number"
            }
            "Debit Card" -> {
                binding.paymentEntry.setMask("#### - ## - ######## - ##")
                binding.paymentEntry.hint = "Please enter you Debit Card Number"
            }
        }

        binding.formularyButton.setOnClickListener{
            var error = 0
            if(binding.customerName.text.isEmpty()){
                binding.customerName.error = "This field is required"
                ++error
            }
            if(binding.address.text.isEmpty()){
                binding.address.error = "This field is required"
                ++error
            }
            if(binding.phone.text.toString().isEmpty()){
                binding.phone.error = "This field is required"
                ++error
            }
            if(binding.email.text.isEmpty()){
                binding.email.error = "This field is required"
                ++error
            }
            if(binding.paymentEntry.maskString?.isEmpty() == false){
                if(binding.paymentEntry.text.toString().isEmpty()){
                    ++error
                    binding.paymentEntry.error = "This field is required"
                }
            }
            if(error == 0){
                val preferencesManager = PreferencesManager(this)
                preferencesManager.addSelectedOption("Customer Name", binding.customerName.text.toString())
                preferencesManager.addSelectedOption("Address", binding.address.text.toString())
                preferencesManager.addSelectedOption("Phone Number", binding.phone.text.toString())
                preferencesManager.addSelectedOption("Email", binding.email.text.toString())
                if(binding.favouriteBook.text.isNotBlank()){
                    preferencesManager.addSelectedOption("Favourite Book", binding.favouriteBook.text.toString())
                }
                if(binding.vacation.text.isNotBlank()){
                    preferencesManager.addSelectedOption("Dream Vacation", binding.vacation.text.toString())
                }
                if(binding.favouriteMovie.text.isNotBlank()){
                    preferencesManager.addSelectedOption("Favourite Movie", binding.favouriteMovie.text.toString())
                }
                preferencesManager.addSelectedOption("Customer Name", binding.customerName.text.toString())

                val preferencesString = preferencesManager.toString()
                Log.d("FORMULARY", "Selected Preferences @ Formulary: $preferencesString")

                val intent = Intent(this, Success::class.java)
                startActivity(intent)
            }
        }
    }

//    private fun isValidNumericInput(input: String): Boolean {
//        return input.isNotBlank() && input.all { it.isDigit() }
//    }

    companion object {
        fun newIntent(packageContext: Context, paymentMethod: String): Intent {
            return Intent(packageContext, Formulary::class.java).apply {
                putExtra(PAYMENT_METHOD, paymentMethod)
            }
        }
    }
}