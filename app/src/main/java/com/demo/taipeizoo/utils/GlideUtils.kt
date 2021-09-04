package com.demo.taipeizoo.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.demo.taipeizoo.R

object GlideUtils {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImage(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.image_placeholder)
            .into(view)
    }
}