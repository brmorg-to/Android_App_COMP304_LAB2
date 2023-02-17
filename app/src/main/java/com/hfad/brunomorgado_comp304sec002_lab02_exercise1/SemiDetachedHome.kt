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
import preferences.PreferencesManager

/**
 * A simple [Fragment] subclass.
 * Use the [SemiDetachedHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class SemiDetachedHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_semi_detached_home, container, false)
        val selections = arrayListOf<String>()
        val checkout = view.findViewById<Button>(R.id.semiDetachedCheckoutButton)

        checkout.setOnClickListener{
            selections.add(view.findViewById<TextView>(R.id.semiDetachedHeader).text.toString())

            if(view.findViewById<CheckBox>(R.id.checkBox1)?.isChecked == true) {
                selections.add(view.findViewById<TextView>(R.id.textView).text.toString())
            }
            if (view.findViewById<CheckBox>(R.id.checkBox2)?.isChecked == true) {
                selections.add(view.findViewById<TextView>(R.id.textView2).text.toString())
            }
            if (view.findViewById<CheckBox>(R.id.checkBox3)?.isChecked == true) {
                selections.add(view.findViewById<TextView>(R.id.textView3).text.toString())
            }

            val preferencesManager = PreferencesManager(requireContext())
            for((index, value) in selections.withIndex()) {
                if(index == 0){
                    preferencesManager.addSelectedOption("Home type", value)
                } else {
                    preferencesManager.addSelectedOption("${selections[0]}_$index", value)
                }
            }
            val selectedOptionsString = preferencesManager.toString()
            Log.d("Semi-Detached", "Selected Semi-Detached: $selectedOptionsString")

            val intent = HomeDetails.newIntent(requireContext(), selections )
            startActivity(intent)
        }

        return view
    }

}