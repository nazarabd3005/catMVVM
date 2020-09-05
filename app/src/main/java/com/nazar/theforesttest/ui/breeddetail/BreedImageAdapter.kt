package com.nazar.theforesttest.ui.breeddetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nazar.theforesttest.core.model.BreedImage
import com.nazar.theforesttest.databinding.LayoutItemBreedImageBinding

class BreedImageAdapter : ListAdapter<BreedImage,BreedImageAdapter.ViewHolder>(BreedImageDiffCallback())  {


    class ViewHolder(val binding: LayoutItemBreedImageBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BreedImage){
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(LayoutItemBreedImageBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}