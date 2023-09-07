package com.nikoprayogaw.pokedex.utils

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun ImageView.loadImageWithPalette(path: String) {
    Glide.with(context)
        .asBitmap()
        .load(path)
        .fitCenter()
        .into(this)
}