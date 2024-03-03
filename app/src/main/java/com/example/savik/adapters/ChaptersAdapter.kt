package com.example.savik.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.savik.R
import com.example.savik.activities.ShlokaActivity
import com.example.savik.models.chepters.CheptersItem

class ChaptersAdapter(val list:List<CheptersItem>):RecyclerView.Adapter<ChaptersAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val chnumber:TextView = view.findViewById(R.id.chnumber)
        val chanme:TextView = view.findViewById(R.id.chname)
        val chmeaning:TextView = view.findViewById(R.id.chmeaning)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(view = LayoutInflater.from(parent.context).inflate(R.layout.chapters_design,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        val context = holder.itemView.context
        holder.chanme.text = model.name
        holder.chnumber.text = model.chapter_number.toString()
        holder.chmeaning.text = model.meaning.en
        holder.itemView.setOnClickListener {
            val intent = Intent(context,ShlokaActivity::class.java)
            intent.putExtra("chname",model.name)
            intent.putExtra("chnumber",model.chapter_number)
            context.startActivity(intent)
        }

    }


}