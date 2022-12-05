package com.example.tablet_dispenser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tablet_dispenser.databinding.ListItemRecordBinding

class RecyclerViewAdapter(private val dataset:ArrayList<List<String>>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = ListItemRecordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
       return dataset.size
    }

    class ViewHolder(private val binding:ListItemRecordBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(data:List<String>) {
            binding.tvDate.text = data[0]
            binding.tvSeq.text = data[1]
        }


    }

}