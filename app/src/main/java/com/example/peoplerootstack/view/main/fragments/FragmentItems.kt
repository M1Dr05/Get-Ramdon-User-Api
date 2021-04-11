package com.example.peoplerootstack.view.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.peoplerootstack.databinding.FragmentItemsBinding
import com.example.peoplerootstack.view.main.MainViewModel

class FragmentItems : Fragment() {

    private var _binding: FragmentItemsBinding?=null
    private lateinit var modelView : MainViewModel
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            modelView = ViewModelProvider(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentItemsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.titleFragment?.text = modelView.titleItem
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}