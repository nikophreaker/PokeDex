package com.nikoprayogaw.pokedex.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout


object MainBinding {
    @SuppressLint("CheckResult")
    @BindingAdapter("loadImage")
    @JvmStatic
    fun ImageView.setLoadImage(id: String?) {
        if (!id.isNullOrEmpty()) {
            val url = ConstantVariable.url_sprite + id + ".png"
            this.loadImage(url)
        }
    }

    @SuppressLint("CheckResult")
    @BindingAdapter("getParent","getAppbar", "getToolbar", "getColor")
    @JvmStatic
    fun ImageView.setColor(
        parent: CoordinatorLayout,
        appbar: AppBarLayout,
        toolbar: CollapsingToolbarLayout,
        type: MutableList<String>?
    ) {
        if (!type.isNullOrEmpty()) {
            val color = PokemonColorUtil(context).getPokemonColor(type)
            appbar.setBackgroundColor(color)
            toolbar.contentScrim?.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            if (parent.context is Activity) {
                val window = (parent.context as Activity).window
                window?.statusBarColor = color
            }
        }
    }
    @SuppressLint("CheckResult")
    @BindingAdapter("setColorBg")
    @JvmStatic
    fun RelativeLayout.setColorBg(
        type: List<String>?
    ) {
        if (!type.isNullOrEmpty()) {
            val color = PokemonColorUtil(context).getPokemonColor(type)
            this.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}