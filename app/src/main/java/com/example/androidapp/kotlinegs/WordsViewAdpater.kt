package com.example.androidapp.kotlinegs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidapp.R

class WordsViewAdapter(var dataArray: Array<String>) : RecyclerView.Adapter<WordsViewAdapter.WordsViewHolder>() {

    var TAG = WordsViewAdapter::class.java.simpleName
    //avinash -- buy row planks from market n give it to sundar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        //inflate the row_layout_xml file in memory
        Log.i(TAG,"avinash  bought a row plank")
        //inflation -- parse xml file and  create its correspondinng memory vars
        var rowPlank = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return  WordsViewHolder(rowPlank)
    }

    //balaji --keeping count of no of items in the dataset n informs avinash
    override fun getItemCount(): Int {
        Log.i(TAG,"balaji counted --"+dataArray.size)
        return dataArray.size
    }

    //chirag -- write the data on the row plank given by sundar
    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        Log.i(TAG,"chirag is writing --"+ dataArray[position]+"on the textview shown by sundar")
        holder.tvRowItem.setText(dataArray[position])

    }

    //sundhar -- maintain the reserve planks in the holder box
    class WordsViewHolder(itemView: View) : ViewHolder(itemView){
        init {
            Log.i("WordsAdapter","sundhar is finding teh textview   row plank")

        }
        var tvRowItem = itemView.findViewById<TextView>(R.id.tvRow)

    }

}