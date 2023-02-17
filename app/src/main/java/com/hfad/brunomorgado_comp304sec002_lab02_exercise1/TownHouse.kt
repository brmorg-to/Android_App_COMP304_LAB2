package com.hfad.brunomorgado_comp304sec002_lab02_exercise1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import preferences.PreferencesManager

/**
 * A simple [Fragment] subclass.
 * Use the [TownHouse.newInstance] factory method to
 * create an instance of this fragment.
 */
class TownHouse : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_town_house, container, false)
        var selections = arrayListOf<String>()
        val checkout = view.findViewById<Button>(R.id.detachedCheckoutButton)

        checkout.setOnClickListener{
            selections.add(view.findViewById<TextView>(R.id.townHouse).text.toString())

            if(view.findViewById<CheckBox>(R.id.checkBox1)?.isChecked == true) {
                selections.add(view.findViewById<TextView>(R.id.textView).text.toString())
            }
            if (view.findViewById<CheckBox>(R.id.checkBox2)?.isChecked == true) {
                selections.add(view.findViewById<TextView>(R.id.textView2).text.toString())
            }
            if (view.findViewById<CheckBox>(R.id.checkBox3)?.isChecked == true) {
                selections.add(view.findViewById<TextView>(R.id.textView3).text.toString())
            }

            Log.d("Selections Size", "${selections.count()}")

            if(selections.count() < 2) {
                Toast.makeText(requireContext(), "You must select at least one option", Toast.LENGTH_LONG).show()
                selections = arrayListOf<String>()
            } else{
                val preferencesManager = PreferencesManager(requireContext())
                for((index, value) in selections.withIndex()) {
                    if(index == 0){
                        preferencesManager.addSelectedOption("Home type", value)
                    } else {
                        preferencesManager.addSelectedOption("${selections[0]}_$index", value)
                    }
                }
                val selectedOptionsString = preferencesManager.toString()
                Log.d("Town House", "Selected Town House: $selectedOptionsString")

                val intent = HomeDetails.newIntent(requireContext(), selections )
                startActivity(intent)
            }
        }
        return view
    }
}