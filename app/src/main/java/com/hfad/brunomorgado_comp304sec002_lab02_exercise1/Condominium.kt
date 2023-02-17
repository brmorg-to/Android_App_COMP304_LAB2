package com.hfad.brunomorgado_comp304sec002_lab02_exercise1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
/**
 * A simple [Fragment] subclass.
 * Use the [Condominium.newInstance] factory method to
 * create an instance of this fragment.
 */
class Condominium : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_condominium, container, false)
    }

}