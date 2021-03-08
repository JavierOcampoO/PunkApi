package com.test.punkapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.test.punkapi.R
import com.test.punkapi.data.model.Beer
import com.test.punkapi.utils.extenders.glide_setUrlImage
import com.test.punkapi.utils.handlers.OnItemClickListener

class BeersAdapter(private val beers: MutableList<Beer>, private val onItemClickListener: OnItemClickListener<Beer>): RecyclerView.Adapter<BeersAdapter.BeerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder =
        BeerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false)
        )

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            parentView.setOnClickListener { onItemClickListener.onItemClick(position, item) }

            item.imageUrl?.let{imgBeer.glide_setUrlImage(it)}

            txtTitle.text = item.name

            txtDescription.text = item.tagline

        }
    }

    override fun getItemCount(): Int = beers.size

    private fun getItem(index: Int): Beer = beers[index]

    fun addItems(items: List<Beer>){
        val size = beers.size
        beers.addAll(items)
        //notifyItemRangeChanged(size, items.size)
        notifyDataSetChanged()
    }

    fun removeItems(){
        beers.clear()
        notifyDataSetChanged()
    }

    class BeerViewHolder(view: View): RecyclerView.ViewHolder(view){

        val parentView = view.findViewById<CardView>(R.id.parent_view)
        val imgBeer = view.findViewById<ImageView>(R.id.img_beer)
        val txtTitle = view.findViewById<TextView>(R.id.txt_title)
        val txtDescription = view.findViewById<TextView>(R.id.txt_description)

    }
}