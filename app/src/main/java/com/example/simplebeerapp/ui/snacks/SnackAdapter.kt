package com.example.simplebeerapp.ui.snacks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebeerapp.R
import com.example.simplebeerapp.data.network.bodies.Data
import com.example.simplebeerapp.databinding.ItemSnackBinding


class SnackAdapter(val clickListener: SnackClickListener) :
    RecyclerView.Adapter<SnackAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSnackBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun configureView(item: Data) {
            binding.name.text = item.name
            binding.price.text = "${item.price.toString()} ₽"
            binding.infoImage.setImageResource(R.drawable.kardimovskoe)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSnackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.configureView(differ.currentList[position])
    }

    private val differCallBack = object :
        DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.UID == newItem.UID
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
}