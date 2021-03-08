package com.test.punkapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.punkapi.R

class FoodAdapter(private val foods: MutableList<String>): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder =
        FoodViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_food_pairing, parent, false)
        )

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            txtFood.text = item
        }
    }

    override fun getItemCount(): Int = foods.size

    private fun getItem(index: Int): String = foods[index]

    class FoodViewHolder(view: View): RecyclerView.ViewHolder(view){

        val txtFood = view.findViewById<TextView>(R.id.txt_food)

    }
}