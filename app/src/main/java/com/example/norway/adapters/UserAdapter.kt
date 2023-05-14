package com.example.norway.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.norway.data.UserAdapterModel
import com.example.norway.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = mutableListOf<UserAdapterModel>()
    var onItemClicked: ((UserAdapterModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder (ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with((holder as UserViewHolder).binding) {
           tvName.text = data[position].name
            root.setOnClickListener { onItemClicked?.invoke(data[position]) }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(items: List<UserAdapterModel>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}