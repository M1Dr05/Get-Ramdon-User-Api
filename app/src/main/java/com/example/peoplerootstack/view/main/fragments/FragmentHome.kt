package com.example.peoplerootstack.view.main.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peoplerootstack.databinding.FragmentHomeBinding
import com.example.peoplerootstack.model.Resource
import com.example.peoplerootstack.model.Results
import com.example.peoplerootstack.utils.Utils.longToast
import com.example.peoplerootstack.view.adapters.PersonAdapter
import com.example.peoplerootstack.view.main.MainViewModel

class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding?=null
    private lateinit var modelView : MainViewModel
    private var listPerson:ArrayList<Results> = arrayListOf()
    private var isLoadingData: Boolean = false
    private var adapterPerson:PersonAdapter?=null

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            modelView = ViewModelProvider(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        init()
        getPersons()
    }

    private fun init(){
        modelView.page = 1
        binding?.edtSearch?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isLoadingData = s.toString().isNotEmpty()
                adapterPerson?.filter?.filter(s)
            }
        })
    }

    private fun initRecycler(){
        adapterPerson = PersonAdapter(listPerson)

        val manager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding?.listPersons?.apply {
            layoutManager = manager
            adapter = adapterPerson
            recycledViewPool.clear()
        }

        binding?.listPersons?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = manager.itemCount-1
                val visible = manager.findLastVisibleItemPosition() + 1

                if (!isLoadingData && totalItemCount<=visible) {
                    isLoadingData = true
                    adapterPerson?.isLoading = true
                    adapterPerson?.notifyDataSetChanged()
                    getPersons()
                }
            }
        })
    }

    private fun getPersons(){
        modelView.getPersons().observe(viewLifecycleOwner,{
            if (it.status == Resource.Status.SUCCESS && it.data != null) {
                adapterPerson?.isLoading = false
                isLoadingData = false
                if (it.data.results.isNotEmpty()) listPerson.addAll(it.data.results)
                adapterPerson?.notifyDataSetChanged()

            }else {
                adapterPerson?.isLoading = false
                adapterPerson?.notifyDataSetChanged()
                requireContext().longToast(it?.error?.message?:"Error")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}