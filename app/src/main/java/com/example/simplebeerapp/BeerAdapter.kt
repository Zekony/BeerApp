package com.example.simplebeerapp

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebeerapp.data.model.Beer
import com.example.simplebeerapp.databinding.ItemBeerBinding
import com.example.simplebeerapp.ui.home.HomeViewModel

class BeerAdapter(val clickListener: BeerAdapterClockListener) : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

    private lateinit var binding: ItemBeerBinding
    var beerList: List<Beer> = emptyList()

    inner class ViewHolder(binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = beerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.tvTitle.text = beerList[position].name
        binding.tvPrice.text = "Цена от ${beerList[position].cost}р."
        binding.checkBox.isChecked = beerList[position].isFavorite

        binding.checkBox.setOnClickListener {
            beerList[position].isFavorite = !beerList[position].isFavorite
            beerList[position].id?.let {
                clickListener.checkBoxUpdate(it)
            }
        }

/*        binding.deleteButton.setOnClickListener {

            notifyDataSetChanged()
        }*/
    }

    fun getBeerList(beerList: List<Beer>) {
        this.beerList = beerList
        notifyDataSetChanged()
    }
}