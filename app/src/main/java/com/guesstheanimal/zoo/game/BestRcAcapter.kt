package com.guesstheanimal.zoo.game

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class BestRcAcapter :RecyclerView.Adapter<BestRcAcapter.ViewHolder>() {
    val plantList = ArrayList<BestData>()
    lateinit var context: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context = view.context

        lateinit var text: TextView

        fun bind(listItem:BestData, context: Context) {
            text = itemView.findViewById(R.id.textView12)
            text.text = "${listItem.id}. ${listItem.pst} points"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.best_rc_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(plantList[position],context)
    }

    override fun getItemCount(): Int {
        Log.d("sadasd",plantList.size.toString())
        return plantList.size
    }

    fun addPlant(plant: BestData){
        plantList.add(plant)
    }

}