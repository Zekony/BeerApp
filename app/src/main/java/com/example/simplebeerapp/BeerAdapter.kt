package com.example.simplebeerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebeerapp.data.model.Beer
import com.example.simplebeerapp.databinding.ItemBeerBinding

class BeerAdapter(val clickListener: BeerAdapterClockListener) :
    RecyclerView.Adapter<BeerAdapter.ViewHolder>() {
    var beerList: List<Beer> = emptyList()

    inner class ViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun configureView(item: Beer) {
            binding.tvTitle.text = item.name
            binding.tvPrice.text = "Цена от ${item.cost.toString()}р."
            binding.checkBox.isChecked = item.isFavorite

            binding.checkBox.setOnClickListener {
                item.isFavorite = !item.isFavorite
                item.id.let {
                    clickListener.checkBoxUpdate(it)
                }
            }

            binding.root.setOnClickListener {
                item.id.let {
                    clickListener.navigateTo(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = beerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.configureView(beerList[position])
    }

    fun configureList(beerList: List<Beer>) {
        this.beerList = beerList
        notifyDataSetChanged()
    }
}