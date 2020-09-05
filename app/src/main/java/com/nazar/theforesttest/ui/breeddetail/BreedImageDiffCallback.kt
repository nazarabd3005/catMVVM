package com.nazar.theforesttest.ui.breeddetail

import androidx.recyclerview.widget.DiffUtil
import com.nazar.theforesttest.core.model.BreedImage

class BreedImageDiffCallback : DiffUtil.ItemCallback<BreedImage>() {
    override fun areItemsTheSame(oldItem: BreedImage, newItem: BreedImage): Boolean {
        return oldItem.imageId == newItem.imageId
    }

    override fun areContentsTheSame(oldItem: BreedImage, newItem: BreedImage): Boolean {
        return oldItem == newItem
    }

}