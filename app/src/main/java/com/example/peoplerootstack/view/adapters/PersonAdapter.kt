package com.example.peoplerootstack.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.example.peoplerootstack.R
import com.example.peoplerootstack.databinding.ItemLoadingBinding
import com.example.peoplerootstack.databinding.ItemPersonsBinding
import com.example.peoplerootstack.model.Results
import com.example.peoplerootstack.utils.Utils.hide
import com.example.peoplerootstack.utils.Utils.show
import java.util.*
import kotlin.collections.ArrayList

class PersonAdapter internal constructor(private val personArray: ArrayList<Results>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var filterArray = ArrayList<Results>()

    var isLoading = true
    private val TYPE_LOADING = 1
    private val TYPE_PERSON = 2

    init {
        filterArray = personArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var  view: RecyclerView.ViewHolder = ViewHolder(LayoutInflater.from(parent.context), parent)

        when (viewType){
            TYPE_PERSON -> view = ViewHolder(LayoutInflater.from(parent.context), parent)
            TYPE_LOADING -> view = ViwHoldeRLoading(LayoutInflater.from(parent.context), parent)
        }

        return view
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder.itemViewType){
            TYPE_PERSON -> (holder as ViewHolder).bindHolderViewPerson(filterArray[position])
            TYPE_LOADING -> (holder as ViwHoldeRLoading).bindHolderLoading()
        }
    }

    override fun getItemCount(): Int {
        return (filterArray.size + 1)
    }

    override fun getItemViewType(position: Int): Int {

        return if (filterArray.size == 0 || position == (filterArray.size))
            TYPE_LOADING
        else{
            TYPE_PERSON
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterArray = personArray
                } else {
                    val resultList = ArrayList<Results>()
                    for (row in personArray) {
                        val name = "${row.name.first} ${row.name.last}"
                        if (name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    filterArray = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterArray
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterArray = results?.values as ArrayList<Results>
                notifyDataSetChanged()
            }

        }
    }


    inner class ViwHoldeRLoading internal constructor(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_loading, parent, false)){

        private val binding = ItemLoadingBinding.bind(itemView)

        fun bindHolderLoading(){
            if (isLoading) binding.progress.show() else binding.progress.hide()
        }
    }


    inner class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_persons, parent, false)) {

        private val binding = ItemPersonsBinding.bind(itemView)
        @SuppressLint("SetTextI18n")
        fun bindHolderViewPerson(person:Results){

            binding.namePerson.text = "${person.name.first} ${person.name.last}"
            binding.locationPerson.text = person.location.street.name

            Glide.with(itemView.context)
                .load(person.picture.medium)
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .placeholder(R.drawable.ic_baseline_person_24)
                .error(R.drawable.ic_baseline_person_24)
                .into(binding.imgPerson)
        }

    }

}