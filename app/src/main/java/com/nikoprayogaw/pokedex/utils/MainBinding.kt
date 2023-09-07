package com.nikoprayogaw.pokedex.utils

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.nikoprayogaw.pokedex.utils.MainBinding.setLoadImage

object MainBinding {
    @SuppressLint("CheckResult")
    @BindingAdapter("getCard", "loadImage")
    @JvmStatic
    fun ImageView.setLoadImage(viewCard: CardView, id: String?) {
        val url = ConstantVariable.url_sprite + id + ".png"
        this.loadImageWithPalette(url)
//        Glide.with(context)
//            .asBitmap()
//            .load(url)
//            .into(object : Target<Bitmap> {
//                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                    val palette = Palette.from(resource)
//                        .generate()
//                        .vibrantSwatch?.rgb
//                    if (palette != null) {
//                        viewCard.setCardBackgroundColor(palette)
//                    } else {
//                        viewCard.setCardBackgroundColor(Color.RED)
//                    }
//                }
//            })
    }
}