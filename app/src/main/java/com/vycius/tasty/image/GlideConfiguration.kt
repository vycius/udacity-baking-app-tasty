package com.vycius.tasty.image

import android.content.Context
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.vycius.tasty.App
import com.vycius.tasty.image.model.VideoThumbnailUrl
import okhttp3.OkHttpClient
import java.io.InputStream
import javax.inject.Inject


@GlideModule
class GlideConfiguration : AppGlideModule() {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun registerComponents(context: Context, registry: Registry) {
        App.get(context).component.inject(this)

        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
        registry.replace(VideoThumbnailUrl::class.java, InputStream::class.java, VideoThumbnailFactory())
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}