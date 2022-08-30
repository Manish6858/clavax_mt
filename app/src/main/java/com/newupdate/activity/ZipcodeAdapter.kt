package com.newupdate.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.newupdate.R
import com.newupdate.model.PincodeItemModel

class ZipcodeAdapter(value1: MainActivity, val value: List<PincodeItemModel>?) : RecyclerView.Adapter<ZipcodeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.zip_code_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.zip_code!!.text = value?.get(position)?.zipcodes.toString()

        holder.itemView.setOnClickListener { v->

        }



    }

    override fun getItemCount(): Int {
        return value!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var zip_code : TextView ?= null


        init {
            zip_code = view.findViewById(R.id.zip_code_tv)

        }

    }
}