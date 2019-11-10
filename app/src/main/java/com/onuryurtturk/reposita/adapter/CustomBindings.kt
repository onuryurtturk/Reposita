package com.onuryurtturk.reposita.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class CustomBindings {

    //Includes custom binder methods for custom attributes
    /**
     * imageview src custom binding method
     * @param imageView view reference
     * @param avatarUrl user profile pic url
     */
    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun loadUserImage(view: ImageView, imageUrl: String) {
            Picasso.get().load(imageUrl).into(view)
        }
    }

}