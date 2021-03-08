package com.test.punkapi.utils.extenders

import android.widget.ImageView
import com.bumptech.glide.request.target.Target
import com.test.punkapi.GlideApp

fun ImageView.glide_setUrlImage(url: String){
    GlideApp.with(this.context).load(url).override(Target.SIZE_ORIGINAL).thumbnail(0.5f).into(this)
}