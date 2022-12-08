package com.example.tablet_dispenser

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablet_dispenser.databinding.ActivityRecordBinding
import com.example.tablet_dispenser.databinding.ListItemRecordBinding

class RecyclerViewAdapter(private val dataset:ArrayList<Pair<String,String>>) : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):RecyclerViewAdapter.CustomViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_record,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.CustomViewHolder, position:Int)
    {
        holder.seq.setText(dataset[position].component1())
        holder.date.setText(dataset[position].component2())
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date = itemView.findViewById<TextView>(R.id.tv_date)
        val seq = itemView.findViewById<TextView>(R.id.tv_seq)

    }
}