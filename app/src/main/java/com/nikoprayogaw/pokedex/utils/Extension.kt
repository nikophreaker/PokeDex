package com.nikoprayogaw.pokedex.utils

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun ImageView.loadImage(path: String) {
    Glide.with(context)
        .asBitmap()
        .load(path)
        .fitCenter()
        .into(this)
}